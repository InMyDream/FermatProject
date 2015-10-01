package com.fermat.service;

import java.util.List;

import com.fermat.domain.Criteria;
import com.fermat.domain.NoticeBoardVO;

public interface NoticeBoardService {
	
	public void regist(NoticeBoardVO board) throws Exception;
	
	public NoticeBoardVO read(Integer bno) throws Exception;
	
	public void modify(NoticeBoardVO board) throws Exception;
	
	public void remove(Integer bno) throws Exception;
	
	public List<NoticeBoardVO> listAll() throws Exception;
	
	public List<NoticeBoardVO> listCriteria(Criteria cri) throws Exception;

}
