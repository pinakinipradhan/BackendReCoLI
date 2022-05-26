package com.example.demo.com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.com.example.demo.entity.LeaderBoard;

@Repository
public interface LeaderBoardRepository extends JpaRepository<LeaderBoard,Integer> {
	
	@Query(value="SELECT * FROM leader_board WHERE uid=:uid",nativeQuery = true)
	LeaderBoard findLeaderBoardDetailsByUid(@Param("uid") String uid);
	
	public LeaderBoard findByuid(String uid);
	
	//Get top 5 from leader_board

	@Query(value="SELECT * FROM leader_board ORDER BY points DESC LIMIT 5",nativeQuery = true)
	ArrayList<LeaderBoard> GetTopFive();
	
	//get points by uid
	@Query(value="SELECT points FROM leader_board WHERE uid=:uid",nativeQuery=true)
	Integer GetPointsFromUid(@Param("uid") String uid);
	
	//get image string by uid
	@Query(value="SELECT image FROM all_about_user WHERE uid=:uid",nativeQuery = true)
	String GetImageUrlByUid(@Param("uid") String uid);

}
