package com.se.reviewsapp.repositories;

import java.util.Collection;
import java.util.Optional;

import com.se.reviewsapp.entities.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, Long> {


    Optional<Review> findReviewById(String id);

    boolean existsById(String id);

    boolean existsByPerformanceId(Long id);

    void deleteReviewById(String id);

    void deleteReviewsByPerformanceId(Long id);

    Collection<Review> findReviewsByPerformanceId(Long id);

    Collection<Review> findReviewsByReviewRatingGreaterThanEqual(long rating);

    Collection<Review> findReviewsByReviewRatingLessThanEqual(long rating);

    Collection<Review> findReviewsByReviewTitleContainsIgnoreCase(String query);

    Collection<Review> findReviewsByReviewTextContainsIgnoreCase(String query);

    Collection<Review> findReviewsByReviewTitleContainsIgnoreCaseAndReviewTextContainsIgnoreCase(String title, String text);

}
