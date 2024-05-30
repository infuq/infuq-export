package com.infuq.provider.service;


import com.alibaba.fastjson.JSON;
import com.infuq.common.enums.ExportTypeEnum;
import com.infuq.common.enums.FileStatus;
import com.infuq.common.enums.SuffixType;
import com.infuq.common.model.ExportRecord;
import com.infuq.common.req.StoreCustomerOrderReq;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ExportService {


    public void exportStoreCustomerOrder(StoreCustomerOrderReq req) {

        req.setUserId(154539762039238656L);

        ExportRecord record = ExportRecord.builder()
                .userId(154539762039238656L)
                .fileName("订货单信息-202405301324413413271.xlsx")
                .fileStatus(FileStatus.CREATE_SUCCESS.getCode())
                .fileTypeDesc("订货单信息")
                .fileSuffix(SuffixType.Xlsx.getFileType())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .requestBody(JSON.toJSONString(req))
                .exportType(ExportTypeEnum.CUSTOMER_ORDER_INFO.getValue())
                .enterpriseId(154539761477201920L)
                .build();

        // 1.向数据库插入导出记录
        // mapper.save(record)

        // 2.发送MQ消息
        // provider.sendMessage(...)

    }


}
