package com.example.demo.com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.com.example.demo.entity.FileDetails;
import com.example.demo.com.example.demo.entity.Files;
import com.example.demo.com.example.demo.repository.FileDetailsRepository;
import com.example.demo.com.example.demo.repository.FilesRepository;
import com.example.demo.com.example.demo.repository.LeaderBoardRepository;

@Service
public class FileService {
	
	@Autowired
	private FilesRepository filesRepo;
	
	@Autowired
	private FileDetailsRepository fileDetailsRepo;
	
	@Autowired
	private LeaderBoardService leaderService;
	
	
	//service for upload
	public HashMap<String,String> uploadFiles(String email,String component,String tag,String name,String description,MultipartFile jar,MultipartFile src,MultipartFile doc)
	{
		HashMap<String,String> file = new HashMap<String,String>();
		try
		{
			Files files = new Files(email,component,tag,name,description);
			filesRepo.save(files);
			FileDetails fileDetails = new FileDetails(files.getCid(),jar.getOriginalFilename(),jar.getContentType(),jar.getBytes(),src.getOriginalFilename(),src.getContentType(),src.getBytes(),doc.getOriginalFilename(),doc.getContentType(),doc.getBytes());
			fileDetailsRepo.save(fileDetails);
			String user_id = fileDetailsRepo.findUidFromEmail(email);
			leaderService.updateLeaderBoard(user_id, 40);
			
			file.put("status", "1");
			file.put("msg", "upload successful");
			return file;
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			file.put("status", "0");
			file.put("msg", "upload failed");
			return file;
		}
	}
	
	
	//service for search
	public List<Files> searchArtifact(String query)
	{
		return filesRepo.searchFile(query);
			
	}
	
	//service for delete
	public String deleteArtefactById(int id)
	{
		filesRepo.deleteById(id);
		return "Artefact removed";
	}
	
	//service for find artefact by email
	public List<Files> findFilesByEmailId(String email)
	{
		return filesRepo.findFilesByEmail(email);
	}
	
	//update files
	public HashMap<String, String> updateDetails(Integer id, String component,String tag,String name,String email,String description)
	{
		HashMap<String,String> res = new HashMap<String,String>();
		
		try {
		Files tempModel = filesRepo.findById(id).orElse(null);
		
			
		tempModel.setComponent(component);
		tempModel.setTag(tag);
		tempModel.setName(name);
		tempModel.setEmail(email);
		tempModel.setDescription(description);	

		Files newModel = filesRepo.save(tempModel);
		res.put("status", "1");
		
		}
		catch(Exception e) {
			res.put("status", "0");
		}
		
		return res;
		
	}	
	
	//get by id
	public FileDetails getByCid(int cid)
	{
		return fileDetailsRepo.findById(cid).orElse(null);
	}
	

}
