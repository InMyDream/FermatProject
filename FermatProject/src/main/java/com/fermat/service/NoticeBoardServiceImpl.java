package com.fermat.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.fermat.domain.Criteria;
import com.fermat.domain.NoticeBoardVO;
import com.fermat.domain.SearchCriteria;
import com.fermat.persistence.NoticeBoardDAO;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService{

	@Inject
	private NoticeBoardDAO dao;
	
	@Transactional
	@Override
	public void regist(NoticeBoardVO board) throws Exception {
		// TODO Auto-generated method stub
		dao.create(board);
		
		String[] files = board.getFiles();
		
		if(files == null){
			return;
		}
		
		for(String fileName : files){
			dao.addAttach(fileName);
		}
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public NoticeBoardVO read(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(NoticeBoardVO board) throws Exception {
		// TODO Auto-generated method stub
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(bno);
	}

	@Override
	public List<NoticeBoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public List<NoticeBoardVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.countPaging(cri);
	}

	@Override
	public List<NoticeBoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}
	
	
	
	
	
	
}
