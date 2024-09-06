package com.movieflix.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file/")
public class FileController {
	
	
	@Autowired
	private  FileService fileService;
	
//    public FileController(FileService fileService) {
//        this.fileService = fileService;
//    }

    @Value("${project.poster}")
    private String path;
    
    //@PostMapping(value = "/upload") or
   @PostMapping("/upload")								//@RequestParam and @RequestBody also working
    public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {
    	System.out.println("value of path :" + path);
        String uploadedFileName = fileService.uploadFile(path, file);
        return ResponseEntity.ok("File uploaded : " + uploadedFileName);
//        return "File uploaded : " + uploadedFileName;
    }

   	//We have two way 39 and 48 we can  anything one same result
    @GetMapping(value = "image/{fileName}")
    public void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        InputStream resourceFile = fileService.getResourceFile(path, fileName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }
}
