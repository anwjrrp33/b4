package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.FreeBoard;
import com.example.domain.FreeBoardReply;
import com.example.persistence.FreeBoardReplyRepository;
import com.example.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {
	
	@Autowired
	private FreeBoardRepository boardRepo;
	@Autowired
	private FreeBoardReplyRepository replyRepo;
	
	@Test
	public void testList3(){
		
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		
		boardRepo.getPage(page).forEach(arr -> 
		  log.info(Arrays.toString(arr)));
	}
	
//	@Test
//	public void testList52(){
//		
//		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
//		
//		boardRepo.getPage52(page).forEach(arr -> 
//		  log.info(Arrays.toString(arr)));
//	}
//	
//	@Test
//	public void testRead2(){
//		
//		Optional<FreeBoard> result = boardRepo.findById(200L);
//		
//		FreeBoard board = result.get();
//		
//		List<FreeBoardReply> replies = replyRepo.getReply(board);
//		
//		replies.forEach(reply  -> log.info("REPLY: "+reply));
//	}
	
	@Test
	public void testPaging() {
		
		Pageable page = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");
		
		List<FreeBoard> result = boardRepo.findByBnoGreaterThan(0L, page);
		
		log.info(result.toString());
	}
	
	@Test
	public void testRead() {
		
		boardRepo.findById(1L).ifPresent(board -> {
			
			log.info(board.toString());
			
			List<FreeBoardReply> list = board.getReplies();
		});
	}
	
	@Test
	public void testInsertReply() {
		
		FreeBoardReply reply = new FreeBoardReply();
		
		Long bno = 1L;
		
		FreeBoard board = new FreeBoard();
		board.setBno(bno);
		
		reply.setReply("RE...");
		reply.setReply("replyer");
		reply.setBoard(board);
		
		replyRepo.save(reply);
	}
	
	@Test
	public void testInsertDummy() {
		
		for(int i = 2; i < 100; i++) {
		
			FreeBoard board = new FreeBoard();
			board.setTitle("title..." + i);
			board.setContent("content..." + i);
			board.setWriter("user1" + i);
		
			boardRepo.save(board);
		}
	}
}
