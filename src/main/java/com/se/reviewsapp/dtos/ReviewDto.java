package com.se.reviewsapp.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public class ReviewDto {

    //DECLARATIONS AND VALIDATION

    @Id
    private String id;

    @NotNull(message = "MethodArgumentNotValidException: Performance ID cannot be blank.")
    private Long performanceId;

    @NotBlank(message = "MethodArgumentNotValidException: Review title cannot be blank.")
    private String title;

    @NotBlank(message = "MethodArgumentNotValidException: Review text cannot be blank.")
    private String text;

    @Min(value = 0, message = "MethodArgumentNotValidException: Rating cannot be lower than 0.")
    @Max(value = 5, message = "MethodArgumentNotValidException: Rating cannot be higher than 5.")
    @NotNull(message = "MethodArgumentNotValidException: Rating cannot be null")
    private float rating;


    //CONSTRUCTOR
    public ReviewDto(String id, Long performanceId, String title, String text, float rating) {
        this.id = id;
        this.performanceId = performanceId;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    //GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}



