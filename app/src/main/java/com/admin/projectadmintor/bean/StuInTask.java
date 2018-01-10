package com.admin.projectadmintor.bean;

/**
 * Created by admin on 2017/12/31.
 */

public class StuInTask {
    private String studentID;
    private String name;
    public StuInTask(){

    }
    public StuInTask(String studentID, String name){
        this.studentID=studentID;
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
