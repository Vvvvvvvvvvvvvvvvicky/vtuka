package club.vtuka.tuka.service;


import club.vtuka.tuka.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService /*implements UserDetailsService*/ {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;

	//@Autowired
//	private BCryptPasswordEncoder passwordEncoder;


//    int insert(User record);
//
//    int insertSelective(User record);
//
//    User selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);
	/*
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		User user = userMapper.selectByUserName(userName);
//		if (user==null){
//			throw new UsernameNotFoundException("用户名不存在");
//		}
		logger.info("用户名为："+userName);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("123456");

		logger.info("密码为："+password);
		//User user = new User(userName,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return new User();
	}
	*/
}