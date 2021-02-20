package com.pamajon.common;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

@SpringBootApplication
@MapperScan(basePackages = "com.pamajon.mapper")
public class MyBatisApplication {

    @Autowired
    GlobalPropertySource globalPropertySource;
    @Bean
    @Primary
    public DataSource customDataSource() {
        return DataSourceBuilder
                .create()
                .url(globalPropertySource.getUrl())
                .driverClassName(globalPropertySource.getDriverClassName())
                .username(globalPropertySource.getUsername())
                .password(globalPropertySource.getPassword())
                .build();
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource customDataSource) throws Exception {

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(customDataSource);
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml");
        sessionFactory.setMapperLocations(res);
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession (SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    }
