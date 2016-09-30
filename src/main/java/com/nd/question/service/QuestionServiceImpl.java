package com.nd.question.service;

import com.nd.question.model.QuestionEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/11 0011.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    private final static Pattern CODE_PATTERN = Pattern.compile("【A-Z】", Pattern.MULTILINE);

    @Override
    public Object uploadQuestions(XSSFWorkbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        Integer ruwNum = sheet.getLastRowNum();
        List<QuestionEntity> questionEntityList = new ArrayList();
        for (int i = 1; i < ruwNum; i++) {
            Row row = sheet.getRow(i);
            QuestionEntity questionEntity = new QuestionEntity();
            Cell cell = row.getCell(0);
            String questionTitle = cell.getRichStringCellValue().toString();
            questionEntity.setQuestionTitle(questionTitle);
        }
        return null;
    }

}
