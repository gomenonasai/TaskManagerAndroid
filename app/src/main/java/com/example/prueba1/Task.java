package com.example.prueba1;

public class Task {

    private String title;
    private String description;
    private String date;
    private String priority;

    public Task(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getPriority() { return priority; }
}
