// Class that holds and manages the library list

import java.util.*;
import java.io.*;

public class LibrarySystem {
    private ArrayList<LibraryItem> library;

    public LibrarySystem() {
        library = new ArrayList<>();
    }

    public void loadLibrary(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",", 6);
                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                String title = parts[2];
                int year = Integer.parseInt(parts[3]);

                switch (type) {
                    case "Book":
                        library.add(new Book(id, title, year, parts[4], Integer.parseInt(parts[5])));
                        break;
                    case "Movie":
                        library.add(new Movie(id, title, year, parts[4]));
                        break;
                    case "Journal":
                        library.add(new Journal(id, title, year, Integer.parseInt(parts[4]), Integer.parseInt(parts[5])));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: library.txt not found.");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void listItems() {
        System.out.println("\nList of all items in the library:");
        for (LibraryItem item : library) {
            System.out.printf("ID: %-10d Type: %-10s Title: %-30s\n", item.getId(), item.getClass().getSimpleName(), item.getTitle());
        }
    }

    public void listItemsSort() {
        System.out.println("\nList of all items in the library:");

        for (LibraryItem item : library) {
            System.out.printf("Average rating: %.2f   Number of reviewers: %d     ID: %-10d     Type: %-10s    Title: %-40s\n",
                    item.getAverageRating(), item.getNumberOfReviewers(), item.getId(), item.getClass().getSimpleName(), item.getTitle());
        }
    }

    public void sortItems() {
        library.sort(Comparator
                .comparing(LibraryItem::getAverageRating).reversed()
                .thenComparing(LibraryItem::getId));
        listItemsSort();
    }

    public List<LibraryItem> searchByIdOrTitle(String keyword) {
        keyword = keyword.toLowerCase();
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : library) {
            if (String.valueOf(item.getId()).equals(keyword) || item.getTitle().toLowerCase().contains(keyword)) {
                results.add(item);
            }
        }
        return results;
    }
}
