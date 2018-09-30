package com.lei.muitdatasourcedruid.dynamic.datasource.provider;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.lei.muitdatasourcedruid.dynamic.datasource.configure.DynamicDataSourceProperties;
import com.lei.muitdatasourcedruid.dynamic.datasource.configure.DynamicDataSourceProperty;
import com.lei.muitdatasourcedruid.dynamic.datasource.toolkit.DataSourceFactory;

public class YmlDynamicDataSourceProvider implements DynamicDataSourceProvider {

    private DynamicDataSourceProperties properties;

    public YmlDynamicDataSourceProvider(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, DataSource> loadDataSources() {
        Map<String, DynamicDataSourceProperty> dataSourcePropertiesMap = properties.getDatasource();
        Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size());
        for (Map.Entry<String, DynamicDataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
            dataSourceMap.put(item.getKey(), DataSourceFactory.createDataSource(item.getValue()));
        }
        return dataSourceMap;
    }

}