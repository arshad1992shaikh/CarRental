package management;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CarRentalService {
    private Map<String, Car> cars;
    private Map<String, Customer> customers;
    private Map<String, Rental> rentals;

    public CarRentalService() {
        this.cars = new HashMap<>();
        this.customers = new HashMap<>();
        this.rentals = new HashMap<>();
    }

    public void addCar(Car car) {
        if (cars.containsKey(car.getLicensePlate())) {
            throw new IllegalArgumentException("Car with this license plate already exists.");
        }
        cars.put(car.getLicensePlate(), car);
    }

    public void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getCustomerId())) {
            throw new IllegalArgumentException("Customer with this ID already exists.");
        }
        customers.put(customer.getCustomerId(), customer);
    }

    public Rental rentCar(String licensePlate, String customerId, LocalDate rentalDate) {
        Car car = cars.get(licensePlate);
        Customer customer = customers.get(customerId);

        if (car == null) {
            throw new IllegalArgumentException("Car not found.");
        }

        if (customer == null) {
            throw new IllegalArgumentException("Customer not found.");
        }

        if (!car.isAvailable()) {
            throw new IllegalStateException("Car is already rented.");
        }

        Rental rental = new Rental(car, customer, rentalDate);
        rentals.put(licensePlate, rental);

        return rental;
    }

    public void returnCar(String licensePlate, LocalDate returnDate) {
        Rental rental = rentals.get(licensePlate);

        if (rental == null) {
            throw new IllegalArgumentException("Rental not found.");
        }

        rental.returnCar(returnDate);
        rentals.remove(licensePlate);
    }

    public Car getCar(String licensePlate) {
        return cars.get(licensePlate);
    }

    public Customer getCustomer(String customerId) {
        return customers.get(customerId);
    }

    public Rental getRental(String licensePlate) {
        return rentals.get(licensePlate);
    }
}