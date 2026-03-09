package com.se.reviewsapp.controllers;
import com.se.reviewsapp.dtos.ReviewDto;
import com.se.reviewsapp.exceptions.ReviewServiceException;
import com.se.reviewsapp.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping("/")
    String displayString() {
        return ("Homepage accessed");
    }

    //GET
    @GetMapping("/reviews")
    Iterable<ReviewDto> getReviews() {
        if (reviewService.findAll().iterator().hasNext()) {
            return reviewService.findAll();
        } else {
            throw new ReviewServiceException(("No records found."));
        }
    }

    //POST
    @PostMapping("/reviews/post")
    ReviewDto postReview(@Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.save(reviewDto);
    }

    //PUT
    @PutMapping("/reviews/put")
    ReviewDto putReview(@Valid @RequestBody ReviewDto reviewDto){
        return reviewService.save(reviewDto);
    }

    //DELETE BY ID
    @DeleteMapping("/reviews/deletebyid/{id}")
    void deleteReviewById(@PathVariable String id){
        if (reviewService.existsById(id)){
            reviewService.deleteById(id);
        }
    }

    //DELETE BY PERFORMANCE ID
    @DeleteMapping("/reviews/deletebyperformanceid/{id}")
    void deleteReviewByPerformanceId(@PathVariable Long id){

            reviewService.deleteByPerformanceId(id);
    }

    @DeleteMapping("/reviews/deleteall")
    void deleteAllReviews(){
        if (reviewService.findAll().iterator().hasNext()){
            reviewService.deleteAllReviews();
        }
        else throw new ReviewServiceException("No reviews found.");
    }

    //SEARCH FUNCTIONS

    //GET BY ID
    @GetMapping("/reviews/getbyid/{id}")
    ReviewDto getById(@PathVariable String id){
        return reviewService.findById(id);
    }

    //GET BY PERFORMANCE ID
    @GetMapping("/reviews/getbyperformanceid/{id}")
    Iterable<ReviewDto> getByPerformanceId(@PathVariable Long id){
        return reviewService.findByPerformanceId(id);
    }

    //GET BY RATING IF HIGHER THAN X
    @GetMapping("/reviews/findbyratinggreaterthan/{rating}")
    Iterable<ReviewDto> findByRatingGreaterThan(@PathVariable Long rating){

        Iterable<ReviewDto> reviews = reviewService.findByRatingGreaterThanEqual(rating);
        if (reviews.iterator().hasNext()){
            return reviews;
        }
        else throw new ReviewServiceException("No reviews with this rating found.");

    }

    //GET BY RATING IF LOWER THAN X
    @GetMapping("/reviews/findbyratinglessthan/{rating}")
    Iterable<ReviewDto> findByRatingLessThan(@PathVariable Long rating){

        Iterable<ReviewDto> reviews = reviewService.findByRatingLessThanEqual(rating);

        if (reviews.iterator().hasNext()){
            return reviews;
        }
        else throw new ReviewServiceException("No reviews with this rating found.");

    }

    //GET BY TITLE AND/OR TEXT CONTAINS
    @GetMapping("/reviews/search")
    Iterable<ReviewDto> getByQuery (@RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "text", required = false) String text) {

        if (title != null && text != null){
            Iterable<ReviewDto> reviews = reviewService.findByTitleAndText(title, text);
            if (reviews.iterator().hasNext()){
                return reviews;
            }
            else throw new ReviewServiceException("No reviews found with this title and text.");
        }

        else if (title != null){
            Iterable<ReviewDto> reviews = reviewService.findByTitle(title);
            if (reviews.iterator().hasNext()){
                return reviews;
            }
            else throw new ReviewServiceException("No reviews found with this title.");
        }

        else if (text != null){
            Iterable<ReviewDto> reviews = reviewService.findByTitle(text);
            if (reviews.iterator().hasNext()){
                return reviews;
            }
            else throw new ReviewServiceException("No reviews found with this text.");
        }

        else throw new ReviewServiceException("No reviews found.");
    }

    @ExceptionHandler(ReviewServiceException.class)
    ResponseEntity<String> exceptionHandler(ReviewServiceException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> notValidExceptionHandler(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

}
