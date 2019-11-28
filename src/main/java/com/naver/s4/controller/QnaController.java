package com.naver.s4.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.naver.s4.model.BoardQnaVO;
import com.naver.s4.model.BoardVO;
import com.naver.s4.model.FilesVO;
import com.naver.s4.service.BoardQnaService;
import com.naver.s4.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {

	@Inject
	private BoardQnaService boardQnaService;
	//
	
	@Value("#{db['qna']}")
	private String board;
	
	@ModelAttribute("board")
	public String getBoard() {
		return board;
	}
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(FilesVO filesVO) throws Exception  {
		filesVO = boardQnaService.fileSelect(filesVO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("file", filesVO);
		
		mv.setViewName("fileDown");
		
		return mv;
	}
	
	
	
	
	@PostMapping("fileDelete")
	public ModelAndView fileDelete(FilesVO filesVO) throws Exception {
		
		int result = boardQnaService.fileDelete(filesVO);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("common/common_ajaxResult");
		mv.addObject("result", result);
		
		return mv;
		
	}

	
	
	//
	@RequestMapping("qnaList")
	public ModelAndView boardList(Pager pager) throws Exception {
		List<BoardVO> ar =  boardQnaService.boardList(pager);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		

		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	@RequestMapping(value = "qnaWrite", method = RequestMethod.GET)
	public ModelAndView boardWrite() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/boardWrite");
		
		return mv;
	}
	
	@RequestMapping(value = "qnaWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(BoardVO boardVO, HttpSession session, MultipartFile [] file) throws Exception {
		int result = boardQnaService.boardWrite(boardVO, file, session);
	
		ModelAndView mv = new ModelAndView();
		String msg = "�옉�꽦 �떎�뙣";
		
		if(result > 0) {
			msg = "�옉�꽦 �꽦怨�";
			mv.setViewName("redirect:./qnaList");
		} else {
			mv.addObject("msg", msg);
			mv.addObject("path", "./qnaList");
			mv.setViewName("common/common_result");
		}
		
		return mv;
	}
	
	@RequestMapping("qnaDelete") 
	public ModelAndView boardDelete(BoardVO boardVO) throws Exception {
		int result = boardQnaService.boardDelete(boardVO);
		
		ModelAndView mv = new ModelAndView();
		String msg = "�궘�젣 �떎�뙣";
		
		if(result > 0) {
			msg = "�궘�젣 �꽦怨�";
			mv.setViewName("redirect:./qnaList");
		} else {
			mv.addObject("msg", msg);
			mv.addObject("path", "./qnaList");
			mv.setViewName("common/common_result");
		}
		
		return mv;
	}
	
	
	@RequestMapping("qnaSelect")
	public ModelAndView boardSelect(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dto", (BoardQnaVO)boardQnaService.boardSelect(boardVO)); 
		mv.setViewName("board/boardSelect");
		
		return mv;
	}
	
	
	@RequestMapping(value = "qnaUpdate", method = RequestMethod.GET)
	public ModelAndView boardUpdate(int num) throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(num);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dto", boardQnaService.boardSelect(boardVO));
		
		mv.setViewName("board/boardUpdate");
		
		return mv;
	}
	
	@RequestMapping(value = "qnaUpdate", method = RequestMethod.POST)
	public ModelAndView boardUpdate(BoardVO boardVO, MultipartFile [] file, HttpSession session) throws Exception {
		int result = boardQnaService.boardUpdate(boardVO, file, session);
		ModelAndView mv = new ModelAndView();
		String msg = "�닔�젙 �떎�뙣";
		
		if(result > 0) {
			msg = "�닔�젙 �꽦怨�";
			mv.setViewName("redirect:./qnaList");
		} else {
			mv.addObject("msg", msg);
			mv.addObject("path", "qna/qnaList");
			mv.setViewName("common/common_result");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "qnaReply", method = RequestMethod.GET)
	public ModelAndView boardReply(int num) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("num", num);
		mv.addObject("board", "qna");
		mv.setViewName("board/boardReply");
		
		return mv;
	}
	
	
	@RequestMapping(value = "qnaReply", method = RequestMethod.POST)
	public ModelAndView boardReply(BoardVO boardVO) throws Exception {
		int result = boardQnaService.boardReply(boardVO);
		
		ModelAndView mv = new ModelAndView();
		String msg = "�뙎湲� 異붽� �떎�뙣";
		
		if(result > 0) {
			msg = "�뙎湲� 異붽� �꽦怨�";
			mv.setViewName("redirect:./qnaList");
		} else {
			mv.addObject("msg", msg);
			mv.addObject("path", "qna/qnaList");
			mv.setViewName("common/common_result");
		}
		
		return mv;

	}

}
