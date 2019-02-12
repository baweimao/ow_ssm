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
import com.dongzhi.ow.pojo.Ranks;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.GameService;
import com.dongzhi.ow.service.GameTableService;
import com.dongzhi.ow.service.RanksService;
import com.dongzhi.ow.util.Filepath;
import com.dongzhi.ow.util.ImageUtil;
import com.dongzhi.ow.util.UploadImageFile;

@Controller
@RequestMapping("")
public class RanksController {

	@Autowired
	RanksService ranksService;
	@Autowired
	GameTableService gameTableService;
	@Autowired
	GameService gameService;
	
	@RequestMapping("admin_ranks_list")
	public String list(int gid, Model model) {
		List<Ranks> rs = ranksService.list(gid);
		Game g = gameService.get(gid);
		model.addAttribute("g", g);
		model.addAttribute("rs", rs);
		model.addAttribute("gid", gid);
		return "admin/listRanks";
	}
	
	@RequestMapping("admin_ranks_add")
	public String add(Ranks r, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		int gid = r.getGid();
		int order;
		try {
			List<Ranks> rs = ranksService.listInit(gid);
			if(rs.isEmpty())
				order = 0;
			else
				order = rs.get(rs.size()-1).getRanksOrder();
			order++;
			r.setRanksOrder(order);
			ranksService.add(r);
		} catch (Exception e) {
			return "admin/error";
		}
		
		File imageFolder = new File(new Filepath().path()+"img/ranksLogo");
		File file = new File(imageFolder, r.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		ImageUtil.resizeImage(file, 150, 150, file);
		return "redirect:/admin_ranks_list?gid="+gid;
	}
	
	@RequestMapping("admin_ranks_delete")
	public String delete(HttpSession session, int id, Model model) {
		Ranks r = ranksService.get(id);
		int gid = r.getGid();
		ranksService.delete(id);
		//删除对应图片
		File imageFolder = new File(new Filepath().path()+"img/ranksLogo");
		File file = new File(imageFolder, id+".jpg");
		file.delete();
		return "redirect:/admin_ranks_list?gid="+gid;
	}
	
	@RequestMapping("admin_ranks_dodelete")
	@ResponseBody
	public String dodelete(int id) {
		Ranks r = ranksService.get(id);
		int gid = r.getGid();
		List<GameTable> gts = gameTableService.listDesc(gid);
		if(!gts.isEmpty()) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("admin_ranks_edit")
	public String edit(Model model, int id) {
		Ranks r = ranksService.get(id);
		Game g = gameService.get(r.getGid());
		List<Game> gs = gameService.list();
		model.addAttribute("gs", gs);
		model.addAttribute("g", g);
		model.addAttribute("r", r);
		return "admin/editRanks";
	}
	
	@RequestMapping("admin_ranks_update")
	public String update(Ranks r, int oldGid) {
		try {
			//判断是否更换分类
			if(r.getGid()!=oldGid) {
				int order = r.getRanksOrder();
				
				//旧列表元素之后均往前移1位
				Ranks rod = ranksService.getOrder(oldGid,order).get(0); //获取更新前的元素
				List<Ranks> rods = ranksService.list(oldGid);	//获取旧列表
				Ranks rodlast = rods.get(rods.size()-1);	//获取旧列表最后一位元素	
				if (rod.getId()!= rodlast.getId()) //判读是否是最后一位元素
					rods = rods.subList(rod.getRanksOrder()-1, rods.size());	
				else
					rods = rods.subList(rods.size(), rods.size());
				
				for(Ranks ro:rods) {
					ro.setRanksOrder(ro.getRanksOrder()-1);
					ranksService.update(ro);
				}
				
				//获取新列表序号
				int gid = r.getGid();
				List<Ranks> rns = ranksService.list(gid);
				if(rns.isEmpty())
					order = 0;
				else
					order = rns.get(rns.size()-1).getRanksOrder();
				order++;
				r.setRanksOrder(order);
			}
			ranksService.update(r);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_ranks_list?gid="+oldGid;
	}
	
	@RequestMapping("admin_ranks_down")
	public String down(int id) {
		Ranks r = ranksService.get(id);
		int gid = r.getGid();
		int order = r.getRanksOrder();
		
		List<Ranks> rs = ranksService.list(gid);
		Ranks rlast = rs.get(rs.size()-1); //获取最后一位元素
		//判读是否是最后一位元素
		if(r.getId()!=rlast.getId()) {
			//元素后一位元素往前移动1位
			Ranks rnext = ranksService.getOrder(gid, order+1).get(0);
			rnext.setRanksOrder(order);
			ranksService.update(rnext);
			//元素本身往后移动1位
			order++;
			r.setRanksOrder(order);
			ranksService.update(r);
		}
		return "redirect:/admin_ranks_list?gid="+gid;
	}
	
	@RequestMapping("admin_ranks_up")
	public String up(int id) {
		Ranks r = ranksService.get(id);
		int gid = r.getGid();
		int order = r.getRanksOrder();
		
		List<Ranks> rs = ranksService.list(gid);
		//判读是否是第一位元素
		if(order > 1) {
			//元素前一位元素往后移动1位
			Ranks rpre = ranksService.getOrder(gid, order-1).get(0);
			rpre.setRanksOrder(order);
			ranksService.update(rpre);
			//元素本身往前移动1位
			order--;
			r.setRanksOrder(order);
			ranksService.update(r);
		}
		return "redirect:/admin_ranks_list?gid="+gid;
	}
	
	@RequestMapping("admin_ranks_hide")
	public String hide(int id) {
		Ranks r = ranksService.get(id);
		int gid = r.getGid();
		int order = r.getRanksOrder();
		
		List<Ranks> rs = ranksService.listInit(gid);
		Ranks rlast = rs.get(rs.size()-1); //获取top列表最后一位元素
		 //判读是否是最后一位元素
		if (r.getId()!=rlast.getId()) {
			rs = rs.subList(r.getRanksOrder()-1, rs.size());	
			for(Ranks re:rs) {
				re.setRanksOrder(re.getRanksOrder()-1);
				ranksService.update(re);
			}
		}
		r.setRanksOrder(0);
		ranksService.update(r);
		return "redirect:/admin_ranks_list?gid="+gid;
	}
	
	@RequestMapping("admin_ranks_show")
	public String show(int id) {
		Ranks r = ranksService.get(id);
		int gid = r.getGid();
		
		List<Ranks> rs = ranksService.listInit(gid);
		for(Ranks re:rs) {
			re.setRanksOrder(re.getRanksOrder()+1);
			ranksService.update(re);
		}
		r.setRanksOrder(1);
		ranksService.update(r);
		return "redirect:/admin_ranks_list?gid="+gid;
	}
	
	@RequestMapping("admin_ranks_image")
	public String image(int id, Model model) {
		Ranks r = ranksService.get(id);
		model.addAttribute("r", r);
		return "admin/imageRanks";
	}
	
	@RequestMapping("admin_ranks_imageupdate")
	public String imageUpdate(int id, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		Ranks r = ranksService.get(id);
		int gid = r.getGid();
		
		File imageFolder = new File(new Filepath().path()+"img/ranksLogo");
		File file = new File(imageFolder, r.getId()+".jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		ImageUtil.resizeImage(file, 150, 150, file);
		return "redirect:/admin_ranks_list?gid="+gid;
	}
}
