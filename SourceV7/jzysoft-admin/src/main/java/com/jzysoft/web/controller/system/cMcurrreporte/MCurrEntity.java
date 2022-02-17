package com.jzysoft.web.controller.system.cMcurrreporte;

import com.jzysoft.common.annotation.Excel;

import java.util.Date;

public class MCurrEntity {
    @Excel(name = "姓名")
    private String username;

    @Excel(name = "微课堂名")
    private String mname;

    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date loginDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
