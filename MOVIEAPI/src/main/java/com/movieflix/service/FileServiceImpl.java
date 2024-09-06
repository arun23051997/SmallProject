package com.movieflix.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{

	@Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        		System.out.println("orginal file name : " + fileName);
        String filePath = path + File.separator + fileName;
        System.out.println("orginal file name : " + filePath);
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
            System.out.println("inside if condition");
        }

          Files.copy(file.getInputStream(), Paths.get(filePath));
          		
        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
	 
}
