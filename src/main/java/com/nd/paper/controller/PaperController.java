package com.nd.paper.controller;

import com.nd.gaea.rest.security.authens.UserInfo;
import com.nd.paper.model.PaperEntity;
import com.nd.paper.service.PaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
@RestController
@RequestMapping(value = "/v1/papers")
public class PaperController {
    @Autowired
    private PaperServiceImpl paperService;

    @PostMapping(value = "/add")
    public void addPaper(@AuthenticationPrincipal UserInfo userInfo, @RequestBody PaperEntity paperEntity) {
        String uid = userInfo.getUserId();
        Date date = new Date();
        paperEntity.setCreateDate(date);
        paperService.addPaper(paperEntity, uid);
    }
}
