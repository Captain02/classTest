package com.jzysoft.commonmoudle.lib.dropdownbox;


import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 通用请求处理
 *
 * @author jzysoft
 */
@Controller
@RequestMapping("/common")
public class DropdownboxController extends BaseJQController {
    @Autowired
    DropdownboxService commonService;

    //    普通下拉框
    @ResponseBody
    @RequestMapping("/GetCommonDropDownData/{TableName}/{ValueFieldName}/{NameFieldName}")
    public RJQ GetCommonDropDownData(@PathVariable("TableName") String TableName, @PathVariable("ValueFieldName") String ValueFieldName, @PathVariable("NameFieldName") String NameFieldName) throws Exception {
        List<PageData> table = (List<PageData>) commonService.getTable(TableName, ValueFieldName, NameFieldName);
        return RJQ.ok().put("data", table);
    }

    //    工程普通下拉框
    @ResponseBody
    @RequestMapping("/GetCommonDropDownDatas/{TableName}/{ValueFieldName}/{NameFieldName}")
    public RJQ GetCommonDropDownDatas(@PathVariable("TableName") String TableName, @PathVariable("ValueFieldName") String ValueFieldName, @PathVariable("NameFieldName") String NameFieldName) throws Exception {
        List<PageData> table = (List<PageData>) commonService.getTables(TableName, ValueFieldName, NameFieldName);
        return RJQ.ok().put("data", table);
    }

    //    字典表下拉框
    @ResponseBody
    @RequestMapping("/GetCommonDropDownDatady/{TableName}/{ValueFieldName}/{NameFieldName}/{dict_type}")
    public RJQ GetCommonDropDownDataZd(@PathVariable("TableName") String TableName, @PathVariable("ValueFieldName") String ValueFieldName,
                                       @PathVariable("NameFieldName") String NameFieldName, @PathVariable("dict_type") String dict_type) throws Exception {
        List<PageData> table = (List<PageData>) commonService.getTableZd(TableName, ValueFieldName, NameFieldName, dict_type);
        return RJQ.ok().put("data", table);
    }

    //    字典表下拉框
    @ResponseBody
    @RequestMapping("/GetCommonDropDownDatadyx/{TableName}/{ValueFieldName}/{NameFieldName}/{dict_type}")
    public RJQ GetCommonDropDownDataZdx(@PathVariable("TableName") String TableName, @PathVariable("ValueFieldName") String ValueFieldName,
                                        @PathVariable("NameFieldName") String NameFieldName, @PathVariable("dict_type") String dict_type) throws Exception {
        List<PageData> table = (List<PageData>) commonService.getTableZdx(TableName, ValueFieldName, NameFieldName, dict_type);
        return RJQ.ok().put("data", table);
    }

    //    字典表下拉框
    @ResponseBody
    @RequestMapping("/GetCommonDropDownById/{TableName}/{ValueFieldName}/{NameFieldName}/{key}/{value}")
    public RJQ GetCommonDropDownById(@PathVariable("TableName") String TableName, @PathVariable("ValueFieldName") String ValueFieldName,
                                     @PathVariable("NameFieldName") String NameFieldName, @PathVariable("key") String key, @PathVariable("value") String value) throws Exception {
        List<PageData> table = (List<PageData>) commonService.GetCommonDropDownById(TableName, ValueFieldName, NameFieldName, key, value);
        return RJQ.ok().put("data", table);
    }


}
