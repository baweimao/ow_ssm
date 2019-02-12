package com.dongzhi.ow.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dongzhi.ow.pojo.Foot;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.FootService;
import com.dongzhi.ow.util.ImageUtil;
import com.dongzhi.ow.util.UploadImageFile;

@Controller
@RequestMapping("")
public class AdminController {

	@Autowired
	FootService footService;
	
	@RequestMapping("admin_page_list")
	public String list(Model model) {
		List<Foot> fs = footService.list();
		model.addAttribute("fs", fs);
		return "admin/listPage";
	}
	
	@RequestMapping("admin_foot_edit")
	public String edit(int id, Model model) {
		Foot f = footService.get(id);
		model.addAttribute("f", f);
		return "admin/editFoot";
	}
	
	@RequestMapping("admin_foot_update")
	public String update(Foot f) {
		try {
			footService.update(f);
		} catch (Exception e) {
			return "admin/error";
		}
		return "redirect:/admin_page_list";
	}
	
	@RequestMapping("admin_backgroundimage_edit")
	public String backgroundImageEdit() {
		return "admin/backgroundImage";
	}
	
	@RequestMapping("admin_backgroundimage_update")
	public String backgroundImageUpdate(HttpSession session, UploadImageFile uploadImageFile) throws IOException {
		File imageFolder = new File(session.getServletContext().getRealPath("img/web"));
		File file = new File(imageFolder, "backgroundimage.jpg");
		if(!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);	
		ImageIO.write(img, "jpg", file);
		return "redirect:/admin_page_list";
	}

}
