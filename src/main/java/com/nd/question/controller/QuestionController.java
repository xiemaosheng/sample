package com.nd.question.controller;

import com.nd.gaea.rest.security.authens.UserInfo;
import com.nd.question.model.QuestionEntity;
import com.nd.question.service.QuestionService;
import com.nd.uc.UcApiService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by ${zhiqiangXu}
 * on 2016/9/11 0011.
 */
@RestController
@RequestMapping(value = "/v1/teachers/questions")
public class QuestionController {
    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private UcApiService ucApiService;

    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/actions/upload")
    public Object uploadQuestions(@AuthenticationPrincipal UserInfo userInfo, @RequestParam("file") CommonsMultipartFile file) {
        //判断文件是否为空
        if (file == null) return null;
        //获取文件名
        String name = file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size = file.getSize();
        if (name == null || ("").equals(name) && size == 0) return null;
        XSSFWorkbook wb = null;
        try {
            InputStream in = file.getInputStream();
            OPCPackage pkg = null;
            try {
                pkg = OPCPackage.open(in);
            } catch (InvalidFormatException e) {
                return null;
            }
            wb = new XSSFWorkbook(pkg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionService.uploadQuestions(wb);
        return "上传成功";
    }
}
