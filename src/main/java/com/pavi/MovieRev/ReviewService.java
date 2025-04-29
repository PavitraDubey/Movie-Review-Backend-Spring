package com.pavi.MovieRev;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId){

        // .insert returns the data that you push inside the database
        Review review=reviewRepository.insert(new Review(imdbId, reviewBody));

        // .first is to make sure that we are getting a single movie and we are updating that
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }

    // New method to fetch review by ID
    public Optional<Review> getReviewById(ObjectId id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviewsByMovieId(String movieid) {
        return reviewRepository.findByMovieid(movieid);
    }
}
