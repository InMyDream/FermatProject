package com.fermat.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fermat.domain.Criteria;
import com.fermat.domain.NoticeReplyVO;

@Repository
public class NoticeReplyDAOImpl implements NoticeReplyDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.fermat.mapper.NoticeReplyMapper";
	
	@Override
	public List<NoticeReplyVO> list(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".list", bno);
	}

	@Override
	public void create(NoticeReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace+".create", vo);
		
	}

	@Override
	public void update(NoticeReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace+".delete", rno);
	}

	@Override
	public List<NoticeReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace+".listPage", paramMap);
	}

	@Override
	public int count(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".count", bno);
	}

	@Override
	public int getBno(Integer rno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".getBno", rno);
	}
	
	
	
	
}
