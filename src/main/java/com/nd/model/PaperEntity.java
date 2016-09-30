package com.nd.model;

import com.nd.question.model.QuestionEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Map;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/6 0006.
 */

@Document
@CompoundIndexes({
        @CompoundIndex(name = "paper", def = "{'paperId':1}", unique = true)
})
public class PaperEntity {
    @Id
    @GeneratedValue
    private String id;
    private String paperId;
    private List<QuestionEntity> questionEntityItems;
    private Map<String, Boolean> userItems;
    private String knowledgeCode;
    private String teacherId;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public List<QuestionEntity> getQuestionEntityItems() {
        return questionEntityItems;
    }

    public void setQuestionEntityItems(List<QuestionEntity> questionEntityItems) {
        this.questionEntityItems = questionEntityItems;
    }

    public Map<String, Boolean> getUserItems() {
        return userItems;
    }

    public void setUserItems(Map<String, Boolean> userItems) {
        this.userItems = userItems;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
