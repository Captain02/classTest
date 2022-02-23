package com.jzysoft.web.controller.system.cMcurr;

import java.util.List;

public class KnowlegeTree {
    private Integer id;
    private String name;
    private String title;
    private List<KnowlegeTree> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<KnowlegeTree> getChildren() {
        return children;
    }

    public void setChildren(List<KnowlegeTree> children) {
        this.children = children;
    }
}
