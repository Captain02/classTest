package com.jzysoft.commonmoudle.moudles.car;

import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;

public interface CarService {
    List<PageData> selectHistory(PageData pageData) throws Exception;
}
