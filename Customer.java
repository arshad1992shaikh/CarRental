package management;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber; // New field
    private List<Car> rentedCars;

    public Customer(String customerId, String name, String email,String phoneNumber) {
        setCustomerId(customerId);
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber); // Set the phone number with validation
        this.rentedCars = new ArrayList<>();//
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
   
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void addRentedCar(Car car) {
        if (getRentedCars().contains(car)) {
            throw new IllegalArgumentException("Customer has already rented this car.");
        }
        getRentedCars().add(car);
    }

    public void removeRentedCar(Car car) {
        getRentedCars().remove(car);
    }

    private void setCustomerId(String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty.");
        }
        this.customerId = customerId;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    private void setEmail(String email) {
        if (email == null ||!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain an '@' symbol.");
        }
        this.email = email;
    }
    private void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || !phoneNumber.matches("\\+?[0-9]*")) {
            throw new IllegalArgumentException("Phone number cannot be null, empty, and must contain only digits (and optionally start with '+').");
        }
        this.phoneNumber = phoneNumber;
    }

	/**
	 * @return the rentedCars
	 */
	public List<Car> getRentedCars() {
		return rentedCars;
	}
}
