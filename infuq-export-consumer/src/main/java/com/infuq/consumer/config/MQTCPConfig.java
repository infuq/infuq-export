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

        properties.setProperty(PropertyKeyConst.ConsumeTimeout, "15");
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "30000");
        properties.setProperty(PropertyKeyConst.AccessKey, "LTAIVydAIJ410rdg");
        properties.setProperty(PropertyKeyConst.SecretKey, "dozXcWUfUtQJFg5Rxv8gDSejSQC0dC");
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, "http://MQ_INST_1106705442396860_BXWMBxy9.mq-internet-access.mq-internet.aliyuncs.com:80");

//        String name = environment.getProperty("spring.application.name");
//        String profile = environment.getProperty("spring.profiles.active");
//        String groupId = "GID_" + name.replaceAll("-", "_").toUpperCase() + "_" + profile.toUpperCase();
        properties.setProperty(PropertyKeyConst.GROUP_ID, "GID_WISP_TMS_TEST");
        return properties;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Consumer consumer(ExportService exportService) {

        Consumer consumer = ONSFactory.createConsumer(properties());
        consumer.subscribe("TEST", "EXPORT", new ExportListener(exportService));
        return consumer;
    }

}

