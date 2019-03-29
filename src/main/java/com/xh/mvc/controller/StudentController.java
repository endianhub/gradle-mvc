package com.xh.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年3月29日
 */
@Controller
@RequestMapping("/")
public class StudentController {

	@RequestMapping("info")
	public String info() {
		return "info";
	}
}
