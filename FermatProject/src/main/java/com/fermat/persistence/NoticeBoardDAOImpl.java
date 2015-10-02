package com.fermat.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fermat.domain.Criteria;
import com.fermat.domain.NoticeBoardVO;
import com.fermat.domain.SearchCriteria;

@Repository
public class NoticeBoardDAOImpl implements NoticeBoardDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.fermat.mapper.NoticeBoardMapper";
	
	@Override
	public void create(NoticeBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace+".create", vo);
	}

	@Override
	public NoticeBoardVO read(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".read", bno);
	}

	@Override
	public void update(NoticeBoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace+".delete", bno);
	}

	@Override
	public List<NoticeBoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".listAll");
	}

	@Override
	public List<NoticeBoardVO> listPage(int page) throws Exception {
		// TODO Auto-generated method stub
		if(page <= 0){
			page = 1;
		}
		
		page = (page - 1) * 10;

		return session.selectList(namespace+".listPage",page);
	}

	@Override
	public List<NoticeBoardVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".listCriteria",cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".countPaging", cri);
	}

	@Override
	public List<NoticeBoardVO> listSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".listSearchCount", cri);
	}

	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);
		
		session.update(namespace+".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace+".updateViewCnt", bno);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace+".addAttach", fullName);
	}
	
	
	
	
}
