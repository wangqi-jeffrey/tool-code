package com.yihaomen.test;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yihaomen.mybatis.inter.IUserOperation;
import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.model.User;
import com.yihaomen.mybatis.utils.LogUtils;

public class TestTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private static Log logger = LogFactory.getLog(TestTest.class);

    static{
        try{
            reader = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    
    public void getUserArticles(int userid){
    	SqlSession session = sqlSessionFactory.openSession();
    	try {
    	    IUserOperation userOperation=session.getMapper(IUserOperation.class);
    	    List<Article> articles = userOperation.getUserArticles(userid);
    	    for(Article article:articles){
    	    	System.out.println(article.getTitle()+":"+
    	    			":作者是:"+article.getUser().getUserName()+":地址:"+
    	                 article.getUser().getUserAddress());
    	    }
    	} finally {
    	    session.close();
    	}
    }
    public void getArticlesByUser(int userid){
    	SqlSession session = sqlSessionFactory.openSession();
    	try {
    		IUserOperation userOperation=session.getMapper(IUserOperation.class);   	   
    		User user = userOperation.getArticlesByUser(userid);
    		List<Article> articles = user.getArticles();
    		for(Article article:articles){
    	    	System.out.println(article.getTitle()+":"+
    	    			":作者是:"+article.getUser().getUserName()+":地址:"+
    	                 article.getUser().getUserAddress());
    	    }
    	} finally {
    		session.close();
    	}
    }
    
    public static void main(String[] args) {
    	TestTest testUser = new TestTest();
    	//testUser.getUserArticles(1);
    	//testUser.getArticlesByUser(1);
    	//testUser.addUser();
    	//testUser.addUserDish();
    	//List<Integer> ids = new ArrayList<Integer>();
    	//ids.add(1);
    	//ids.add(10);
    	//ids.add(11);
    	//ids.add(12);
    	//testUser.getUsersByIds(ids);
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("param1", "aassdsadads");
    	map.put("param2", "aassdsadads");
    	map.put("param3", "aassdsadads");
    	map.put("param4", "aassdsadads");
    	String payPassword = "sdks3234323ksdwsdksd";
    	logger.info(LogUtils.info("订单", "支付分期", null, "orderId="+1+",payPassword="+payPassword));
    	Users users = new Users("王琪","322332","sdsddssddssd");
    	logger.info(LogUtils.info("订单模块", "支付分期，必须传入支付密码，拆过你就爱你大了的路上看电视凉快的", "1",users));
	}

	private void addUser() {
		SqlSession session = sqlSessionFactory.openSession();
    	try {
    		IUserOperation userOperation=session.getMapper(IUserOperation.class);    	   
    		User user = new User();
    		user.setUserName("Jeffrey111");
    		user.setUserAge(23);
    		user.setUserAddress("山西忻州111");
    		userOperation.addUser(user);
    		System.out.println("userId:"+user.getId());                                                                   
    		session.commit();
    	} finally {
    		session.close();
    	}
	}
	/**
	 * 批量插入用户
	 */
	private void addUserDish() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation=session.getMapper(IUserOperation.class);
			List<User> userList = new ArrayList<User>();
			for(int i=400;i<5000;i++){
				User user = new User();
				user.setUserName("Jeffrey"+i);
				user.setUserAge(i);
				user.setUserAddress("山西忻州"+i);
				userList.add(user);
			}
			int num = userOperation.insertUserDish(userList);
			session.commit();//不提交也可以？？？ 当标签为select时不需要提交
			System.out.println("插入总数量："+num);
		} finally {
			session.close();
		}
	}
	/**
	 * 根据多个id获取用户
	 */
    public void getUsersByIds(List<Integer> ids){
    	logger.info("进入方法");
    	SqlSession session = sqlSessionFactory.openSession();
    	try {
    		IUserOperation userOperation=session.getMapper(IUserOperation.class);
    		List<User> users = userOperation.getUsersByIds(ids);
    		for(User user:users){
    	    	System.out.println(user.getUserName());
    	    }
    	} finally {
    		session.close();
    	}
    }
}