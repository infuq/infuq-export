package com.infuq.export.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.ds.ItemDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Map;


@Configuration
@Slf4j
public class MySQLConfig implements ApplicationContextAware {

    @Autowired
    private Environment environment;
    private ApplicationContext applicationContext;


    public static void main(String[] args) {

    }

    private void printDBInfo() {
        try {

            String env = environment.getProperty("spring.profiles.active");

            if (StringUtils.isEmpty(env)) {
                return;
            }

            if (env.contains("dev") || env.contains("test") || env.contains("pre")) {
                DynamicRoutingDataSource dataSource = (DynamicRoutingDataSource) applicationContext.getBean("dataSource");
                Map<String, DataSource> currentDataSources = dataSource.getDataSources();

                ItemDataSource iDataSource = (ItemDataSource) currentDataSources.get("master");
                HikariDataSource realDataSource = (HikariDataSource) iDataSource.getRealDataSource();

                String username = realDataSource.getUsername();
                String password = realDataSource.getPassword();
                String jdbcUrl = realDataSource.getJdbcUrl();

                log.info("数据库信息:{},{},{}", username, password, jdbcUrl);
            }

        } catch (Throwable ignored) { }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
