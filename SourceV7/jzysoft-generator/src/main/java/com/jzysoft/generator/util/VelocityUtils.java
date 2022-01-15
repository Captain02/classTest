package com.jzysoft.generator.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.velocity.VelocityContext;
import com.alibaba.fastjson.JSONObject;
import com.jzysoft.common.constant.GenConstants;
import com.jzysoft.common.utils.DateUtils;
import com.jzysoft.common.utils.StringUtils;
import com.jzysoft.generator.config.GenConfig;
import com.jzysoft.generator.domain.GenTable;
import com.jzysoft.generator.domain.GenTableColumn;

public class VelocityUtils
{
    /** 项目空间路径 */
    private static final String PROJECT_PATH = "java";

    /** mybatis空间路径 */
    private static final String MYBATIS_PATH = "mapper";

    /** html空间路径 */
    private static final String TEMPLATES_PATH = "html";
    /** html空间路径 */
    private static final String JS_PATH = "js";

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable)
    {
        String moduleName = genTable.getModuleName();
        String classname = genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getTplCategory();
        String functionName = genTable.getFunctionName();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getTplCategory());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
        velocityContext.put("moduleName", genTable.getModuleName());
        velocityContext.put("classname", genTable.getBusinessName());
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        velocityContext.put("packageName", packageName);
        velocityContext.put("author", genTable.getFunctionAuthor());
        velocityContext.put("datetime", DateUtils.getDate());
        velocityContext.put("pkColumn", genTable.getPkColumn());
        velocityContext.put("importList", getImportList(genTable.getColumns()));
        velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, classname));
        velocityContext.put("columns", genTable.getColumns());
        velocityContext.put("table", genTable);
        if (GenConstants.TPL_TREE.equals(tplCategory))
        {
            setTreeVelocityContext(velocityContext, genTable);
        }
        return velocityContext;
    }

    public static void setTreeVelocityContext(VelocityContext context, GenTable genTable)
    {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeCode = getTreecode(paramsObj);
        String treeParentCode = getTreeParentCode(paramsObj);
        String treeName = getTreeName(paramsObj);

        context.put("treeCode", treeCode);
        context.put("treeParentCode", treeParentCode);
        context.put("treeName", treeName);
        context.put("expandColumn", getExpandColumn(genTable));
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE))
        {
            context.put("tree_parent_code", paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        if (paramsObj.containsKey(GenConstants.TREE_NAME))
        {
            context.put("tree_name", paramsObj.getString(GenConstants.TREE_NAME));
        }
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory)
    {
        List<String> templates = new ArrayList<String>();
//        实体类
//        templates.add("vm/java/domain.java.vm");
//        templates.add("vm/java/mapper.java.vm");
//        java
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
//        xml
        templates.add("vm/xml/mapper.xml.vm");
//        html
//        if (GenConstants.TPL_CRUD.equals(tplCategory))
//        {
//            templates.add("vm/html/list.html.vm");
//        }
//        else if (GenConstants.TPL_TREE.equals(tplCategory))
//        {
////            templates.add("vm/html/tree.html.vm");
////            templates.add("vm/html/list-tree.html.vm");
//        }
        templates.add("vm/html/lists.html.vm");
//        templates.add("vm/html/add.html.vm");
//        templates.add("vm/html/edit.html.vm");
//        js
        templates.add("vm/js/list.js.vm");
//        sql
        templates.add("vm/sql/sql.vm");
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenTable genTable)
    {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();

        // 模块名
        String moduleName = genTable.getModuleName();
        // 大写类名
        String className = genTable.getClassName();
//        小写
        String classname = StringUtils.uncapitalize(genTable.getClassName());
        // 业务名称
//        String classname = genTable.getBusinessName();

        String javaPath ="Java" + "/" + classname;
        String mybatisPath = MYBATIS_PATH + "/" + classname;
        String htmlPath = TEMPLATES_PATH + "/"  + classname;
        String jsPath = JS_PATH + "/" + classname;
//实体类
        if (template.contains("domain.java.vm"))
        {
            fileName = StringUtils.format("{}/domain/{}.java", javaPath, className);
        }
//        接口
        else if (template.contains("mapper.java.vm"))
        {
            fileName = StringUtils.format("{}/mapper/{}Mapper.java", javaPath, className);
        }
//        java
        else if (template.contains("controller.java.vm"))
        {
            fileName = StringUtils.format("{}/{}Controller.java", javaPath, className);
        }
        else if (template.contains("service.java.vm"))
        {
            fileName = StringUtils.format("{}/{}Service.java", javaPath, className);
        }
        else if (template.contains("serviceImpl.java.vm"))
        {
            fileName = StringUtils.format("{}/{}ServiceImpl.java", javaPath, className);
        }
//        xml
        else if (template.contains("mapper.xml.vm"))
        {
            fileName = StringUtils.format("{}/{}Mapper.xml", mybatisPath, className);
        }
//        html
        else if (template.contains("lists.html.vm"))
        {
            fileName = StringUtils.format("{}/{}.html", htmlPath, classname);
        }
        else if (template.contains("list.html.vm"))
        {
            fileName = StringUtils.format("{}/{}.html", htmlPath, classname);
        }
        else if (template.contains("list-tree.html.vm"))
        {
            fileName = StringUtils.format("{}/{}.html", htmlPath, classname);
        }
        else if (template.contains("tree.html.vm"))
        {
            fileName = StringUtils.format("{}/tree.html", htmlPath);
        }
        else if (template.contains("add.html.vm"))
        {
            fileName = StringUtils.format("{}/add.html", htmlPath);
        }
        else if (template.contains("edit.html.vm"))
        {
            fileName = StringUtils.format("{}/edit.html", htmlPath);
        }
        else if (template.contains("list.js.vm"))
        {
            fileName = StringUtils.format("{}/{}.js", jsPath, classname);
        }
//        sql
        else if (template.contains("sql.vm"))
        {
            fileName = classname + "Menu.sql";
        }
        return fileName;
    }

    /**
     * 获取项目文件路径
     *
     * @return 路径
     */
    public static String getProjectPath()
    {
        String packageName = GenConfig.getPackageName();
        StringBuffer projectPath = new StringBuffer();
        projectPath.append("main/java/");
        projectPath.append(packageName.replace(".", "/"));
        projectPath.append("/");
        return projectPath.toString();
    }

    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        String basePackage = StringUtils.substring(packageName, 0, lastIndex);
        return basePackage;
    }

    /**
     * 根据列类型获取导入包
     *
     * @param column 列集合
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(List<GenTableColumn> columns)
    {
        HashSet<String> importList = new HashSet<String>();
        for (GenTableColumn column : columns)
        {
            if (!column.isSuperColumn() && GenConstants.TYPE_DATE.equals(column.getJavaType()))
            {
                importList.add("java.util.Date");
            }
            else if (!column.isSuperColumn() && GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType()))
            {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 获取权限前缀
     *
     * @param moduleName 模块名称
     * @param classname 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String classname)
    {
        return StringUtils.format("{}:{}", moduleName, classname);

    }

    /**
     * 获取树编码
     *
     * @param options 生成其他选项
     * @return 树编码
     */
    public static String getTreecode(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_CODE))
        {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_CODE));
        }
        return "";
    }

    /**
     * 获取树父编码
     *
     * @param options 生成其他选项
     * @return 树父编码
     */
    public static String getTreeParentCode(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE))
        {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_PARENT_CODE));
        }
        return "";
    }

    /**
     * 获取树名称
     *
     * @param options 生成其他选项
     * @return 树名称
     */
    public static String getTreeName(JSONObject paramsObj)
    {
        if (paramsObj.containsKey(GenConstants.TREE_NAME))
        {
            return StringUtils.toCamelCase(paramsObj.getString(GenConstants.TREE_NAME));
        }
        return "";
    }

    /**
     * 获取需要在哪一列上面显示展开按钮
     *
     * @param genTable 业务表对象
     * @return 展开按钮列序号
     */
    public static int getExpandColumn(GenTable genTable)
    {
        String options = genTable.getOptions();
        JSONObject paramsObj = JSONObject.parseObject(options);
        String treeName = paramsObj.getString(GenConstants.TREE_NAME);
        int num = 0;
        for (GenTableColumn column : genTable.getColumns())
        {
            if (column.isList())
            {
                num++;
                String columnName = column.getColumnName();
                if (columnName.equals(treeName))
                {
                    break;
                }
            }
        }
        return num;
    }
}