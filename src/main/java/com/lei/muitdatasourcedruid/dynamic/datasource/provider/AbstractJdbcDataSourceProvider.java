package com.lei.muitdatasourcedruid.dynamic.datasource.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.JdbcUtils;

import com.lei.muitdatasourcedruid.dynamic.datasource.configure.DynamicDataSourceProperty;
import com.lei.muitdatasourcedruid.dynamic.datasource.toolkit.DataSourceFactory;

public abstract class AbstractJdbcDataSourceProvider implements DynamicDataSourceProvider {

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

    public AbstractJdbcDataSourceProvider(String driverClassName, String url, String username, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Map<String, DataSource> loadDataSources() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            Map<String, DynamicDataSourceProperty> dataSourcePropertiesMap = executeStmt(stmt);
            Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size());
            for (Map.Entry<String, DynamicDataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
                dataSourceMap.put(item.getKey(), DataSourceFactory.createDataSource(item.getValue()));
            }
            return dataSourceMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
            JdbcUtils.closeStatement(stmt);
        }
        return null;
    }

    protected abstract Map<String, DynamicDataSourceProperty> executeStmt(Statement statement) throws SQLException;

}
