package com.pavi.MovieRev;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") // Allow frontend
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    // what ever we get in request body we will like to convert it to a map of key string and value string named as payload
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){

        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }

    // New endpoint to fetch a review by ID
    /*@GetMapping("/{id}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable ObjectId id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.isPresent() ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }*/

    @GetMapping("/{movieid}")
    public ResponseEntity<List<Review>> getReviewsByMovieId(@PathVariable String movieid) {
        List<Review> reviews = reviewService.getReviewsByMovieId(movieid);
        return ResponseEntity.ok(reviews);
    }
}
