/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

/**
 *
 * @author Asus
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private Map<Integer, List<LendingInfo>> lendingInfo;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        lendingInfo = new HashMap<>();
    }

    // Add Book
    public void addBook(Book book) {
        books.add(book);
    }

    // Remove Book
    public void removeBook(String isbn) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            books.remove(bookToRemove);
        } else {
            System.out.println("Book not found.");
        }
    }

    // Register Member
    public void registerMember(Member member) {
        members.add(member);
    }

    // Remove Member
    public void removeMember(int memberId) {
        Member memberToRemove = null;
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                memberToRemove = member;
                break;
            }
        }

        if (memberToRemove != null) {
            members.remove(memberToRemove);
        } else {
            System.out.println("Member not found.");
        }
    }

    // Search Book Information
    public Book searchBookInformation(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null; // Book not found
    }

    // Search Member Information
    public Member searchMemberInformation(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null; // Member not found
    }

    // Lend Book
    public void lendBook(int memberId, String isbn) {
        Member member = searchMemberInformation(memberId);
        Book book = searchBookInformation(isbn);

        if (member != null && book != null) {
            // Check if the book is available
            if (isBookAvailable(book)) {
                // Create a lending record
                LendingInfo lendingRecord = new LendingInfo(member, book, new Date(),null,new Date());
                List<LendingInfo> memberLendingList = lendingInfo.getOrDefault(memberId, new ArrayList<>());
                memberLendingList.add(lendingRecord);
                lendingInfo.put(memberId, memberLendingList);

                // Update book status
                book.setAvailable(false);
                System.out.println("Book lent successfully.");
            } else {
                System.out.println("The book is not available for lending.");
            }
        } else {
            System.out.println("Member or book not found.");
        }
    }

    // Return Book
    public void returnBook(int memberId, String isbn) {
        Member member = searchMemberInformation(memberId);
        Book book = searchBookInformation(isbn);

        if (member != null && book != null) {
            List<LendingInfo> memberLendingList = lendingInfo.getOrDefault(memberId, new ArrayList<>());

            // Find the lending record for the book
            LendingInfo lendingRecordToRemove = null;
            for (LendingInfo lendingRecord : memberLendingList) {
                if (lendingRecord.getBook().equals(book) && lendingRecord.getReturnDate() == null) {
                    lendingRecordToRemove = lendingRecord;
                    break;
                }
            }

            if (lendingRecordToRemove != null) {
                // Calculate fine (if any) and update the return date
                Date returnDate = new Date();
                lendingRecordToRemove.setReturnDate(returnDate);
                double fine = calculateFine(lendingRecordToRemove);
                System.out.println("Book returned successfully.");
                System.out.println("Fine amount: Rs. " + fine);
            } else {
                System.out.println("Book not found in the member's lending records.");
            }
        } else {
            System.out.println("Member or book not found.");
        }
    }

    // Check if a book is available for lending
    private boolean isBookAvailable(Book book) {
        // Check if the book is not already lent
        for (List<LendingInfo> lendingRecords : lendingInfo.values()) {
            for (LendingInfo lendingRecord : lendingRecords) {
                if (lendingRecord.getBook().equals(book) && lendingRecord.getReturnDate() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    // Calculate fine based on overdue days
    private double calculateFine(LendingInfo lendingRecord) {
        Date dueDate = lendingRecord.getDueDate();
        Date returnDate = lendingRecord.getReturnDate();

        if (dueDate != null && returnDate != null && returnDate.after(dueDate)) {
            long diffMillis = returnDate.getTime() - dueDate.getTime();
            long diffDays = diffMillis / (24 * 60 * 60 * 1000);
            if (diffDays <= 7) {
                return diffDays * 50;
            } else {
                return (7 * 50) + ((diffDays - 7) * 100);
            }
        }
        return 0;
    }

    // Display Books
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Display Members
    public void displayMembers() {
        for (Member member : members) {
            System.out.println(member);
        }
    }

    // Display Lending Information by Member ID
    public void displayLendingInfoByMember(int memberId) {
        List<LendingInfo> memberLendingList = lendingInfo.getOrDefault(memberId, new ArrayList<>());
        if (!memberLendingList.isEmpty()) {
            for (LendingInfo lendingRecord : memberLendingList) {
                System.out.println(lendingRecord);
            }
        } else {
            System.out.println("No lending information found for the member.");
        }
    }

    // Display Lending Information by ISBN
    public void displayLendingInfoByISBN(String isbn) {
        for (List<LendingInfo> lendingRecords : lendingInfo.values()) {
            for (LendingInfo lendingRecord : lendingRecords) {
                if (lendingRecord.getBook().getIsbn().equals(isbn)) {
                    System.out.println(lendingRecord);
                }
            }
        }
    }

    // Display Overdue Books
    public void displayOverdueBooks() {
        Date currentDate = new Date();
        for (List<LendingInfo> lendingRecords : lendingInfo.values()) {
            for (LendingInfo lendingRecord : lendingRecords) {
                if (lendingRecord.getReturnDate() == null && currentDate.after(lendingRecord.getDueDate())) {
                    System.out.println("Overdue Book:");
                    System.out.println(lendingRecord.getBook());
                    System.out.println("Borrower: " + lendingRecord.getMember().getName());
                    System.out.println("Due Date: " + lendingRecord.getDueDate());
                }
            }
        }
    }
}


