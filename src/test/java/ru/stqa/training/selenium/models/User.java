package ru.stqa.training.selenium.models;

public class User {

    String taxId;
    String company;
    String firstName;
    String lastName;
    String address1;
    String address2;
    String postcode;
    String city;
    String country;
    String zone;
    String email;
    String phone;
    boolean newsletter;
    String password;

    public String getTaxId() {
        return taxId;
    }

    public User withTaxId(String taxId) {
        this.taxId = taxId;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public User withCompany(String company) {
        this.company = company;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public User withAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public User withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public User withPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User withCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public User withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getZone() {
        return zone;
    }

    public User withZone(String zone) {
        this.zone = zone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public User withNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "taxId='" + taxId + '\'' +
                ", company='" + company + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", newsletter=" + newsletter +
                ", password='" + password + '\'' +
                '}';
    }
}
