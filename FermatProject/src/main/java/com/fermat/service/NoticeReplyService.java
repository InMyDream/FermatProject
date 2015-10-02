package com.fermat.service;

import java.util.List;

import com.fermat.domain.Criteria;
import com.fermat.domain.NoticeReplyVO;

public interface NoticeReplyService {
	
	public void addReply(NoticeReplyVO vo) throws Exception;
	
	public List<NoticeReplyVO> listReply(Integer bno) throws Exception;
	
	public void modifyReply(NoticeReplyVO vo) throws Exception;
	
	public void removeReply(Integer rno) throws Exception;
	
	public List<NoticeReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
}
