package com.fermat.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fermat.domain.Criteria;
import com.fermat.domain.NoticeReplyVO;
import com.fermat.persistence.NoticeBoardDAO;
import com.fermat.persistence.NoticeReplyDAO;

@Service
public class NoticeReplyServiceImpl implements NoticeReplyService{

	@Inject
	private NoticeReplyDAO replyDAO;
	
	@Inject
	private NoticeBoardDAO boardDAO;
	
	@Transactional
	@Override
	public void addReply(NoticeReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.create(vo);
		boardDAO.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public List<NoticeReplyVO> listReply(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.list(bno);
	}

	@Override
	public void modifyReply(NoticeReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {
		// TODO Auto-generated method stub
		int bno = replyDAO.getBno(rno);
		replyDAO.delete(rno);
		boardDAO.updateReplyCnt(bno, -1);
	}

	@Override
	public List<NoticeReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.count(bno);
	}
	
	
	
}
