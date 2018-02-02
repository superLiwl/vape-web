package cn.aldd.vape.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.aldd.vape.util.HttpUtils;
import cn.aldd.vape.vo.CarouselFigureVo;

@Controller
public class CarouselFigureController {

	@RequestMapping("/list")
	public String test(Model model) {
		List<CarouselFigureVo> list= HttpUtils.sendGet("http://localhost:9041/carouselFigure/micro/1/4",CarouselFigureVo.class);
		model.addAttribute("list", list);
		return "carouselFigureList";
	}

}
