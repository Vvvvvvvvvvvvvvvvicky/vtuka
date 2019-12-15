package club.vtuka.tuka;

import club.vtuka.tuka.controller.SpiderController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan("club.vtuka.tuka.mapper")
public class VtukaApplication {
//	@Bean
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource() {
//		DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
//		return dataSource;
//	}

//	@Bean
//	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
//
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource());
//
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//
//		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
//
//		return sqlSessionFactoryBean.getObject();
//	}

//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		return new DataSourceTransactionManager(dataSource());
//	}

	@Autowired
	private SpiderController spiderController;

	public static void main(String[] args) {
		SpringApplication.run(VtukaApplication.class, args);
	}

    //全局配置跨域
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/*").allowedOrigins("http://localhost:8080");
            }
        };
    }

//	public void task(){
//		spiderController.startSpider();
//	}
}
