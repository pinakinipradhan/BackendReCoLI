package com.example.demo.com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.com.example.demo.entity.LeaderBoard;
import com.example.demo.com.example.demo.repository.LeaderBoardRepository;
import com.example.demo.com.example.demo.repository.UserDetailsRepository;

@Service
public class LeaderBoardService {

	@Autowired
	private LeaderBoardRepository leaderBoardRepo;

	@Autowired
	private UserDetailsRepository userRepo;

	public void updateLeaderBoard(String uid,int points)
	{
		//System.out.print("leaderboarduid"+uid);
		LeaderBoard leader = leaderBoardRepo.findByuid(uid);
		int point = leader.getPoints();
		point = point+points;
		leader.setPoints(point);
		leaderBoardRepo.save(leader);

	}

	//get Top 5
	public ArrayList<HashMap<String,String>> topFive()
	{
		ArrayList<LeaderBoard> leaderList = leaderBoardRepo.GetTopFive();

		ArrayList<HashMap<String,String>> listsofhash = new ArrayList<HashMap<String,String>>();

		for(int i=0;i<leaderList.size();i++)
		{
			HashMap<String,String> map = new HashMap<String,String>();
			String name = userRepo.getNameByUid(leaderList.get(i).getUid());
			map.put("name", name);
			String points = Integer.toString(leaderList.get(i).getPoints());
			map.put("points", points);
			String email = userRepo.getEmailByUid(leaderList.get(i).getUid());
			map.put("email", email);
			String imageurl = leaderBoardRepo.GetImageUrlByUid(leaderList.get(i).getUid());
			map.put("imageUrl", imageurl);
			listsofhash.add(map);
		}


		return listsofhash;

	}


}