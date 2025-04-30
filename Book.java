public class Book extends LibraryItem {
    private String author;
    private int numberOfPages;

    public Book(int id, String title, int year, String author, int numberOfPages) {
        super(id, title, year, "Book");  // Pass the type as "Book"
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public void displayDetails() {
        System.out.println("\nSelected item is:");
        System.out.println("Type: " + getType());  // Display the type (Book)
        System.out.println("Title: " + getTitle());
        System.out.println("ID: " + getId());
        System.out.println("Year: " + getYear());
        System.out.println("Average rating: " + String.format("%.1f", getAverageRating()));
        System.out.println("Number of reviewers: " + getNumberOfReviewers());
        System.out.println("Status: " + (isAvailable() ? "available" : "on loan"));
        System.out.println("Author: " + author);
        System.out.println("Number of pages: " + numberOfPages);
        System.out.println("Max number of days for borrowing: " + getMaxBorrowDays());
    }

    @Override
    public int getMaxBorrowDays() {
        return 28;  // Default borrow period for a book
    }
}
