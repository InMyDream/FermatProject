package com.fermat.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fermat.domain.NoticeBoardVO;
import com.fermat.domain.PageMaker;
import com.fermat.domain.SearchCriteria;
import com.fermat.service.NoticeBoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchNoticeBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchNoticeBoardController.class);
	
	@Inject
	private NoticeBoardService service;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/removePage", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modifyPagingGET(int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPagingPOST(NoticeBoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
		logger.info(cri.toString());
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg","success");
		
		logger.info(rttr.toString());
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registGET() throws Exception{
		logger.info("regist get.......");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registPOST(NoticeBoardVO board, RedirectAttributes rttr) throws Exception{
		logger.info("regist post..........");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/sboard/list";
	}
}