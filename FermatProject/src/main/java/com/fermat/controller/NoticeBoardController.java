package com.fermat.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fermat.domain.NoticeBoardVO;
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
	
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		logger.info("show all list...............");
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception{
		service.remove(bno);
		
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/noticeboard/listAll";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(NoticeBoardVO board, RedirectAttributes rttr) throws Exception{
		logger.info("mod post............");
		
		service.modify(board);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/noticeboard/listAll";
	}
}
