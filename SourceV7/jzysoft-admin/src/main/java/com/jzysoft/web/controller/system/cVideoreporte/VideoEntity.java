package com.jzysoft.web.controller.system.cVideoreporte;

import com.jzysoft.common.annotation.Excel;

import java.util.Date;

public class VideoEntity {

    @Excel(name = "姓名")
    private String username;

    @Excel(name = "课程堂名")
    private String mname;

    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date createtime;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
