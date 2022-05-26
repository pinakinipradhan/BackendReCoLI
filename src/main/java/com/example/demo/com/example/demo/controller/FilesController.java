package com.example.demo.com.example.demo.controller;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.com.example.demo.entity.FileDetails;
import com.example.demo.com.example.demo.entity.Files;
import com.example.demo.com.example.demo.service.FileService;

@CrossOrigin(origins = "*")
@RestController
public class FilesController {
	
	@Autowired
	private FileService fileService;
	
	//API to upload all details
	@PostMapping("/upload")
	public HashMap<String, String> uploadDetails(@RequestParam("email") String email,@RequestParam("component") String component,@RequestParam("tag") String tag,@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("jar") MultipartFile jar,@RequestParam("src") MultipartFile src,@RequestParam("document") MultipartFile doc)
	{
		return fileService.uploadFiles(email, component, tag, name, description, jar, src, doc);
	}
	
	//API to search list of artifacts
	@GetMapping("/search/{query}")
	public List<Files> getSeach(@PathVariable String query)
	{
		return fileService.searchArtifact(query);
	}
	
	//API to get artefacts by email
	@GetMapping("/search/email/{email}")
	public List<Files> getFileByEid(@PathVariable String email)
	{
		return fileService.findFilesByEmailId(email);
	}
			
	
	//update artefact
	@PutMapping("/updateFileDetails")
	public HashMap<String,String> update(@RequestParam("cid") int cid,@RequestParam("component") String component,@RequestParam("tag") String tag,@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("description") String description)
	{
		return fileService.updateDetails(cid, component, tag, name, email, description);
	}
	
	//delete 
	@GetMapping("/deleteFiles/cid/{cid}")
	public String deleteFiles(@PathVariable int cid)
	{
		return fileService.deleteArtefactById(cid);
	}
		
	
	
	
	//Download API'S
	@GetMapping("/artifacts/download/jar/id/{id}")
	public ResponseEntity<ByteArrayResource> downloaJarById(@PathVariable int id)
	{
		FileDetails doc = fileService.getByCid(id);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getJartype()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getJarname() + "\"")
				.body(new ByteArrayResource(doc.getJar()));
		//return new ByteArrayResource(doc.getData());
	}
	
	
	//download project source code by id
	@GetMapping("/artifacts/download/source/id/{id}")
	public ResponseEntity<ByteArrayResource> downloaSrcById(@PathVariable int id)
	{
		FileDetails doc = fileService.getByCid(id);
		return ResponseEntity.ok()
				
				.contentType(MediaType.parseMediaType(doc.getSrctype()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getSrcname() + "\"")
				.body(new ByteArrayResource(doc.getSrc()));
		//return new ByteArrayResource(doc.getData());
	}
	
	//download project documentation by id
	@GetMapping("/artifacts/download/document/id/{id}")
	public ResponseEntity<ByteArrayResource> downloaDocumentById(@PathVariable int id)
	{
		FileDetails doc = fileService.getByCid(id);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDoctype()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getDocname() + "\"")
				.body(new ByteArrayResource(doc.getDoc()));
		//return new ByteArrayResource(doc.getData());
	}
	
	

	

}
