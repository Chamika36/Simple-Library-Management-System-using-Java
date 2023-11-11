/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

/**
 *
 * @author Asus
 */

public class Member {
    private int memberId;
    private String name;
    private String address;

    public Member(int memberId, String name, String address) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
    }

    // Getter and Setter methods for attributes

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + "\nName: " + name + "\nAddress: " + address;
    }
}

