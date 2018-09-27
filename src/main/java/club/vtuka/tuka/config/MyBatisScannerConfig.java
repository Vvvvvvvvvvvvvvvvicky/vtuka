package club.vtuka.tuka.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisScannerConfig {
	@Bean
	public MapperScannerConfigurer MyBatisScannerConfig(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("");
		return mapperScannerConfigurer;
	}
}
