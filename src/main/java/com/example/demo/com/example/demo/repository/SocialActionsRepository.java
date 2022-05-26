package com.example.demo.com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.com.example.demo.entity.SocialActions;

@Repository
public interface SocialActionsRepository extends JpaRepository<SocialActions,Integer>{

	@Query(value="SELECT COUNT(*) FROM social_actions WHERE cid=:cid AND action=:action",nativeQuery=true)
	Integer CountSocialActions(@Param("cid") int cid,@Param("action") String action);

	@Query(value="SELECT COUNT(*) FROM social_actions WHERE uid=:uid AND action=:action",nativeQuery=true)
	Integer findCountOfSocialActionByUser(@Param("uid") String uid,@Param("action") String action);

	@Query(value = "select cid from social_actions WHERE action='Download' group by cid ORDER BY COUNT(*) DESC LIMIT :n,4;",nativeQuery = true)
	ArrayList<Integer> findMostDownloaded(@Param("n") int n);


	@Query(value = "select cid from social_actions WHERE action='Like' group by cid ORDER BY COUNT(*) DESC LIMIT :n,4;",nativeQuery = true)
	ArrayList<Integer> findMostLiked(@Param("n") int n);

	//@Query(value="SELECT uid from social_actions WHERE action='Comment' AND cid=:cid",nativeQuery = true)
	//ArrayList<String> getUidOfAPostByCid(@Param("cid") int cid);

	@Query(value="SELECT * from social_actions WHERE action='Comment' AND cid=:cid",nativeQuery = true)
	List<SocialActions> getAllFromSocialTableForComments(@Param("cid") int cid);

	@Query(value="SELECT email from files WHERE cid=:cid",nativeQuery = true)
	String getEmailFromCid(@Param("cid") int cid);

	@Query(value="SELECT uid from all_about_user WHERE email=:email",nativeQuery = true)
	String getUidFromEmail(@Param("email") String email);


	@Query(value="SELECT cid from social_actions WHERE uid=:uid AND action=:action",nativeQuery = true)
	HashSet<Integer> getUniqueCid(@Param("uid") String uid,@Param("action") String action);

	@Query(value="SELECT count(*) from social_actions WHERE cid=:cid AND action=:action AND uid=:uid",nativeQuery = true)
	Integer findCountByActionOnComponent(@Param("cid") int cid,@Param("action") String action,@Param("uid") String uid);
	
	@Query(value="SELECT comment from social_actions WHERE id=:id AND action=Comment",nativeQuery = true)
	SocialActions getAllComments(@Param("id") int id);
	
	//for list view
	@Query(value = "select cid from social_actions WHERE action='Download' group by cid ORDER BY COUNT(*) DESC LIMIT :n,15;",nativeQuery = true)
	ArrayList<Integer> findMostDownloadedListView(@Param("n") int n);


	@Query(value = "select cid from social_actions WHERE action='Like' group by cid ORDER BY COUNT(*) DESC LIMIT :n,15;",nativeQuery = true)
	ArrayList<Integer> findMostLikedListview(@Param("n") int n);

}