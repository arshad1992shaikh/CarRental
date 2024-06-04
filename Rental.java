package management;
import java.time.LocalDate;

public class Rental {
    private Car car;
    private Customer customer;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental(Car car, Customer customer, LocalDate rentalDate) {
        setCar(car);
        setCustomer(customer);
        setRentalDate(rentalDate);
        this.returnDate = null;
        this.returnDate = rentalDate.plusDays(7); // Setting a default return date to 7 days from rental date
        car.markAsRented();
        customer.addRentedCar(car);
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void returnCar(LocalDate returnDate) {
        if (returnDate == null || returnDate.isBefore(rentalDate)) {
            throw new IllegalArgumentException("Return date must be after the rental date.");
        }
        this.returnDate = returnDate;
        car.markAsReturned(); // Mark the car as returned
        customer.removeRentedCar(car); // Remove the car from the customer's rented cars list
    }
    
    //// Implement additional methods, such as a method to extend the rental period, considering the implications on return dates and validations.
    public void extendRentalPeriod(LocalDate newReturnDate) {
        if (newReturnDate == null || newReturnDate.isBefore(returnDate)) {
            throw new IllegalArgumentException("New return date must be after the current return date.");
        }
        this.returnDate = newReturnDate;
    }
    
    private void setCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null.");
        }
        this.car = car;
    }

    private void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        this.customer = customer;
    }

    private void setRentalDate(LocalDate rentalDate) {
        if (rentalDate == null || rentalDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Rental date cannot be in the future.");
        }
        this.rentalDate = rentalDate;
    }
}
