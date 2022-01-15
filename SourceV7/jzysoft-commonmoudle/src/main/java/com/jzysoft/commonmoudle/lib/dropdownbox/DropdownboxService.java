package com.jzysoft.commonmoudle.lib.dropdownbox;




import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.List;

public interface DropdownboxService {
    List<PageData> getTable(String tableName, String ValueFieldName, String NameFieldName) throws Exception;

    List<PageData> getTables(String tableName, String ValueFieldName, String NameFieldName) throws Exception;


    List<PageData> getTableZd(String tableName, String ValueFieldName, String NameFieldName, String dict_type) throws Exception;

    List<PageData> getTableZdx(String tableName, String ValueFieldName, String NameFieldName, String dict_type) throws Exception;
//    根据值获取
    List<PageData> GetCommonDropDownById(String tableName, String ValueFieldName, String NameFieldName, String key, String value) throws Exception;


}
