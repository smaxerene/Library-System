public class Movie extends LibraryItem {
    private String director;

    public Movie(int id, String title, int year, String director) {
        super(id, title, year, "Movie");  // Pass "Movie" as the type
        this.director = director;
    }

    @Override
    public void displayDetails() {
        System.out.println("\nSelected item is:");
        System.out.println("Type: " + getType());  // Use getType() to get the type from the LibraryItem class
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Year: " + getYear());
        System.out.println("Average Rating: " + String.format("%.2f", getAverageRating()));
        System.out.println("Number of Reviewers: " + getNumberOfReviewers());
        System.out.println("Status: " + (isAvailable() ? "Available" : "On loan (Due date: " + getFormattedDueDate() + ")"));
        System.out.println("Director: " + director);
        System.out.println("Max number of days for borrowing: 7 ");
    }

    @Override
    public int getMaxBorrowDays() {
        return 7;  // Maximum borrow days for a movie
    }
}
