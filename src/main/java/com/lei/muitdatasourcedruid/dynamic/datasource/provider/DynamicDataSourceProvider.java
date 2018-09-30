package com.lei.muitdatasourcedruid.dynamic.datasource.provider;

import java.util.Map;

import javax.sql.DataSource;

public interface DynamicDataSourceProvider {
    /**
     * 加载所有数据源
     *
     * @return 所有数据源，key为数据源名称
     */
    Map<String, DataSource> loadDataSources();

}
