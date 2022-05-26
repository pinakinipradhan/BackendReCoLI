package com.example.demo.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.com.example.demo.entity.FileDetails;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails,Integer> {
	
	@Query(value="SELECT * FROM file_details WHERE cid=:cid",nativeQuery=true)
	FileDetails findByCid(@Param("cid") int cid);

	@Query(value="SELECT uid from all_about_user WHERE email=:email",nativeQuery = true)
	String findUidFromEmail(@Param("email") String email);
}
