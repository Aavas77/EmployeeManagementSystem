package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bway.springproject.utils.FileUtil;

@Controller
public class UploadController {
	
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping("/upload")
	public String getUpload() {
		
		return "UploadForm";
	}
	
	@PostMapping("/upload")
	public String postUpload(@RequestParam MultipartFile image, Model model) {
		
		if(!image.isEmpty()) {
			
			fileUtil.fileUpload(image);
			model.addAttribute("message", "Upload Successful");
			
			return "UploadForm";
			
		}
		model.addAttribute("message", "Upload Failed");
		return "UploadForm";
	}

}
