package com.admin.projectadmintor.mvp.bean;

/**
 * Created by apple on 2018/1/5.
 */

public class SignInfo {
    private String signid;
    private String memberid;
    private String projectid;
    private String signdate;

    public String getSignid() {
        return signid;
    }

    public void setSignid(String signid) {
        this.signid = signid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getSigndate() {
        return signdate;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }
}
