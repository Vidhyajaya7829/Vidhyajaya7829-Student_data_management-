import java.util.Scanner;

// Node class to represent each book in the library
class BookNode {
    String title;
    String author;
    String isbn;
    BookNode next;  // Points to the next node (book)

    // Constructor to initialize a book node
    public BookNode(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.next = null;
    }
}

// LinkedList class to manage books in the library
class LibraryLinkedList {
    private BookNode head;  // Points to the first book in the list

    // Constructor to initialize an empty linked list
    public LibraryLinkedList() {
        this.head = null;
    }

    // Method to add a book at the end of the list
    public void addBook(String title, String author, String isbn) {
        BookNode newBook = new BookNode(title, author, isbn);
        if (head == null) {  // If the list is empty, set the new book as the head
            head = newBook;
        } else {
            BookNode lastBook = head;
            while (lastBook.next != null) {  // Traverse to the end of the list
                lastBook = lastBook.next;
            }
            lastBook.next = newBook;  // Append the new book to the list
        }
        System.out.println("Book \"" + title + "\" added successfully.");
    }

    // Method to display all books in the library
    public void displayBooks() {
        if (head == null) {
            System.out.println("No books in the library.");
        } else {
            BookNode current = head;
            while (current != null) {
                System.out.println("Title: " + current.title + ", Author: " + current.author + ", ISBN: " + current.isbn);
                current = current.next;
            }
        }
    }

    // Method to search for a book by title
    public void searchBook(String title) {
        BookNode current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                System.out.println("Book found - Title: " + current.title + ", Author: " + current.author + ", ISBN: " + current.isbn);
                return;
            }
            current = current.next;
        }
        System.out.println("Book not found.");
    }

    // Method to remove a book by ISBN
    public void removeBook(String isbn) {
        if (head == null) {
            System.out.println("The library is empty.");
            return;
        }

        // If the book to be removed is the first one
        if (head.isbn.equals(isbn)) {
            System.out.println("Book \"" + head.title + "\" removed.");
            head = head.next;  // Update head to the next book
            return;
        }

        // Traverse to find the book to remove
        BookNode previous = null;
        BookNode current = head;
        while (current != null) {
            if (current.isbn.equals(isbn)) {
                System.out.println("Book \"" + current.title + "\" removed.");
                previous.next = current.next;  // Skip the current node
                return;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("Book with the given ISBN not found.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryLinkedList library = new LibraryLinkedList();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Remove Book by ISBN");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(title, author, isbn);
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3:
                    System.out.print("Enter the title of the book to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchBook(searchTitle);
                    break;
                case 4:
                    System.out.print("Enter the ISBN of the book to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}