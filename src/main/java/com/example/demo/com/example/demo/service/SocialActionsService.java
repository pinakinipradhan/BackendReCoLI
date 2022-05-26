package com.example.demo.com.example.demo.service;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.com.example.demo.entity.SocialActions;
import com.example.demo.com.example.demo.repository.SocialActionsRepository;

@Service
public class SocialActionsService {

	@Autowired
	private SocialActionsRepository socialRepo;

	@Autowired
	private LeaderBoardService leaderBoardService;


	public HashMap<String,String> getSocialActions(SocialActions socialActions)
	{

		HashMap<String,String> res = new HashMap<String,String>();
		String action = socialActions.getAction();
		if(action.equals("Comment"))
		{
			String uid = socialActions.getUid();
			int cid = socialActions.getCid();

			String uploader_email = socialRepo.getEmailFromCid(cid);
			String uploader_uid = socialRepo.getUidFromEmail(uploader_email);
			String actions = socialActions.getAction();
			String comment = socialActions.getComment();
			String date = socialActions.getDate();
			
			if(comment.length()>0)
			{
				SocialActions model = new SocialActions(uid,cid,actions,comment,date);
				socialRepo.save(model);
				leaderBoardService.updateLeaderBoard(uploader_uid,10);
			}

			int count = socialRepo.CountSocialActions(cid, actions);
			res.put("count",Integer.toString(count));

			return res;

			}

		else if(action.equals("Download"))
		{
			SocialActions social = new SocialActions(socialActions.getUid(),socialActions.getCid(),socialActions.getAction(),"null",socialActions.getDate());
			String actions = socialActions.getAction();

			int cid = socialActions.getCid();
			String uploader_email = socialRepo.getEmailFromCid(cid);
			String uploader_uid = socialRepo.getUidFromEmail(uploader_email);
			//check if download exists
			int download_count = socialRepo.findCountByActionOnComponent(cid, "Download", social.getUid());
			if(download_count == 0)
			{
				leaderBoardService.updateLeaderBoard(uploader_uid,40);
				socialRepo.save(social);
			}
			int count = socialRepo.CountSocialActions(cid,actions);
			res.put("count",Integer.toString(count));
			return res;
		}
		else if(action.equals("Like"))
		{
			SocialActions social = new SocialActions(socialActions.getUid(),socialActions.getCid(),socialActions.getAction(),"null",socialActions.getDate());
			String actions = socialActions.getAction();
			int cid = socialActions.getCid();
			String uploader_email = socialRepo.getEmailFromCid(cid);
			String uploader_uid = socialRepo.getUidFromEmail(uploader_email);

			//check if like exists
			int like_count = socialRepo.findCountByActionOnComponent(cid, "Like", social.getUid());
			if(like_count == 0)
			{
				leaderBoardService.updateLeaderBoard(uploader_uid,10);
				socialRepo.save(social);
			}
			int count = socialRepo.CountSocialActions(cid,actions);
			res.put("count",Integer.toString(count));
			return res;
		}
		else
		{
			SocialActions social = new SocialActions(socialActions.getUid(),socialActions.getCid(),socialActions.getAction(),"null",socialActions.getDate());
			String actions = socialActions.getAction();
			int cid = socialActions.getCid();
			String uploader_email = socialRepo.getEmailFromCid(cid);
			String uploader_uid = socialRepo.getUidFromEmail(uploader_email);
			leaderBoardService.updateLeaderBoard(uploader_uid,10);
			socialRepo.save(social);
			int count = socialRepo.CountSocialActions(cid, actions);
			res.put("count",Integer.toString(count));
			return res;

		}



	}

	//find Total Likes
	public Integer getLikesCount(int cid)
	{
		return socialRepo.CountSocialActions(cid,"Like");

	}

	//find Total shares
	public Integer getSharesCount(int cid)
	{
		return socialRepo.CountSocialActions(cid,"Share");

	}

	//find Total Comments
	public Integer getCommentsCount(int cid)
	{
		return socialRepo.CountSocialActions(cid,"Comment");

	}

	//find Total Download
	public Integer getDownloadsCount(int cid)
	{
		return socialRepo.CountSocialActions(cid,"Download");

	}
	
	//update comment
	public HashMap<String,String> updateComment(int id,String comment)
	{
		SocialActions optionalComment = socialRepo.findById(id).orElse(null);
		HashMap<String,String> hash = new HashMap<String,String>();
		try
		{
			optionalComment.setComment(comment);
			hash.put("msg", "comment updated");
			return hash;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			hash.put("msg", "upate failed");
			return hash;
		}
		
		
	}
	
	//delete a comment
	public HashMap<String,String> delete(int id)
	{
		HashMap<String,String> hash = new HashMap<String,String>();
		socialRepo.deleteById(id);;
		hash.put("msg", "Comment deleted");
		return hash;
		
	}


}