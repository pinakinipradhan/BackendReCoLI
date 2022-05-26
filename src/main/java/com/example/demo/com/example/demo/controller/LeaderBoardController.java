package com.example.demo.com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.com.example.demo.service.LeaderBoardService;

@CrossOrigin(origins = "*")
@RestController
public class LeaderBoardController {
	
	@Autowired
	private LeaderBoardService leaderBoardService;
	
	@GetMapping("/leaderBoard")
	public ArrayList<HashMap<String, String>> getList()
	{
		return leaderBoardService.topFive();
	}
	

}
