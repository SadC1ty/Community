package com.nowcoder.community;

import com.nowcoder.community.Dao.AlphaDao;
import com.nowcoder.community.Service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	@Test
	void contextLoads() {

	}

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());//Mybatis

		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());//Hibernate
	}

	//测试管理方法
	@Test
	public void testBeanManagement(){
		AlphaService  alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		/*
			实例化AlphaService
			初始化AlphaService
			com.nowcoder.community.Service.AlphaService@20411320
			销毁AlphaService
			默认是单个实例，当添加注解@Scope("prototype")
		 */
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		/*
			实例化AlphaService
			初始化AlphaService
			com.nowcoder.community.Service.AlphaService@1416a80a
			实例化AlphaService
			初始化AlphaService
			com.nowcoder.community.Service.AlphaService@719bb3b4
		 */
	}

	//测试装配类
	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));//2021-03-06  21:11:05
	}

	//依赖注入
	@Autowired
	//@Qualifier("alphaHibernate")
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		System.out.println(alphaDao);//com.nowcoder.community.Dao.AlphaDaoMybatisImpl@1e530163
		//System.out.println(alphaDao);//com.nowcoder.community.Dao.AlphaDaoHibernateImpl@54bca971(声明的情况下)
		System.out.println(alphaService);//com.nowcoder.community.Service.AlphaService@14d8444b
		System.out.println(simpleDateFormat);//java.text.SimpleDateFormat@25c5980
	}
}
