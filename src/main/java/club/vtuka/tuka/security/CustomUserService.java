package club.vtuka.tuka.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import club.vtuka.tuka.mapper.UserMapper;
import club.vtuka.tuka.model.User;

public class CustomUserService implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userMapper.selectByUserName(userName);
		if(user==null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		//给用户分配权限
//		for(user.getRole()){
//			
//		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
	}
}
