/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class LibraryManagementSystem {

        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Lend Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Display Members");
            System.out.println("7. Remove Book");
            System.out.println("8. Remove Member");
            System.out.println("9. Search Book Information");
            System.out.println("10. Search Member Information");
            System.out.println("11. View Lending Information");
            System.out.println("12. Display Overdue Books");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add Book
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    Book book = new Book(isbn, title, author);
                    library.addBook(book);
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    // Register Member
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    Member member = new Member(memberId, name, address);
                    library.registerMember(member);
                    System.out.println("Member registered successfully!");
                    break;

                case 3:
                    // Lend Book
                    System.out.print("Enter Member ID: ");
                    int lendMemberId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter ISBN of the book to lend: ");
                    String lendIsbn = scanner.nextLine();
                    library.lendBook(lendMemberId, lendIsbn);
                    break;

                case 4:
                    // Return Book
                    System.out.print("Enter Member ID: ");
                    int returnMemberId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnMemberId, returnIsbn);
                    break;

                case 5:
                    // Display Books
                    library.displayBooks();
                    break;

                case 6:
                    // Display Members
                    library.displayMembers();
                    break;

                    
                case 7:
                    // Remove Book
                    System.out.print("Enter ISBN of the book to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    System.out.println("Book removed successfully!");
                    break;
                   
                case 8:
                    // Remove Member
                    System.out.print("Enter Member ID to remove: ");
                    int removeMemberId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    library.removeMember(removeMemberId);
                    System.out.println("Member removed successfully!");
                    break;
                    
                case 9:
                    // Search Book Information
                    System.out.print("Enter ISBN of the book to search: ");
                    String searchIsbn = scanner.nextLine();
                    Book foundBook = library.searchBookInformation(searchIsbn);
    
                    if (foundBook != null) {
                        System.out.println("Book Found:");
                        System.out.println(foundBook.toString());
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 10:
                    // Search Member Information
                    System.out.print("Enter Member ID to search: ");
                    int searchMemberId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Member foundMember = library.searchMemberInformation(searchMemberId);

                    if (foundMember != null) {
                        System.out.println("Member Found:");
                        System.out.println(foundMember.toString());
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;
                    
                case 11:
                    // View Lending Information
                    System.out.println("1. View Lending Information by Member ID");
                    System.out.println("2. View Lending Information by ISBN");
                    System.out.print("Enter your choice: ");
                    int viewChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (viewChoice == 1) {
                        // View by Member ID
                        System.out.print("Enter Member ID to view lending information: ");
                        int viewMemberId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        library.displayLendingInfoByMember(viewMemberId);
                    } else if (viewChoice == 2) {
                        // View by ISBN
                        System.out.print("Enter ISBN to view lending information: ");
                        String viewIsbn = scanner.nextLine();
                        library.displayLendingInfoByISBN(viewIsbn);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                    
                case 12:
                    // Display Overdue Books
                    library.displayOverdueBooks();
                    break;
                    
                case 13:
                    // Exit
                    System.out.println("Exiting Library Management System.");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
}
