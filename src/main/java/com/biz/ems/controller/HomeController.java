package com.biz.ems.controller;

import com.biz.ems.model.EmsVO;
import com.biz.ems.service.EmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class HomeController {

	@Autowired
	@Qualifier("emsServiceV1")
	private EmsService emsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<EmsVO> emsVOList = emsService.selectAll();
		model.addAttribute("EMS_LIST", emsVOList);
		return "home";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("BODY", "EMS-WRITE");
		return "home";
	}

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@ModelAttribute("emsVO") EmsVO emsVO,
						@RequestParam(name = "file", required = false) MultipartFile file[]) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        emsVO.setS_date(dateFormat.format(date));

        dateFormat = new SimpleDateFormat("hh:mm");
        emsVO.setS_time(dateFormat.format(date));
        emsService.insert(emsVO, file);
        return "redirect:/";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") String id, Model model) {
		EmsVO emsVO = emsService.findById(Long.valueOf(id));
		model.addAttribute("BODY", "EMS-DETAIL");
		model.addAttribute("EMSVO", emsVO);
		return "home";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id) {
		emsService.delete(Long.valueOf(id));
		return "redirect:/";
	}
}
