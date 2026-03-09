package com.se.reviewsapp.dtos;
import com.se.reviewsapp.entities.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {
    public ReviewDto mapToDto(Review review) {

        return new ReviewDto
                (review.getId(),
                review.getPerformanceId(),
                review.getReviewTitle(),
                review.getReviewText(),
                review.getReviewRating());
    }

    public Review mapFromDto(ReviewDto reviewDto) {

        return new Review
                (reviewDto.getId(),
                reviewDto.getPerformanceId(),
                reviewDto.getTitle(),
                reviewDto.getText(),
                reviewDto.getRating());
    }
}