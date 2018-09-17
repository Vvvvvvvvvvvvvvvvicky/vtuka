package club.vtuka.tuka.config;

import java.beans.PropertyVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * created by vic on 2018/09/17
 */
@Configuration
public class DBConfig {
	
	@Autowired
	private Environment env;
	
	@Bean(name="dataSource")
	public ComboPooledDataSource dataSource() throws PropertyVetoException{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getProperty("db.driverClassName"));
		dataSource.setJdbcUrl(env.getProperty("db.url"));
		dataSource.setUser(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		dataSource.setMaxPoolSize(50);
		dataSource.setMinPoolSize(5);
		dataSource.setInitialPoolSize(10);
		dataSource.setMaxIdleTime(300);
		dataSource.setAcquireIncrement(5);
		dataSource.setIdleConnectionTestPeriod(60);
		return dataSource;
	}
}
