package cn.aldd.vape.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.aldd.vape.constants.CommonConstants;
import cn.aldd.vape.user.micro.vo.CarouselFigureVo;
import cn.aldd.vape.util.FtpUtil;
import cn.aldd.vape.util.HttpUtils;
import cn.aldd.vape.util.JSONUtil;

@Controller
@RequestMapping("/carouselFigure")
public class CarouselFigureController {

	@RequestMapping("/list")
	public String list(Model model) {
		String result = HttpUtils.sendGet(CommonConstants.MICRO_URL + "/carouselFigure/micro/1/4");
		Map<String, Object> map = JSONUtil.toMap(result);
		model.addAttribute("list", JSONUtil.toList(JSONUtil.toJson(map.get("list")), CarouselFigureVo.class));
		model.addAttribute("imgUrl", CommonConstants.IMG_URL);
		return "carousel/carouselFigureList";
	}

	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") String id) {
		String result = HttpUtils.sendGet(CommonConstants.MICRO_URL + "/carouselFigure/micro/" + id);
		model.addAttribute("data", JSONUtil.toBean(result, CarouselFigureVo.class));
		model.addAttribute("imgUrl", CommonConstants.IMG_URL);
		return "carousel/carouselFigureDetail";
	}

	@PostMapping(value = "/upload")
	public String tijiao(@RequestParam(value = "id") String id,@RequestParam(value = "sort") String sort, @RequestParam(value = "file") MultipartFile file,
			Model model) {
		try {
			String path = FtpUtil.uploadFile(file.getInputStream(), file.getOriginalFilename(), "carousel");
			CarouselFigureVo vo = new CarouselFigureVo();
			vo.setId(id);
			vo.setSort(Integer.parseInt(sort));
			vo.setUrl(path);
			String result = HttpUtils.sendPost(CommonConstants.MICRO_URL + "/carouselFigure/micro/add", JSONUtil.toJson(vo));
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/carouselFigure/list";
	}

}
