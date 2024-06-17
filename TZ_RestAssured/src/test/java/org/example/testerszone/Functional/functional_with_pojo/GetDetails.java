package org.example.testerszone.Functional.functional_with_pojo;

public class GetDetails {

    private String dashboard;
    private Courses courses;
    private String instructor;
    private String channel;
    private String expertise;



    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getDashboard() {
        return dashboard;
    }

    public void setDashboard(String dashboard) {
        this.dashboard = dashboard;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
         this.courses = courses;
    }
}
