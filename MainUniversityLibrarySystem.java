import java.util.*;
import java.time.LocalDate;
import java.util.Random;

public class MainUniversityLibrarySystem {

    private static void displayInfo() {
        System.out.println("-----------------------------------------");
        System.out.println("Assignment 2 Semester 1 2025");
        System.out.println("Submitted by: Quilat, Samantha  23017224");
        System.out.println("------------------------------------------");
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);  // Try parsing the input to an integer
            return true;             // If successful, return true
        } catch (NumberFormatException e) {
            return false;            // If parsing fails, return false
        }
    }

    public static void main(String[] args) {
        displayInfo();

        Scanner scanner = new Scanner(System.in);
        LibrarySystem system = new LibrarySystem();
        system.loadLibrary("library.txt");

        system.listItems();

        while (true) {
            System.out.println("\nEnter 'q' to quit,");
            System.out.println("or enter 's' to sort (first by average rating and then by id) and display all items,");
            System.out.println("or enter 'i' to search by ID,");
            System.out.print("or enter any other key to search by phrase in the title: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "q":
                    scanner.close();
                    return;

                case "s":
                    system.sortItems();
                    break;

                case "i":
                    // Search by ID
                    boolean searching = true;
                    while (searching) {
                        System.out.print("\nEnter ID to start search, or enter 'b' to go back to choose search method: ");
                        String idInput = scanner.nextLine();

                        if (idInput.equals("b")) {
                            searching = false;
                            break;
                        }

                        try {
                            int id = Integer.parseInt(idInput);
                            List<LibraryItem> results = system.searchByIdOrTitle(String.valueOf(id));

                            if (results.isEmpty()) {
                                System.out.println("No matching items found.");
                            } else {
                                for (int i = 0; i < results.size(); i++) {
                                    System.out.println("ID: " + results.get(i).getId() + "     Type: " + results.get(i).getClass().getSimpleName() + "     Title: " + results.get(i).getTitle());
                                    System.out.println("");
                                }

                                try {
                                    System.out.print("Enter 'i' to search another item by ID, or enter any other key to select this item: ");
                                    String option = scanner.nextLine();

                                    if (option.equals("i")) {
                                        continue;  // Go back to search another item by ID
                                    } else {
                                        int selectedIndex = 0;  // Since we only display one result at a time, default is the first one
                                        LibraryItem selectedItem = results.get(selectedIndex);

                                        selectedItem.displayDetails();

                                        boolean itemInteraction = true;
                                        while (itemInteraction) {
                                            if (selectedItem.isAvailable()) {
                                                System.out.print("\nEnter 'b' to borrow the item, enter 'a' to rate the item, or enter any other key to restart: ");
                                            } else {
                                                System.out.print("\nEnter 'r' to return the item, enter 'a' to rate the item, or enter any other key to restart: ");
                                            }

                                            String itemOption = scanner.nextLine();

                                            if (itemOption.equals("b")) {
                                                if (selectedItem.isAvailable()) {
                                                    // Fixed number of days to borrow (e.g., 7 days)
                                                    Random random = new Random();
                                                    int borrowDays = random.nextInt(28) + 1;

                                                    // Borrow the item and set the due date (based on current date)
                                                    LocalDate dueDate = LocalDate.now().plusDays(borrowDays);
                                                    selectedItem.borrowItem(dueDate);

                                                    // Display updated item details after borrowing
                                                    selectedItem.displayDetails();

                                                } else {
                                                    System.out.println("\nThis item is currently on loan.");
                                                }
                                            } else if (itemOption.equals("r")) {
                                                selectedItem.returnItem();
                                                System.out.println("\nThis item is returned.");
                                                selectedItem.displayDetails();

                                            } else if (itemOption.equals("a")) {
                                                System.out.print("\nPlease enter your rating (0 - 10): ");
                                                double rating = Double.parseDouble(scanner.nextLine());
                                                if (rating >= 0 && rating <= 10) {
                                                    selectedItem.rateItem(rating);
                                                    selectedItem.displayDetails();
                                                } else {
                                                    System.out.println("Invalid rating. Please enter a number between 0 and 10.");
                                                }
                                            } else {
                                                // Restart or exit this specific item interaction
                                                itemInteraction = false;
                                                break;  // Break out of item interaction loop to go back to main menu
                                            }
                                        }

                                        // After exiting the item interaction loop, break out of the search loop as well
                                        searching = false;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("Invalid selection. Please choose a valid item.");
                                } catch (Exception e) {
                                    System.out.println("An error occurred. Please try again.");
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid ID format. Please enter a valid ID or 'b' to go back.");
                        }
                    }
                    break;

                default:
                    // Search by phrase in title
                    System.out.print("\nEnter phrase in title to start search, or enter 'b' to go back to choose search method: ");
                    String keyword = scanner.nextLine();

                    List<LibraryItem> results = system.searchByIdOrTitle(keyword.toLowerCase());

                    if (results.isEmpty()) {
                        System.out.println("No items found.");
                    } else {
                        for (int i = 0; i < results.size(); i++) {
                            System.out.println("* " + (i + 1) + ": ");
                            System.out.println("ID: " + results.get(i).getId() + "     Type: " + results.get(i).getClass().getSimpleName() + "     Title: " + results.get(i).getTitle());
                        }

                        System.out.print("\nEnter item number to select item, or enter any other key to continue searching: ");
                        String selection = scanner.nextLine();

                        if (isInteger(selection)) {
                            int itemNumber = Integer.parseInt(selection);
                            if (itemNumber > 0 && itemNumber <= results.size()) {
                                LibraryItem selectedItem = results.get(itemNumber - 1);
                                selectedItem.displayDetails();

                                boolean itemInteraction = true;
                                while (itemInteraction) {
                                    if (selectedItem.isAvailable()) {
                                        System.out.print("\nEnter 'b' to borrow the item, enter 'a' to rate the item, or enter any other key to restart: ");
                                    } else {
                                        System.out.print("\nEnter 'r' to return the item, enter 'a' to rate the item, or enter any other key to restart: ");
                                    }

                                    String itemOption = scanner.nextLine();

                                    if (itemOption.equals("b")) {
                                        if (selectedItem.isAvailable()) {
                                            // Calculate due date (current date + max days)
                                            LocalDate dueDate = LocalDate.now().plusDays(selectedItem.getMaxBorrowDays());
                                            selectedItem.borrowItem(dueDate);

                                            // Display the new details after borrowing
                                            selectedItem.displayDetails();
                                        } else {
                                            System.out.println("\nThis item is currently on loan.");
                                        }
                                    } else if (itemOption.equals("r")) {
                                        selectedItem.returnItem();
                                        System.out.println("\nThis item is returned.");
                                        selectedItem.displayDetails();
                                    } else if (itemOption.equals("a")) {
                                        System.out.print("\nPlease enter your rating (0 - 10): ");
                                        double rating = Double.parseDouble(scanner.nextLine());
                                        if (rating >= 0 && rating <= 10) {
                                            selectedItem.rateItem(rating);
                                            System.out.println("This item's new average rating is " + selectedItem.getAverageRating());
                                            selectedItem.displayDetails();
                                        } else {
                                            System.out.println("Invalid rating. Please enter a number between 0 and 10.");
                                        }
                                    } else {
                                        // Restart or exit this specific item interaction
                                        itemInteraction = false;
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }
}
