package com.server.pojo.entity;

public class Feedback {
    private String feedbackid;

    private String feedbackdetail;

    private String feedbackcustomer;

    private String feedbacktime;

    private String feedbackstate;

    public String getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(String feedbackid) {
        this.feedbackid = feedbackid == null ? null : feedbackid.trim();
    }

    public String getFeedbackdetail() {
        return feedbackdetail;
    }

    public void setFeedbackdetail(String feedbackdetail) {
        this.feedbackdetail = feedbackdetail == null ? null : feedbackdetail.trim();
    }

    public String getFeedbackcustomer() {
        return feedbackcustomer;
    }

    public void setFeedbackcustomer(String feedbackcustomer) {
        this.feedbackcustomer = feedbackcustomer == null ? null : feedbackcustomer.trim();
    }

    public String getFeedbacktime() {
        return feedbacktime;
    }

    public void setFeedbacktime(String feedbacktime) {
        this.feedbacktime = feedbacktime == null ? null : feedbacktime.trim();
    }

    public String getFeedbackstate() {
        return feedbackstate;
    }

    public void setFeedbackstate(String feedbackstate) {
        this.feedbackstate = feedbackstate == null ? null : feedbackstate.trim();
    }
}