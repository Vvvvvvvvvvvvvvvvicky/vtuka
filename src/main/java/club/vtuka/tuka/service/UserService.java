package club.vtuka.tuka.service;

import org.springframework.beans.factory.annotation.Autowired;

import club.vtuka.tuka.mapper.UserMapper;
import club.vtuka.tuka.model.User;

public class UserService {
	
	@Autowired
	private UserMapper userMapper;

//    int insert(User record);
//
//    int insertSelective(User record);
//
//    User selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);
	
	public User getUserByName(String userName){
		return userMapper.selectByUserName(userName);
	}
}