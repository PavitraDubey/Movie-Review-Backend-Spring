package com.pavi.MovieRev;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    Optional<Review> findById(ObjectId id);

    List<Review> findByMovieid(String movieid);  // Fetch reviews by movieid
}
