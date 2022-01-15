package com.jzysoft.commonmoudle.moudles.getIp;


import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参见"测控组成类型"状态图 服务层实现
 *
 * @author zyc
 * @date 2020-03-28
 */
@Service("GetIpService")
public class GetIpServiceImpl implements GetIpService {
    @Autowired
    DaoSupport daoSupport;


    //    保存
    @Override
    public void insertVisitrecord(PageData pageData) throws Exception {
        daoSupport.save("VisitrecordMapper.insertVisitrecord", pageData);

    }



}
