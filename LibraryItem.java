import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class LibraryItem {
    private int id;
    private String title;
    private int year;
    private List<Review> reviews;  // List to store reviews
    private boolean available;
    private LocalDate dueDate; // Using LocalDate for due date
    private String type;  // Add type to indicate the type of item (Book, Movie, Journal)

    public LibraryItem(int id, String title, int year, String type) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.reviews = new ArrayList<>();  // Initialize the review list
        this.available = true;
        this.dueDate = null;
        this.type = type;  // Set type during initialization
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public boolean isAvailable() { return available; }
    public LocalDate getDueDate() { return dueDate; } // Use LocalDate for due date
    public String getType() { return type; }  // Getter for type

    // Getters for reviews
    public double getAverageRating() {
        if (reviews.isEmpty()) return 0.0;
        double total = 0;
        for (Review review : reviews) {
            total += review.getRating();
        }
        return total / reviews.size();
    }

    public int getNumberOfReviewers() {
        return reviews.size();
    }

    // Updated to take a LocalDate instead of int
    public void borrowItem(LocalDate dueDate) {
        this.available = false;
        this.dueDate = dueDate;
        System.out.println("This item's due date is " + getFormattedDueDate());
    }

    public void returnItem() {
        this.available = true;
        this.dueDate = null;
    }

    // Add a review to the library item
    public void rateItem(double rating) {
        reviews.add(new Review("Anonymous", rating));  // You can add a reviewer name here as well
        System.out.println("This item's rating is " + String.format("%.2f", getAverageRating()));
    }

    public String getFormattedDueDate() {
        if (dueDate == null) return "N/A";
        return dueDate.toString();  // return formatted due date in ISO format
    }

    public abstract void displayDetails();
    public abstract int getMaxBorrowDays();
}
