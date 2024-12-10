package com.oop.event_ticket_system.resources;

public class RegisterVendorResource {
    private String firstName; // Vendor's first name
    private String lastName; // Vendor's last name
    private String email; // Vendor's email address
    private String contactNumber; // Vendor's contact number
    private String password; // Vendor's password

    public String getFirstName() {
        return firstName; // Get the vendor's first name
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName; // Set the vendor's first name
    }

    public String getLastName() {
        return lastName; // Get the vendor's last name
    }

    public void setLastName(String lastName) {
        this.lastName = lastName; // Set the vendor's last name
    }

    public String getEmail() {
        return email; // Get the vendor's email address
    }

    public void setEmail(String email) {
        this.email = email; // Set the vendor's email address
    }

    public String getContactNumber() {
        return contactNumber; // Get the vendor's contact number
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber; // Set the vendor's contact number
    }

    public String getPassword() {
        return password; // Get the vendor's password
    }

    public void setPassword(String password) {
        this.password = password; // Set the vendor's password
    }

    public String getConfirmPassword() {
        return confirmPassword; // Get the vendor's confirmation password
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword; // Set the vendor's confirmation password
    }

    private String confirmPassword; // Vendor's confirmation password
}