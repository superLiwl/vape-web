package cn.aldd.vape.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.aldd.vape.constants.CommonConstants;
import cn.aldd.vape.util.HttpUtils;
import cn.aldd.vape.util.JSONUtil;
import cn.aldd.vape.vo.CarouselFigureVo;

@Controller
public class CarouselFigureController {

	@RequestMapping("/list")
	public String test(Model model) {
		String result = HttpUtils.sendGet(CommonConstants.MICRO_URL + "/carouselFigure/micro/1/4");
		Map<String, Object> map = JSONUtil.toMap(result);
		model.addAttribute("list", JSONUtil.toList(JSONUtil.toJson(map.get("list")), CarouselFigureVo.class));
		model.addAttribute("imgUrl", CommonConstants.IMG_URL);
		return "carousel/carouselFigureList";
	}

}
