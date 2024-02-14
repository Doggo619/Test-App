package com.base.unscramblewords.fireStoreDatabase;

public class Students {
    private String studentName;

    public Students() {
    }

    public Students(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
