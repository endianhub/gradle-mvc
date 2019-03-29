package com.xh.mvc.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xh.mvc.model.WebTask;
import com.xh.mvc.service.IWebTaskService;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年3月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config.xml" })
public class WebTaskServiceTest {

	@Resource
	private IWebTaskService taskService;

	@Test
	public void query() {
		List<WebTask> list = taskService.loadAll(WebTask.class);
		System.out.println(list.size());
	}
}
