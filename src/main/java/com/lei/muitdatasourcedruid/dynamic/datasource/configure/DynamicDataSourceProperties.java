package com.lei.muitdatasourcedruid.dynamic.datasource.configure;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;


@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class DynamicDataSourceProperties {
    /**
     * 必须设置默认的库,默认master
     */
    private String primary = "master";

    /**
     * 每一个数据源
     */
    private Map<String, DynamicDataSourceProperty> datasource = new LinkedHashMap<>();


    /**
     * aop切面顺序，默认优先级最高
     */
    private Integer order = Ordered.HIGHEST_PRECEDENCE;


	public String getPrimary() {
		return primary;
	}


	public void setPrimary(String primary) {
		this.primary = primary;
	}


	public Map<String, DynamicDataSourceProperty> getDatasource() {
		return datasource;
	}

	
	public void setDatasource(Map<String, DynamicDataSourceProperty> datasource) {
		this.datasource = datasource;
	}


	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}
    
    
    
}
