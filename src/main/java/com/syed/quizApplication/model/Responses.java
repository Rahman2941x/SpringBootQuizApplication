package com.syed.quizApplication.model;

public class Responses {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Responses(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    private Integer id;
    private String response;
}
