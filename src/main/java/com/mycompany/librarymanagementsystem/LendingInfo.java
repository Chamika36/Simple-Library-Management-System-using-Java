/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

/**
 *
 * @author Asus
 */
import java.util.Date;

public class LendingInfo {
    private Member member;
    private Book book;
    private Date lendDate;
    private Date returnDate;
    private Date dueDate;

    public LendingInfo(Member member, Book book, Date lendDate, Date returnDate, Date dueDate) {
        this.member = member;
        this.book = book;
        this.lendDate = lendDate;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    // Getter and Setter methods (if needed)

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String returnStatus = (returnDate != null) ? "Returned" : "Not Returned";
        return "Member: " + member.getName() + "\nBook: " + book.getTitle() + "\nLend Date: " + lendDate +
               "\nReturn Date: " + ((returnDate != null) ? returnDate : "Not Returned") + "\nStatus: " + returnStatus;
    }
}
