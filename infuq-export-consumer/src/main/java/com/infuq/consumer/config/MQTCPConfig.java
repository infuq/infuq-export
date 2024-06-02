package com.infuq.consumer.config;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.infuq.consumer.listener.ExportListener;
import com.infuq.consumer.service.ExportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Slf4j
@AllArgsConstructor
@Configuration
public class MQTCPConfig {

    private final Environment environment;


    public Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(PropertyKeyConst.ConsumeTimeout, "3");
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "30000");
        properties.setProperty(PropertyKeyConst.AccessKey, "LTAI5tKjNBBn5xo4surp7Z5P");
        properties.setProperty(PropertyKeyConst.SecretKey, "dHSBviUuisvwpl4ojqAiNBsCs8AJxW");
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, "http://MQ_INST_1077684811287398_BY9u0O9r.cn-hangzhou.mq.aliyuncs.com:80");

//        String name = environment.getProperty("spring.application.name");
//        String profile = environment.getProperty("spring.profiles.active");
//        String groupId = "GID_" + name.replaceAll("-", "_").toUpperCase() + "_" + profile.toUpperCase();
        properties.setProperty(PropertyKeyConst.GROUP_ID, "GID_EXPORT_CONSUMER");
        return properties;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Consumer consumer(ExportService exportService) {

        Consumer consumer = ONSFactory.createConsumer(properties());
        consumer.subscribe("EXPORT_ORDER", "*", new ExportListener(exportService));
        consumer.subscribe("EXPORT_RETURN_ORDER", "*", new ExportListener(exportService));
        return consumer;
    }

}

