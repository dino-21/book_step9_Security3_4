package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper mapper;

	// 내가 가지고있는 tbl_board있는 bno값 5개 저장
	private Long[] bnoArr = { 5136L, 5135L, 5134L, 5133L, 5132L };

	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]); // i%5 => 0,1,2,3,4
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("replyer");
			mapper.insert(vo);

		});
	}
	
	@Test
	public void testRead() {
		
		ReplyVO vo = mapper.read(3L);  //rno (댓글 번호) 3번만 가져와
		log.info(vo);
	}
	
	
	@Test
	public void testDelete() {
		
		int result = mapper.delete(3L);  //3번댓글 삭제
		log.info(result);
	}
	
	
	@Test
	public void testUpdate() {
		
		ReplyVO vo = new ReplyVO();
		
		vo.setRno(1L);
		vo.setReply("1번만수정");
		
		mapper.update(vo);
		
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria(2,5);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply->log.info(reply));
	}
	
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria(4, 5);
			
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 5136L);
		replies.forEach(reply -> log.info(reply));
	}
	
	
	@Test   //페이징처리 보기위해서
	public void testInsert2() {
		IntStream.rangeClosed(1, 20).forEach(i->{
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[0]);  //i%5 => 0,1,2,3,4
			vo.setReply("페이징 테스트"+i);
			vo.setReplyer("페이징");
			mapper.insert(vo);
			
		});
	}
	
	
	@Test
	public void testCount() {
		int count = mapper.getCountByBno(5136L);
		log.info("count : " + count);
	}
		
}
