package com.infuq.consumer.config;

import com.aliyun.mq.http.MQClient;
import com.aliyun.mq.http.MQConsumer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@AllArgsConstructor
//@Configuration
public class MQHTTPConfig {

    private final Environment environment;

    @Bean
    public MQConsumer consumer() {
        MQClient mqClient = new MQClient(
                "http://1106705442396860.mqrest.cn-hangzhou.aliyuncs.com",
                "LTAIVydAIJ410rdg",
                "dozXcWUfUtQJFg5Rxv8gDSejSQC0dC"
        );

        return mqClient.getConsumer("MQ_INST_1106705442396860_BXXINmp6", "TEST", "GID_WISP_EXPORT_TEST", "EXPORT");
    }


}

