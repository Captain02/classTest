package com.jzysoft.web.controller.system.answer;

import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CAwserServiceImpl implements CAnswerService{

    @Autowired
    DaoSupport daoSupport;


    @Override
    public List<PageData> selectTest(PageData pageData) throws Exception {
        return (List<PageData>) daoSupport.findForList("CAnswerMapper.selectTestByMcurrId",pageData);
    }

    @Override
    public void insertscore(PageData pageData1) throws Exception {
        daoSupport.save("CAnswerMapper.insertscore",pageData1);
    }


}
