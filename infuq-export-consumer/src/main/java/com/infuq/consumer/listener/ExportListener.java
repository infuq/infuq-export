package com.infuq.consumer.listener;

import com.alibaba.fastjson2.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.infuq.common.model.ExportTaskDTO;
import com.infuq.consumer.service.ExportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ExportListener implements MessageListener {

    private ExportService exportService;

    public ExportListener() {
    }

    public ExportListener(ExportService exportService) {
        this.exportService = exportService;
    }

    @Override
    public Action consume(Message message, ConsumeContext context) {

        log.info("监听器:" + this.hashCode() + ",消费MQ:" + message.getMsgID() + ",TOPIC:" + message.getTopic() + ",TAG:" + message.getTag());
        byte[] body = message.getBody();

        ExportTaskDTO exportTaskDTO = JSON.parseObject(body, ExportTaskDTO.class);

        exportService.handleExport(exportTaskDTO);

        return Action.CommitMessage;

    }



}

