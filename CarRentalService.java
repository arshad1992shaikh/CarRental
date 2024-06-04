//Create a `CarRentalService` class to manage the overall car rental service, keeping track of all cars, customers, and rentals,
//ensuring no duplicate license plates or customer ID.
package management;
/*These lines specify the package name and import necessary classes. 
 * java.time.LocalDate is used to represent dates, and
 *  java.util.HashMap and java.util.Map are used to implement the maps that store cars, customers, and rentals.*/
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/*These lines define the CarRentalService class and declare three private fields: cars, customers, and rentals.
 *These fields are maps that store cars, customers, and rentals, respectively. 
 *The keys of the maps are strings (license plates, customer IDs, and rental IDs), 
 *and the values are objects of type Car, Customer, and Rental, respectively.*/
public class CarRentalService {
    private Map<String, Car> cars;
    private Map<String, Customer> customers;
    private Map<String, Rental> rentals;
/*This is the constructor of the CarRentalService class. It initializes the three maps with new instances of HashMap.*/
    public CarRentalService() {
        this.cars = new HashMap<>();
        this.customers = new HashMap<>();
        this.rentals = new HashMap<>();
    }
/*This method adds a car to the cars map. It checks if a car with the same license plate already exists in the map.
 If it does, it throws an IllegalArgumentException. Otherwise, it adds the car to the map with its license plate as the key.*/
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

    
   
    //Now, you need to add a method in the CarRentalService class to allow extending the rental period for a given rental:
    public void extendRentalPeriod(String licensePlate, LocalDate newReturnDate) {
        Rental rental = rentals.get(licensePlate);

        if (rental == null) {
            throw new IllegalArgumentException("Rental not found.");
        }

        rental.extendRentalPeriod(newReturnDate);
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