package com.admin.projectadmintor.task.tasklist;

/**
 * Created by admin on 2018/1/2.
 */

public class Project {
    private String name;
    private String id;
    private  String content;

    public Project(String name, String id, String content) {
        this.name = name;
        this.id = id;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
