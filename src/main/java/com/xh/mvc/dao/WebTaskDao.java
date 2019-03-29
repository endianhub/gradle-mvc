package com.xh.mvc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.xh.mvc.model.WebTask;

/**
 * <b>Title: </b>
 * <p>Description: </p>
 * 
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2019年3月28日
 */
@Repository
public class WebTaskDao extends HibernateDaoSupport {

	@Resource(name = "sessionFactory")
	private void setMySessionFactory(SessionFactory sessionFactory) {
		// 这个方法名可以随便写，@Resource可以通过name 或者type来装载的。
		super.setSessionFactory(sessionFactory);
	}

	public Session getSession() {

		return getSessionFactory().getCurrentSession();
	}

	public List<WebTask> loadAll(Class<WebTask> paramClass) {

		return getHibernateTemplate().loadAll(paramClass);
	}
}
