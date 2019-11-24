package club.vtuka.tuka.config.security;

/*
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userService;

	@Autowired
	private CustomMetadataSource metadataSource;

	@Autowired
	private UrlAccessDecisionManager urlAccessDecisionManager;

	@Autowired
	private AuthenticationAccessDeniedHandler deniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.html","/static/**","/login_p");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O object) {
				object.setAccessDecisionManager(urlAccessDecisionManager);
				object.setSecurityMetadataSource(metadataSource);
				return object;
			}
		}).and()
		.formLogin().loginPage("/login.html").loginProcessingUrl("/login")
		.passwordParameter("password").usernameParameter("username")
		.failureHandler(new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				response.setContentType("application/json;charset=utf-8");
				RespResult respResult = null;
				if(exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException){
					respResult = RespResult.error("用户名或密码输入错误");
				}else if(exception instanceof LockedException){
					respResult = RespResult.error("用户被锁定，请联系管理员!");
				}else if(exception instanceof CredentialsExpiredException){
					respResult = RespResult.error("密码过期，请联系管理员!");
				}else if(exception instanceof AccountExpiredException){
					respResult = RespResult.error("用户已过期，请联系管理员!");
				}else if(exception instanceof DisabledException){
					respResult = RespResult.error("用户已被禁用，请联系管理员!");
				}else{
					respResult = RespResult.error("登陆失败!");
				}
			}
		});
	}
}

 */
