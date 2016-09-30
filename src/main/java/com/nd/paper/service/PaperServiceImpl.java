package com.nd.paper.service;

import com.nd.paper.dao.PaperDao;
import com.nd.paper.model.PaperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
@Service
public class PaperServiceImpl implements IPaperService {
    @Autowired
    private PaperDao paperDao;

    @Override
    public void addPaper(PaperEntity paperEntity,String uid) {
        paperDao.save(paperEntity,uid);
    }
}
