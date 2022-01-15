/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.jzysoft.commonmoudle.lib.util;


import com.jzysoft.commonmoudle.lib.config.page.PageData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class RJQ extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
//	private int rows; //每页显示记录数
//	private int total;		//总页数
//	private int records;	//总记录数
//	private int page;	//当前页
//	private List<?> root;

//	public R(Page page, List<PageData> root) {
//		put("code", 0);
//		put("msg", "success");
//		put("rows",page.getRows());
//		put("total",page.getTotal());
//		put("records",page.getRecords());
//		put("page",page.getPage());
//		put("root",root);
//	}

    public static RJQ ok(PageJQ page, List<PageData> root) {
        RJQ r = new RJQ();
        r.put("code", 0);
        r.put("msg", "success");
//		r.put("rows",page.getRows());
        r.put("total", page.getTotal());
        r.put("records", page.getRecords());
        r.put("page", page.getPage());
        r.put("rows", root);
        return r;
    }

    public RJQ() {
        put("code", 0);
        put("msg", "success");
    }

    public static RJQ error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static RJQ error(String msg) {
        return error(500, msg);
    }

    public static RJQ error(int code, String msg) {
        RJQ r = new RJQ();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static RJQ ok(String msg) {
        RJQ r = new RJQ();
        r.put("msg", msg);
        return r;
    }

    public static RJQ ok(Map<String, Object> map) {
        RJQ r = new RJQ();
        r.putAll(map);
        return r;
    }

    public static RJQ ok() {
        return new RJQ();
    }

    @Override
    public RJQ put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
