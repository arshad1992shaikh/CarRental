package management;

public class Car {
    private String licensePlate;
    private String model;
    private String brand;
    private int year;
    private boolean available;
    private String color; // New field
	
    public Car(String licensePlate, String model, String brand, String color, int year, boolean available) {
		this.licensePlate = licensePlate;
		this.model = model;
		this.brand = brand;
		this.color = color;
		this.year = year;
		this.available = available;
		
	}



	public String getLicensePlate() {
		return licensePlate;
	}

	public String getModel() {
		return model;
	}

	public String getBrand() {
		return brand;
	}
	
	 public String getColor() {
	        return color;
	    }

	public int getYear() {
		return year;
	}

	public boolean isAvailable() {
		return available;
	}
    
	   public void markAsRented() {
	        setAvailable(false);
	    }

		public void markAsReturned() {
	        setAvailable(true);
	    }

		private void setLicensePlate(String licensePlate) {
		        if (licensePlate == null || licensePlate.isEmpty()) {
		            throw new IllegalArgumentException("License plate cannot be null or empty.");
		        }
		        this.licensePlate = licensePlate;
		    }
		 
			private void setModel(String model) {
		        if (model == null || model.isEmpty()) {
		            throw new IllegalArgumentException("Model cannot be null or empty.");
		        }
		        this.model = model;
		    }

		    private void setBrand(String brand) {
		        if (brand == null || brand.isEmpty()) {
		            throw new IllegalArgumentException("Brand cannot be null or empty.");
		        }
		        this.brand = brand;
		    }
		    
		    private void setColor(String color) {
		        if (color == null || color.isEmpty()) {
		            throw new IllegalArgumentException("Color cannot be null or empty.");
		        }
		        this.color = color;
		    }

		    private void setYear(int year) {
		        if (year <= 0) {
		            throw new IllegalArgumentException("Year must be a positive number.");
		        }
		        this.year = year;
		    }

		    private void setAvailable(boolean available) {
		        this.available = available;
		    }
		}
