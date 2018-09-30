package com.lei.muitdatasourcedruid.dynamic.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import com.lei.muitdatasourcedruid.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.lei.muitdatasourcedruid.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean{
	private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 所有数据库
     */
    private Map<String, DataSource> dataSourceMap;

    /**
     * 分组数据库
     */
   // private Map<String, DynamicGroupDataSource> groupDataSources = new HashMap<>();

    private DynamicDataSourceProvider provider;
    
    //private Class<? extends DynamicDataSourceStrategy> strategy;

    private String primary;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 this.dataSourceMap = provider.loadDataSources();
	     log.debug("共加载 {} 个数据源", dataSourceMap.size());
	}

	@Override
	protected DataSource determineDataSource() {
		String lookupKey = DynamicDataSourceContextHolder.getDataSourceLookupKey();
        if (StringUtils.isEmpty(lookupKey)) {
            return determinePrimaryDataSource();
        }else if (dataSourceMap.containsKey(lookupKey)) {
            log.debug("从 {} 单数据源中返回数据源", lookupKey);
            return dataSourceMap.get(lookupKey);
        } 
        return determinePrimaryDataSource();
	}

	private DataSource determinePrimaryDataSource() {
		 log.debug("从默认数据源中返回数据");
        return dataSourceMap.get(primary);
	}

	public Map<String, DataSource> getDataSourceMap() {
		return dataSourceMap;
	}

	public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}

	public DynamicDataSourceProvider getProvider() {
		return provider;
	}

	public void setProvider(DynamicDataSourceProvider provider) {
		this.provider = provider;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}
	
	

}
