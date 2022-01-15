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
public class R extends HashMap<String, Object> {
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

    public static R ok(PageJQ page, List<PageData> root) {
        R r = new R();
        r.put("code", 0);
        r.put("msg", "success");
//		r.put("rows",page.getRows());
        r.put("total", page.getTotal());
        r.put("records", page.getRecords());
        r.put("page", page.getPage());
        r.put("rows", root);
        return r;
    }

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
