package com.infuq.consumer.listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class ExportListener implements MessageListener {


    @Override
    public Action consume(Message message, ConsumeContext context) {


        return Action.CommitMessage;

    }



}

