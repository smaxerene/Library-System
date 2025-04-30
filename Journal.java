public class Journal extends LibraryItem {
    private int volume;
    private int number;

    public Journal(int id, String title, int year, int volume, int number) {
        super(id, title, year, "Journal");  // Pass "Journal" as the type
        this.volume = volume;
        this.number = number;
    }

    @Override
    public void displayDetails() {
        System.out.println("\nSelected item is:");
        System.out.println("Type: " + getType());  // Use getType() to get the type from the LibraryItem class
        System.out.println("Title: " + getTitle());
        System.out.println("ID: " + getId());
        System.out.println("Year: " + getYear());
        System.out.println("Average Rating: " + String.format("%.2f", getAverageRating()));
        System.out.println("Number of Reviewers: " + getNumberOfReviewers());
        System.out.println("Status: " + (isAvailable() ? "Available" : "On loan \nDue date: " + getFormattedDueDate()));
        System.out.println("Volume: " + volume);
        System.out.println("Number: " + number);
        System.out.println("Max number of days for borrowing: 14");
    }

    @Override
    public int getMaxBorrowDays() {
        return 14;  // Maximum borrow days for a journal
    }
}
