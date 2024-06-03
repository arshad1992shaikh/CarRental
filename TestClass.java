package management;

import java.time.LocalDate;

public class TestClass {

    public static void main(String[] args) {
        testCar();
        testCustomer();
        testRental();
        testInvalidOperations();
        testCarRentalService();
    }

    public static void testCar() {
        Car car = new Car("ABC123", "Toyota", "Corolla", 2020, true);
        System.out.println("Output of Car class...");
        System.out.println("License Plate: " + car.getLicensePlate());
        System.out.println("Brand: " + car.getBrand());
        System.out.println("Model: " + car.getModel());
        System.out.println("Year: " + car.getYear());
        System.out.println("Availability: " + car.isAvailable());
        car.markAsRented();
        System.out.println("Marking car as rented...");
        System.out.println("Availability: " + car.isAvailable());
        car.markAsReturned();
        System.out.println("Marking car as returned...");
        System.out.println("Availability: " + car.isAvailable());
        System.out.println();
    }

    public static void testCustomer() {
        Customer customer = new Customer("123", "John Doe", "john.doe@example.com");
        System.out.println("Output of Customer class...");
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        Car car = new Car("ABC123", "Toyota", "Corolla", 2020, true);
        customer.addRentedCar(car);
        System.out.println("Adding car to customer's rented cars...");
        System.out.println("Rented cars: " + customer.getRentedCars().size());
        customer.removeRentedCar(car);
        System.out.println("Removing car from customer's rented cars...");
        System.out.println("Rented cars: " + customer.getRentedCars().size());
        System.out.println();
    }
    
    public static void testCarRentalService() {
        System.out.println("Output of CarRentalService class...");
        CarRentalService service = new CarRentalService();

        // Adding cars
        Car car1 = new Car("ABC123", "Toyota", "Corolla", 2020, true);
        Car car2 = new Car("XYZ789", "Honda", "Civic", 2019, true);
        service.addCar(car1);
        service.addCar(car2);

        // Adding customers
        Customer customer1 = new Customer("123", "John Doe", "john.doe@example.com");
        Customer customer2 = new Customer("456", "Jane Smith", "jane.smith@example.com");
        service.addCustomer(customer1);
        service.addCustomer(customer2);

        // Renting a car
        Rental rental1 = service.rentCar("ABC123", "123", LocalDate.now());
        System.out.println("Car rented: " + rental1.getCar().getLicensePlate());

        // Trying to rent the same car again (should fail)
        try {
            service.rentCar("ABC123", "456", LocalDate.now());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Returning the car
        service.returnCar("ABC123", LocalDate.now().plusDays(3));
        System.out.println("Car returned.");

        // Renting the car again (should succeed)
        Rental rental2 = service.rentCar("ABC123", "456", LocalDate.now());
        System.out.println("Car rented again: " + rental2.getCar().getLicensePlate());
        }

    public static void testRental() {
        Car car = new Car("ABC123", "Toyota", "Corolla", 2020, true);
        Customer customer = new Customer("123", "John Doe", "john.doe@example.com");
        Rental rental = new Rental(car, customer, LocalDate.now());
        System.out.println("Output of Rental class...");
        System.out.println("Car: " + rental.getCar().getLicensePlate());
        System.out.println("Customer: " + rental.getCustomer().getCustomerId());
        System.out.println("Rental Date: " + rental.getRentalDate());
        System.out.println("Return Date: " + rental.getReturnDate());
        rental.returnCar(LocalDate.now().plusDays(3));
        System.out.println("Returning car...");
        System.out.println("Return Date: " + rental.getReturnDate());
        System.out.println();
    }

    public static void testInvalidOperations() {
        System.out.println("Output of invalid operations...");
        Car car = new Car("ABC123", "Toyota", "Corolla", 2020, true);
        Customer customer = new Customer("123", "John Doe", "john.doe@example.com");

        // Mark the car as rented before attempting to rent it again
        car.markAsRented();
        try {
            new Rental(car, customer, LocalDate.now()); // Try renting the car again
            System.out.println("Error: Should not be able to rent a car that the customer has already rented");
        } catch (Exception e) {
            System.out.println("Success: Cannot rent a car that the customer has already rented");
        }

        // Verify that the customer's rented car list is properly updated
        if (customer.getRentedCars().isEmpty()) {
            System.out.println("Success: Customer's rented car list is empty.");
        } else {
            System.out.println("Error: Customer's rented car list should be empty.");
        }

    
    }}
