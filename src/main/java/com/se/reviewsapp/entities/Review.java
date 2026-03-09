package com.se.reviewsapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Review {

    //@Id
    @Id
    private String id;
    private Long performanceId;
    private String reviewTitle;
    private String reviewText;
    private float reviewRating;


    public Review(String id, Long performanceId, String reviewTitle, String reviewText, float reviewRating) {
        this.id = id;
        this.performanceId = performanceId;
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
        this.reviewRating = reviewRating;
    }

    //GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(float reviewRating) {
        this.reviewRating = reviewRating;
    }
}

