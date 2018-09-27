package club.vtuka.tuka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	UserDetailsService customUserService(){
		return new CustomUserService();
	}
}
