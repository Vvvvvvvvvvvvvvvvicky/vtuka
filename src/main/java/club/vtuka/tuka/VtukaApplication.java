package club.vtuka.tuka;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import club.vtuka.tuka.controller.SpiderController;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
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
//		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
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


//	public void task(){
//		spiderController.startSpider();
//	}
}
