package com.nd.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/6 0006.
 */
@Document
@CompoundIndexes({
        @CompoundIndex(name = "paperAnswer", def = "{'userId':1,'paperId':1}", unique = true)
})
public class PaperAnswerEntity {
    @Id
    @GeneratedValue
    private String id;
    private String userId;
    private String paperId;
    private List<QuestionAnswerEntity> result;
    private String totalScore;
    private Date time;
    private List<String> resource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public List<QuestionAnswerEntity> getResult() {
        return result;
    }

    public void setResult(List<QuestionAnswerEntity> result) {
        this.result = result;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<String> getResource() {
        return resource;
    }

    public void setResource(List<String> resource) {
        this.resource = resource;
    }
}
