package com.lei.muitdatasourcedruid.dynamic.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.AbstractDataSource;

/**
 * 抽象动态获取数据源
 *
 */
public abstract class AbstractRoutingDataSource extends AbstractDataSource {

	/**
	 * 子类实现决定最终数据源
	 *
	 * @return 数据源
	 */
	protected abstract DataSource determineDataSource();

	@Override
	public Connection getConnection() throws SQLException {
		return determineDataSource().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return determineDataSource().getConnection(username, password);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T unwrap(Class<T> iface) throws SQLException {
		if (iface.isInstance(this)) {
			return (T) this;
		}
		return determineDataSource().unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return (iface.isInstance(this) || determineDataSource().isWrapperFor(iface));
	}

}
