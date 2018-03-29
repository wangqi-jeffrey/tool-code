package com.yihaomen.mybatis.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.model.Region;
import com.yihaomen.mybatis.model.User;

public interface IUserOperation {
    
	public User selectUserByID(int id);
	public List<User> selectUsers(String userName);	
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	
	public List<Article> getUserArticles(int id);
	public User getArticlesByUser(int id);
	
	public int insertUserDish(List<User> userList);
	public List<User> getUsersByIds(@Param("ids")List<Integer> ids);
	Region selectForKey(String string);
	
}
