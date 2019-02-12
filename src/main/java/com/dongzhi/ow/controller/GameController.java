package com.dongzhi.ow.controller;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongzhi.ow.pojo.Category;
import com.dongzhi.ow.pojo.Game;
import com.dongzhi.ow.pojo.GameTable;
import com.dongzhi.ow.pojo.Live;
import com.dongzhi.ow.pojo.News;
import com.dongzhi.ow.pojo.People;
import com.dongzhi.ow.pojo.Ranks;
import com.dongzhi.ow.service.GameService;
import com.dongzhi.ow.service.GameTableService;
import com.dongzhi.ow.service.LiveService;
import com.dongzhi.ow.service.RanksService;
import com.dongzhi.ow.util.Filepath;
import com.dongzhi.ow.util.ImageUtil;
import com.dongzhi.ow.util.UploadImageFile;

@Controller
@RequestMapping("")
public class GameController {

	@Autowired
	GameService gameService;
	@Autowired
	RanksService ranksService;
	@Autowired
	LiveService liveService;
	@Autowired
	GameTableService gameTableService;
	
	@RequestMapping("admin_game_list")
	public String list(Model model) {
		List<Game> gsTop = gameService.listOrder(gameService.top);
		List<Game> gsArt = gameService.listOrder(gameService.art);
		model.addAttribute("gsTop", gsTop);
		model.addAttribute("gsArt", gsArt);
		return "admin/listGame";
	}
	
	@RequestMapping("admin_game_add")
	public String add(Game g, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		int order;
		try {
			List<Game> gs = gameService.listOrderInit(g.getUp());
			if(gs.isEmpty())
				order = 0;
			else
				order = gs.get(gs.size()-1).getGameOrder();
			order++;
			g.setGameOrder(order);
			gameService.add(g);
		} catch (Exception e) {
			return "admin/error";
		}
		
		File imageFolder = new File(new Filepath().path()+"img/gameLogo");
		File file = new File(imageFolder, g.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		ImageUtil.resizeImage(file, 400, 340, file);
		return "redirect:/admin_game_list";
	}
	
	@RequestMapping("admin_game_delete")
	public String delete(HttpSession session, int id, Model model) {
		gameService.delete(id);
		//删除对应图片
		File imageFolder = new File(new Filepath().path()+"img/gameLogo");
		File file = new File(imageFolder, id+".jpg");
		file.delete();
		return "redirect:/admin_game_list";
	}
	
	@RequestMapping("admin_game_dodelete")
	@ResponseBody
	public String dodelete(int id) {
		List<Ranks> rs = ranksService.list(id);
		List<Live> ls = liveService.list(id);
		List<GameTable> gts = gameTableService.listDesc(id);
		if(!(rs.isEmpty()&&ls.isEmpty()&&gts.isEmpty())) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("admin_game_edit")
	public String edit(Model model, int id) {
		Game g = gameService.get(id);
		model.addAttribute("g", g);
		return "admin/editGame";
	}
	
	@RequestMapping("admin_game_update")
	public String update(Game g) {
		try {
			gameService.update(g);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_game_list";
	}
	
	@RequestMapping("admin_game_down")
	public String down(int id) {
		Game g = gameService.get(id);
		int order = g.getGameOrder();
		int up = g.getUp();
		
		List<Game> gs = gameService.listOrder(up);
		Game glast = gs.get(gs.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(g.getId()!=glast.getId()) {
			//元素后一位元素往前移动1位
			Game gnext = gameService.getOrder(up, order+1).get(0);
			gnext.setGameOrder(order);
			gameService.update(gnext);
			//元素本身往后移动1位
			order++;
			g.setGameOrder(order);
			gameService.update(g);
		}
		return "redirect:/admin_game_list";
	}
	
	@RequestMapping("admin_game_up")
	public String up(int id) {
		Game g = gameService.get(id);
		int order = g.getGameOrder();
		int up = g.getUp();
		
		List<Game> gs = gameService.listOrder(up);
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			Game gpre = gameService.getOrder(up, order-1).get(0);
			gpre.setGameOrder(order);
			gameService.update(gpre);
			//元素本身往前移动1位
			order--;
			g.setGameOrder(order);
			gameService.update(g);
		}
		return "redirect:/admin_game_list";
	}
	
	@RequestMapping("admin_game_hide")
	public String hide(int id) {
		Game g = gameService.get(id);
		int order = g.getGameOrder();
		int up = g.getUp();
		
		List<Game> gs = gameService.listOrderInit(up);
		Game glast = gs.get(gs.size()-1); //获取top列表最后一位元素
		 //判读是否是最后一位元素
		if (g.getId()!=glast.getId()) {
			gs = gs.subList(g.getGameOrder()-1, gs.size());	
			for(Game ge:gs) {
				ge.setGameOrder(ge.getGameOrder()-1);
				gameService.update(ge);
			}
		}	
		g.setGameOrder(0);
		gameService.update(g);
		return "redirect:/admin_game_list";
	}
	
	@RequestMapping("admin_game_show")
	public String show(int id) {
		Game g = gameService.get(id);
		int up = g.getUp();

		List<Game> gs = gameService.listOrderInit(up);
		for(Game ge:gs) {
			ge.setGameOrder(ge.getGameOrder()+1);
			gameService.update(ge);
		}
		g.setGameOrder(1);
		gameService.update(g);
		return "redirect:/admin_game_list";
	}
	
	@RequestMapping("admin_game_image")
	public String image(int id, Model model) {
		Game g = gameService.get(id);
		model.addAttribute("g", g);
		return "admin/imageGame";
	}
	
	@RequestMapping("admin_game_imageupdate")
	public String imageUpdate(int id, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		Game g = gameService.get(id);
		
		File imageFolder = new File(new Filepath().path()+"img/gameLogo");
		File file = new File(imageFolder, g.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		ImageUtil.resizeImage(file, 400, 340, file);
		return "redirect:/admin_game_list";
	}
}
