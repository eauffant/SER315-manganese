import java.util.HashMap;

public class RaceReviewDatabase {

    HashMap<String, Review> raceReviews = new HashMap<>();

    public void addReview(Review review) {
        raceReviews.put(review.getReviewId(), review);
    }

    public Review getReview(String reviewId) {
        return raceReviews.get(reviewId);
    }

}
