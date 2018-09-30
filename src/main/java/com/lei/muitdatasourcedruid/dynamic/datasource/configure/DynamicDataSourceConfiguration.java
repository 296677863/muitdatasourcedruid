package com.lei.muitdatasourcedruid.dynamic.datasource.configure;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lei.muitdatasourcedruid.dynamic.datasource.DynamicRoutingDataSource;
import com.lei.muitdatasourcedruid.dynamic.datasource.aop.DynamicDataSourceAnnotationAdvisor;
import com.lei.muitdatasourcedruid.dynamic.datasource.aop.DynamicDataSourceAnnotationInterceptor;
import com.lei.muitdatasourcedruid.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.lei.muitdatasourcedruid.dynamic.datasource.provider.YmlDynamicDataSourceProvider;

@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DynamicDataSourceConfiguration {

	private final DynamicDataSourceProperties properties;
	
	public DynamicDataSourceConfiguration(DynamicDataSourceProperties properties) {
		this.properties = properties;
	}

	@Bean
	@ConditionalOnMissingBean
	public DynamicDataSourceProvider dynamicDataSourceProvider() {
		return new YmlDynamicDataSourceProvider(properties);
	}

	@Bean
	@ConditionalOnMissingBean
	public DynamicRoutingDataSource dynamicRoutingDataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
		DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
		dynamicRoutingDataSource.setPrimary(properties.getPrimary());
		// dynamicRoutingDataSource.setStrategy(properties.getStrategy());
		dynamicRoutingDataSource.setProvider(dynamicDataSourceProvider);
		return dynamicRoutingDataSource;
	}

	@Bean
	@ConditionalOnMissingBean
	public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
		DynamicDataSourceAnnotationInterceptor interceptor = new DynamicDataSourceAnnotationInterceptor();
		DynamicDataSourceAnnotationAdvisor advisor = new DynamicDataSourceAnnotationAdvisor(interceptor);
		advisor.setOrder(properties.getOrder());
		System.out.println("advisor 初始化");
		return advisor;
	}

}
