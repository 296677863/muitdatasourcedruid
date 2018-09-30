package com.lei.muitdatasourcedruid.dynamic.datasource.configure;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

import com.lei.muitdatasourcedruid.dynamic.datasource.configure.druid.DruidDataSourceProperties;

public class DynamicDataSourceProperty {
	 /**
     * 连接池类型，如果不设置自动查找 Druid > HikariCp
     */
    private Class<? extends DataSource> type;

    /**
     * JDBC driver
     */
    private String driverClassName;

    /**
     * JDBC url 地址
     */
    private String url;

    /**
     * JDBC 用户名
     */
    private String username;

    /**
     * JDBC 密码
     */
    private String password;

    /**
     * Druid参数配置DruidDataSourceWrapper
     */
    @NestedConfigurationProperty
    private DruidDataSourceProperties druid = new DruidDataSourceProperties();

	public Class<? extends DataSource> getType() {
		return type;
	}

	public void setType(Class<? extends DataSource> type) {
		this.type = type;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DruidDataSourceProperties getDruid() {
		return druid;
	}

	public void setDruid(DruidDataSourceProperties druid) {
		this.druid = druid;
	}

    
}
