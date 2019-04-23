/**
 * 
 */
package com.hello.web.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.VoiceStatus;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hello.dto.FileInfo;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {
	String folder = 
			"E:\\zc-work\\learn-workspace\\security-demo\\src\\main\\java\\com\\hello\\web\\controller";
	/**
	 * 上传
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public FileInfo upload(MultipartFile file) throws Exception {
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		
		File localFile = new File(folder,new Date().getTime()+".txt"); 
		
		//file.getInputStream()
		file.transferTo(localFile);//上传
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	
	@GetMapping("/{id}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		try (InputStream input = new FileInputStream(new File(folder,id+".txt"));
				OutputStream output = response.getOutputStream();){
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			
			IOUtils.copy(input, output);
			output.flush();
		} 
				
	}
}
