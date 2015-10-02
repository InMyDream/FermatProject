package com.fermat.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fermat.domain.MessageVO;
import com.fermat.persistence.MessageDAO;
import com.fermat.persistence.PointDAO;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Inject
	private MessageDAO messageDAO;
	
	@Inject
	private PointDAO pointDAO;

	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		// TODO Auto-generated method stub
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {
		// TODO Auto-generated method stub
		messageDAO.updateState(mid);
		pointDAO.updatePoint(uid, 5);
		return messageDAO.readMessage(mid);
	}

}
