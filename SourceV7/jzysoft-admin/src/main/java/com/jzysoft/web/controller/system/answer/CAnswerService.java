package com.jzysoft.web.controller.system.answer;

import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;

public interface CAnswerService {
    List<PageData> selectTest(PageData pageData) throws Exception;

    void insertscore(PageData pageData1) throws Exception;
}
