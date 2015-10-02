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

import com.fermat.domain.Criteria;
import com.fermat.domain.NoticeBoardVO;
import com.fermat.domain.PageMaker;
import com.fermat.service.NoticeBoardService;

@Controller
@RequestMapping("/noticeboard/*")
public class NoticeBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	
	@Inject
	private NoticeBoardService service;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET(NoticeBoardVO board, Model model) throws Exception{
		logger.info("register get ..........");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(NoticeBoardVO board, RedirectAttributes rttr) throws Exception{
		logger.info("regist post..........");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/noticeboard/listAll";
	}
	
	@RequestMapping(value="/listCri", method=RequestMethod.GET)
	public void listAll(Criteria cri, Model model) throws Exception{
		logger.info("show all list page with Criteria...............");
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/removePage", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/noticeboard/listPage";
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPOST(NoticeBoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("mod post............");
		
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/noticeboard/listPage";
	}
	
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
}
