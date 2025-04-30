public class Review {
    private String reviewerName;
    private double rating;

    public Review(String reviewerName, double rating) {
        this.reviewerName = reviewerName;
        this.rating = rating;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public double getRating() {
        return rating;
    }
}


