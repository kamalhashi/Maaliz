package com.hashi.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class MultipartToFile {

	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
		File convFile = new File( multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	public static List<File> multipartsToFiles(MultipartFile[] multiparts) throws IllegalStateException, IOException 
	{ 
		List<File> files = new ArrayList<>();
		for (MultipartFile multipart: multiparts){
			File convFile = new File( multipart.getOriginalFilename());
			multipart.transferTo(convFile);
			files.add(convFile);
		}
		return files;
	}

}
