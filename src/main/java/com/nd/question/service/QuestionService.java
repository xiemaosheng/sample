package com.nd.question.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/11 0011.
 */
public interface QuestionService {
    Object uploadQuestions(XSSFWorkbook wb);
}
