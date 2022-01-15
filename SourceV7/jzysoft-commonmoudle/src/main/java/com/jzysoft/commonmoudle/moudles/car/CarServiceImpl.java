package com.jzysoft.commonmoudle.moudles.car;


import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    DaoSupport daoSupport;


    //    id
    @Override
    public List<PageData> selectHistory(PageData pageData) throws Exception {
        return (List<PageData>) daoSupport.findForList("HistoryBhMapper.selectHistory", pageData);
    }
}
