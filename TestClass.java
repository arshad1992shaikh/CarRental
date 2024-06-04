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
        Car car = new Car("ABC123", "Toyota", "Corolla","Black", 2020, true);
        
        System.out.println("Testing Car class...");
        System.out.println("License Plate: " + car.getLicensePlate());
        System.out.println("Brand: " + car.getBrand());
        System.out.println("Model: " + car.getModel());
        System.out.println("Color: " + car.getColor());
        System.out.println("Year: " + car.getYear());
        System.out.println("Availability: " + car.isAvailable());
        car.markAsRented();
        System.out.println("Marking car as rented...");
        System.out.println("Availability: " + car.isAvailable());
        car.markAsReturned();
        System.out.println("Marking car as returned...");
        System.out.println("Availability: " + car.isAvailable());
        System.out.println();
        
        Car car2 = new Car("zxw123", "tata", "nano","Red", 2024, true);
        System.out.println("Testing Car class...");
        System.out.println("License Plate: " + car2.getLicensePlate());
        System.out.println("Brand: " + car2.getBrand());
        System.out.println("Model: " + car2.getModel());
        System.out.println("Year: " + car2.getYear());
        System.out.println("Availability: " + car2.isAvailable());
        car2.markAsRented();
        System.out.println("Marking car as rented...");
        System.out.println("Availability: " + car.isAvailable());
        car2.markAsReturned();
        System.out.println("Marking car as returned...");
        System.out.println("Availability: " + car2.isAvailable());
        System.out.println();
    }

    public static void testCustomer() {
        Customer customer = new Customer("123", "John Doe", "john.doe@example.com","+1234567890");
        System.out.println("Testing Customer class...");
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Phone Number: " + customer.getPhoneNumber());
        Car car = new Car("ABC123", "Toyota", "Corolla","Black", 2020, true);
        customer.addRentedCar(car);
        System.out.println("Adding car to customer's rented cars...");
        System.out.println("Rented cars: " + customer.getRentedCars().size());
        customer.removeRentedCar(car);
        System.out.println("Removing car from customer's rented cars...");
        System.out.println("Rented cars: " + customer.getRentedCars().size());
        System.out.println();
    }
    
    public static void testCarRentalService() {
        System.out.println("Testing CarRentalService class...");
        CarRentalService service = new CarRentalService();
        Car car = new Car("ABC123", "Corolla", "Toyota","Black", 2020, true);
        Car car2 = new Car("zxw123", "tata", "nano","Red", 2024, true);
        Customer customer = new Customer("123", "John Doe", "john.doe@example.com","+1234567890");
        service.addCar(car);
        service.addCar(car2);
        service.addCustomer(customer);
        LocalDate rentalDate = LocalDate.now();
        Rental rental = service.rentCar("ABC123", "123", rentalDate);
        System.out.println("Car rented: " + rental.getCar().getLicensePlate());

        // Attempt to rent the same car again
        try {
            service.rentCar("ABC123", "123", rentalDate);
            System.out.println("Error: Car is already rented.");
        } catch (IllegalStateException e) {
            System.out.println("Success: Car is already rented.");
        }

        // Return the car
        service.returnCar("ABC123", rentalDate.plusDays(3));
        System.out.println("Car returned.");

        // Rent the car again
        rental = service.rentCar("ABC123", "123", rentalDate);
        System.out.println("Rental period extended to: " + rental.getCar().getLicensePlate());

        // Extend the rental period
        LocalDate newReturnDate = rentalDate.plusDays(7);
        service.extendRentalPeriod("ABC123", newReturnDate);
        System.out.println("Rental period extended to: " + newReturnDate);
    }

    public static void testRental() {
    	
        Car car = new Car("ABC123","Toyota", "Corolla","Black", 2020, true);
        Customer customer = new Customer("123", "John Doe", "john.doe@example.com","+1234567890");
        Rental rental = new Rental(car, customer, LocalDate.now());
        System.out.println("Testing Rental class...");
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
        System.out.println("Testing invalid operations...");
        
        Car car = new Car("ABC126", "Toyota", "Corolla","Black", 2020, true);
        Customer customer = new Customer("123", "John Doe", "john.doe@example.com","+1234567890");

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
