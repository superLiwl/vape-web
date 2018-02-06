package cn.aldd.vape.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.aldd.vape.constants.CommonConstants;
import cn.aldd.vape.user.micro.vo.EncyclopediaVo;
import cn.aldd.vape.util.HttpUtils;
import cn.aldd.vape.util.JSONUtil;

@Controller
@RequestMapping("/encyclopedia")
public class EncyclopediaController {

	@RequestMapping("/list")
	public String list(Model model) {
		EncyclopediaVo encyclopediaVo = new EncyclopediaVo();
		String result = HttpUtils.sendPost(CommonConstants.MICRO_URL + "/encyclopedia/micro/1/1000",
				JSONUtil.toJson(encyclopediaVo));
		Map<String, Object> map = JSONUtil.toMap(result);
		List<EncyclopediaVo> list = null;
		if (null != map.get("list")) {
			list = JSONUtil.toList(JSONUtil.toJson(map.get("list")), EncyclopediaVo.class);
		}
		model.addAttribute("list", list);
		model.addAttribute("imgUrl", CommonConstants.IMG_URL);
		return "encyclopedia/encyclopedia";
	}

	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") String id) {
		String result = HttpUtils.sendGet(CommonConstants.MICRO_URL + "/encyclopedia/micro/" + id);
		model.addAttribute("data", JSONUtil.toBean(result, EncyclopediaVo.class));
		model.addAttribute("imgUrl", CommonConstants.IMG_URL);
		return "encyclopedia/encyclopediaDetail";
	}

	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		model.addAttribute("data", new EncyclopediaVo());
		model.addAttribute("imgUrl", CommonConstants.IMG_URL);
		return "encyclopedia/encyclopediaDetail";
	}

	@RequestMapping("/save")
	public String save(Model model, @RequestParam("id") String id, @RequestParam("title") String title,
			@RequestParam("author") String author, @RequestParam("content") String content) {
		EncyclopediaVo encyclopediaVo = new EncyclopediaVo();
		encyclopediaVo.setAuthor(author);
		encyclopediaVo.setContent(content);
		encyclopediaVo.setId(id);
		encyclopediaVo.setTitle(title);
		HttpUtils.sendPost(CommonConstants.MICRO_URL + "/encyclopedia/micro/add/", JSONUtil.toJson(encyclopediaVo));
		return "redirect:/encyclopedia/list";
	}

	@RequestMapping("/delete")
	public void delete(Model model, @RequestBody @RequestParam("id") String id) {
		HttpUtils.sendGet(CommonConstants.MICRO_URL + "/encyclopedia/micro/delete/" + id);
	}
}
