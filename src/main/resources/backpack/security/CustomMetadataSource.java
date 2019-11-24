package club.vtuka.tuka.config.security;

/*
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> allMenu = menuService.getAllMenu();
        //匹配资源，按照匹配角色访问
        for(Menu menu:allMenu){
            if(antPathMatcher.match(menu.getUrl(),requestUrl) && menu.getRoles().size() > 0){
                List<Role> roles = menu.getRoles();
                String[] values = new String[roles.size()];
                for(int i=0;i<roles.size();i++){
                    values[i] = (roles.get(i).getName());
                }
                return SecurityConfig.createList(values);
            }
        }
        //未匹配资源，需登陆访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
*/
