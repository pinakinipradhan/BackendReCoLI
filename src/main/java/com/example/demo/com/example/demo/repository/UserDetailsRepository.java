package com.example.demo.com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.com.example.demo.entity.LeaderBoard;
import com.example.demo.com.example.demo.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer>{
	
	@Query(value = "SELECT * from all_about_user WHERE uid=:uid",nativeQuery = true)
	UserDetails GetDetailsById(@Param("uid") String uid);
	
	@Query(value = "SELECT * from all_about_user WHERE email=:email",nativeQuery = true)
	UserDetails GetDetailsByEmail(@Param("email") String email);
	
	@Query(value="SELECT name from all_about_user WHERE uid=:uid",nativeQuery = true)
	String getNameByUid(@Param("uid") String uid);
	
	@Query(value="SELECT email from all_about_user WHERE uid=:uid",nativeQuery = true)
	String getEmailByUid(@Param("uid") String uid);
	
	@Query(value="SELECT uid from all_about_user WHERE email=:email",nativeQuery = true)
	String getUidByEmail(@Param("email") String email);

	
	@Query(value="SELECT image from all_about_user WHERE email=:email",nativeQuery = true)
	String GetImageUrlByEmail(@Param("email") String email);
	
}
