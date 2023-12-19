package kr.couple.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.couple.web.dao.BucketListDAO;
import kr.couple.web.vo.BucketListCommVO;
import kr.couple.web.vo.BucketListVO;
import kr.couple.web.vo.Paging;
import lombok.extern.slf4j.Slf4j;

@Service("bucketListService")
@Slf4j
public class BuckListServiceImpl implements BucketListService {
	
	@Autowired
	private BucketListDAO bucketListDAO;
	
	@Override
	public Paging<BucketListVO> selectList(BucketListCommVO bv, Map<String, String> params) {
		log.info("selectList 호출 : {},{},{}", bv.getP(),  bv.getP(), bv.getB());
		Paging<BucketListVO> paging = null;
		
		//-----------------------------------------------------------------------------
		// 1. 페이지 계산을 위하여 전체개수를 구한다.
		int totalCount = bucketListDAO.selectCount(params);
		int currentPage = bv.getP();
		int sizeOfPage = bv.getS();
		int sizeOfBlock = bv.getB();
		
		// 2. 페이지 계산을 한다.
		paging = new Paging<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);
		
		// 3. 한페이지 분량의 글 목록을 가져온다.
		params.put("startNo", Integer.toString(paging.getStartNo()));
		params.put("endNo", Integer.toString(paging.getEndNo()));
		List<BucketListVO> list = bucketListDAO.selectList(params);
		
		// 5. 글목록을 페이징 객체에 넣어준다.
		paging.setList(list);
		//-----------------------------------------------------------------------------
		log.info("selectList 리턴 : {}", paging);
		
		return paging;
	}

	@Override
	public Paging<BucketListVO> selectList2(BucketListCommVO bv, Map<String, String> params) {
		log.info("selectList2 호출 : {},{},{}", bv.getP(),  bv.getP(), bv.getB());
		Paging<BucketListVO> paging = null;
		
		//-----------------------------------------------------------------------------
		// 1. 페이지 계산을 위하여 전체개수를 구한다.
		int totalCount = bucketListDAO.selectCount2(params);
		int currentPage = bv.getPb();
		int sizeOfPage = bv.getSb();
		int sizeOfBlock = bv.getBb();
		
		// 2. 페이지 계산을 한다.
		paging = new Paging<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);
		
		// 3. 한페이지 분량의 글 목록을 가져온다.
		params.put("startNo", Integer.toString(paging.getStartNo()));
		params.put("endNo", Integer.toString(paging.getEndNo()));
		List<BucketListVO> list = bucketListDAO.selectList2(params);
		
		// 5. 글목록을 페이징 객체에 넣어준다.
		paging.setList(list);
		//-----------------------------------------------------------------------------
		log.info("selectList2 리턴 : {}", paging);
		
		return paging;
	}
	// 저장
	@Override
	public boolean insert(BucketListVO bucketListVO) {
		log.info("insert 호출 : {}", bucketListVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		// 객체가 존재하며
		if(bucketListVO!=null) {
			// 이름이 있다면
			if (bucketListVO.getMember_name()!=null && bucketListVO.getMember_name().trim().length()>0) {
				bucketListDAO.insert(bucketListVO);
				result = true;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("insert 리턴 : {}", result);
		return result;
	}
	// 내용보기
	@Override
	public BucketListVO view(int id) {
		log.info("view 호출 : {}", id);
		BucketListVO bucketListVO =null;
		//-----------------------------------------------------------------------------
		// 1. 해당 번호의 글을 읽어온다.
		bucketListVO = bucketListDAO.selectById(id);
		//-----------------------------------------------------------------------------
		log.info("view 리턴 : {}", bucketListVO);
		return bucketListVO;
	}
	// 수정하기
	@Override
	public boolean update(BucketListVO bucketListVO) {
		log.info("update 호출 : {}", bucketListVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		// 객체가 존재하며
		if(bucketListVO!=null) {
			// 이름이 같다면 수정을 한다.
			BucketListVO dbVO = bucketListDAO.selectById(bucketListVO.getId());
			log.info("update 호출 : {}", bucketListVO);
			// if (dbVO!=null && dbVO.getMember_name().equals(bucketListVO.getMember_name())) { // 이름 동일할때만 수정 가능해서 주석처리함!
			 if (dbVO!=null) {
				bucketListDAO.update(bucketListVO);
				result = true;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("update 리턴 : {}", result);
		return result;
	}

	@Override
	public boolean delete(BucketListVO bucketListVO) {
		log.info("update 호출 : {}", bucketListVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		// 객체가 존재하며
		if(bucketListVO!=null) {
			// 이름이 같다면 삭제를 한다.
			BucketListVO dbVO = bucketListDAO.selectById(bucketListVO.getId());
			if (dbVO!=null && dbVO.getMember_name().equals(bucketListVO.getMember_name())) {
				// 원본 글 삭제
				bucketListDAO.delete(bucketListVO.getId());
				result = true;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("update 리턴 : {}", result);
		return result;
	}

	@Override
	public BucketListVO selectById(int id) {
		return bucketListDAO.selectById(id);
	}
	
	@Override
	public int updateComcheck(Map<String, String> reqBody) {
		
		log.info("updateComcheck 호출 : {}", reqBody);
		int id = Integer.parseInt(reqBody.get("id"));
		
		int result = 0;
		//-----------------------------------------------------------------------------
		// 객체가 존재하며
		if(reqBody!=null || !reqBody.isEmpty()) {
			// 이름이 같다면 수정을 한다.
			BucketListVO dbVO = bucketListDAO.selectById(id);
			if (dbVO!=null && dbVO.getId() == id) {
				bucketListDAO.updateComcheck(reqBody);
				result = 1;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("updateComcheck 리턴 : {}", result);
		
		return result;
	}

}
