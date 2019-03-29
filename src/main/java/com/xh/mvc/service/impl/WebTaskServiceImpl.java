package com.xh.mvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xh.mvc.dao.WebTaskDao;
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
@Service
public class WebTaskServiceImpl implements IWebTaskService {

	@Resource
	private WebTaskDao webTaskDao;

	@Override
	public List<WebTask> loadAll(Class<WebTask> paramClass) {

		return webTaskDao.loadAll(paramClass);
	}

}
