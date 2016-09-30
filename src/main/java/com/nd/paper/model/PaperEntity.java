package com.nd.paper.model;

import com.nd.question.model.QuestionEntity;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;

public class PaperEntity {
    @Id
    @GeneratedValue
    private String paperId;

    private String paperName;

    private List<QuestionEntity> questionEntityItems;

    private String knowledgeCode;

    private String teacherId;

    private Date createDate;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public List<QuestionEntity> getQuestionEntityItems() {
        return questionEntityItems;
    }

    public void setQuestionEntityItems(List<QuestionEntity> questionEntityItems) {
        this.questionEntityItems = questionEntityItems;
    }

    public String getKnowledgeCode() {
        return knowledgeCode;
    }

    public void setKnowledgeCode(String knowledgeCode) {
        this.knowledgeCode = knowledgeCode;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

