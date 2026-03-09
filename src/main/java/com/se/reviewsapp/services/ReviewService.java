package com.se.reviewsapp.services;

import com.se.reviewsapp.config.Config;
import com.se.reviewsapp.dtos.PerformanceDTO;
import com.se.reviewsapp.dtos.ReviewDto;
import com.se.reviewsapp.dtos.ReviewMapper;

import com.se.reviewsapp.exceptions.ReviewServiceException;
import com.se.reviewsapp.repositories.ReviewRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    final Config config;
    final ReviewRepository reviewRepository;
    final ReviewMapper reviewMapper;

    //CONSTRUCTOR
    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper, Config config) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.config = config;
    }

    //SAVE
    public ReviewDto save(ReviewDto reviewDto){

        if (reviewDto.getPerformanceId() != 0){
            config.webClient().get()
                    .uri("/performances/getbyid/" + reviewDto.getPerformanceId())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                        throw new ReviewServiceException("Error: no performance found with ID "
                                + reviewDto.getPerformanceId());
                    })
                    .bodyToMono(PerformanceDTO.class)
                    .block();

        }

        return reviewMapper.mapToDto(reviewRepository.save(reviewMapper.mapFromDto(reviewDto)));
    }

    //DELETE BY ID
    public void deleteById(String id){
        if (reviewRepository.findReviewById(id).isPresent()) {
        reviewRepository.deleteReviewById(id);
        }
        else throw new ReviewServiceException("No review with ID "+ " found.");
    }

    //DELETE ALL
    public void deleteAllReviews(){
        reviewRepository.deleteAll();
    }

    //DELETE BY REVIEW'S PERFORMANCE ID
    public void deleteByPerformanceId(Long id){
            reviewRepository.deleteReviewsByPerformanceId(id);
        }

    //FIND ALL
    public Iterable<ReviewDto> findAll(){
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::mapToDto)
                .toList();
    }

    //FIND BY ID
    public ReviewDto findById(String id){
        if (reviewRepository.findReviewById(id).isPresent()) {
            return reviewMapper.mapToDto(reviewRepository.findReviewById(id).get());
        }
        else throw new ReviewServiceException("No review with ID " + id + " found.");
    }

    //FIND BY PERFORMANCE ID
    public Iterable<ReviewDto> findByPerformanceId(Long id){
        return reviewRepository.findReviewsByPerformanceId(id)
                .stream()
                .map(reviewMapper::mapToDto)
                .toList();
    }

    //FIND BY RATING HIGHER THAN
    public Iterable<ReviewDto> findByRatingGreaterThanEqual(Long rating){
        return reviewRepository.findReviewsByReviewRatingGreaterThanEqual(rating)
                .stream()
                .map(reviewMapper::mapToDto)
                .toList();
    }

    //FIND BY RATING LESS THAN
    public Iterable<ReviewDto> findByRatingLessThanEqual(Long rating){
        return reviewRepository.findReviewsByReviewRatingLessThanEqual(rating)
                .stream()
                .map(reviewMapper::mapToDto)
                .toList();
    }

    //FIND BY TITLE
    public Iterable<ReviewDto> findByTitle(String title){
        return reviewRepository.findReviewsByReviewTitleContainsIgnoreCase(title)
                .stream()
                .map(reviewMapper::mapToDto)
                .toList();
    }

    //FIND BY TEXT
    public Iterable<ReviewDto> findByText(String text){
        return reviewRepository.findReviewsByReviewTextContainsIgnoreCase(text)
                .stream()
                .map(reviewMapper::mapToDto)
                .toList();
    }

    //FIND BY TITLE AND TEXT
    public Iterable<ReviewDto> findByTitleAndText(String title, String text){
        return reviewRepository.findReviewsByReviewTitleContainsIgnoreCaseAndReviewTextContainsIgnoreCase(title, text)
                .stream()
                .map(reviewMapper::mapToDto)
                .toList();
    }

    //CHECK IF EXISTS BY ID
    public boolean existsById(String id){
        return reviewRepository.existsById(id);
    }

}

