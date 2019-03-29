package com.xh.mvc.service;

import java.util.List;

import com.xh.mvc.model.WebTask;


/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年3月28日
 */
public interface IWebTaskService {

	public List<WebTask> loadAll(Class<WebTask> paramClass);
}
