package com.admin.projectadmintor.mvp.bean;

/**
 * Created by apple on 2018/1/1.
 */

public class Schedule {
    private String scheduleid;
    private String projectid;
    private String memberid;
    private String description;
    private String deadline;
    private String completestate;

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCompletestate() {
        return completestate;
    }

    public void setCompletestate(String completestate) {
        this.completestate = completestate;
    }
}
