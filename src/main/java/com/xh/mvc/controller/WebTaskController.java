package com.xh.mvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xh.mvc.model.WebTask;
import com.xh.mvc.service.IWebTaskService;

/**
 * <b>Title: 任务管理</b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2018年9月4日
 */
@Controller
public class WebTaskController {

	@Resource
	private IWebTaskService taskService;

	@RequestMapping("index")
	public String index() {

		return "easyui.index";
	}

	@RequestMapping("query")
	@ResponseBody
	public Object query() {
		List<WebTask> list = taskService.loadAll(WebTask.class);
		return list;
	}

}
