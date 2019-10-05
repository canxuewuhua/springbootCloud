package com.spring.cloud.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.spring.cloud.**.dataplatform", sqlSessionFactoryRef="dataplatformSessionFactory")
@Slf4j
public class MysqlDataSourceConfig {

    /**
     * 配置数据平台数据数据源
     */
    @Bean(name = "dataplatformDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.hikari")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 配置session工厂
     * */
    @Bean(name = "dataplatformSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataplatformDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        hikariDataSource.setJdbcUrl("127.0.0.1");
        hikariDataSource.setUsername("admin");
        hikariDataSource.setPassword("123456");
        sessionFactoryBean.setDataSource(hikariDataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/spring/cloud/**/dataplatform/.**.*"));
        log.info("数据平台数据源初始化成功");
        return sessionFactoryBean.getObject();
    }
}
