package com.jzysoft.commonmoudle.lib.dropdownbox;



import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commonService")
public class DropdownboxServiceImpl implements DropdownboxService {
    @Autowired
    DaoSupport daoSupport;

    @Override
    public List<PageData> getTable(String TableName, String ValueFieldName, String NameFieldName) throws Exception {
        PageData pageData = new PageData();
        pageData.put("TableName", TableName);
        pageData.put("ValueFieldName", ValueFieldName);
        pageData.put("NameFieldName", NameFieldName);
        return (List<PageData>) daoSupport.findForList("DropdownMapper.selectCommon", pageData);
    }

    @Override
    public List<PageData> getTables(String TableName, String ValueFieldName, String NameFieldName) throws Exception {
        PageData pageData = new PageData();
        pageData.put("TableName", TableName);
        pageData.put("ValueFieldName", ValueFieldName);
        pageData.put("NameFieldName", NameFieldName);
        return (List<PageData>) daoSupport.findForList("DropdownMapper.selectCommons", pageData);
    }


    @Override
    public List<PageData> getTableZd(String TableName, String ValueFieldName, String NameFieldName, String dict_type) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("TableName", TableName);
        map.put("ValueFieldName", ValueFieldName);
        map.put("NameFieldName", NameFieldName);
        map.put("dict_type", dict_type);
        return (List<PageData>) daoSupport.findForList("DropdownMapper.selectCommonZd", map);
    }

    @Override
    public List<PageData> getTableZdx(String TableName, String ValueFieldName, String NameFieldName, String dict_type) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("TableName", TableName);
        map.put("ValueFieldName", ValueFieldName);
        map.put("NameFieldName", NameFieldName);
        map.put("dict_type", dict_type);
        return (List<PageData>) daoSupport.findForList("DropdownMapper.selectCommonZdx", map);
    }
    @Override
    public List<PageData> GetCommonDropDownById(String TableName, String ValueFieldName, String NameFieldName, String key, String value) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("TableName", TableName);
        map.put("ValueFieldName", ValueFieldName);
        map.put("NameFieldName", NameFieldName);
        map.put("key", key);
        map.put("value", value);
        return (List<PageData>) daoSupport.findForList("DropdownMapper.GetCommonDropDownById", map);
    }


}
