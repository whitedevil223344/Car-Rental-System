import java.util.*;

public class CarRentalSystem {

    // Class to represent a car in the rental system
    static class Car {
        private final String id;
        private final String model;
        private boolean isRented;

        Car(String id, String model) {
            this.id = id;
            this.model = model;
            this.isRented = false;
        }

        public String getId() {
            return id;
        }

        public String getModel() {
            return model;
        }

        public boolean isRented() {
            return isRented;
        }

        public void rent() {
            isRented = true;
        }

        public void returnCar() {
            isRented = false;
        }

        @Override
        public String toString() {
            return String.format("ID: %s, Model: %s, Status: %s", 
                    id, model, isRented ? "Rented" : "Available");
        }
    }

    private static final List<Car> cars = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCars();
        System.out.println("\nWelcome to the Car Rental System!\n");

        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> viewCars();
                case 2 -> rentCar();
                case 3 -> returnCar();
                case 4 -> exitSystem();
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Initialize the car list with sample data
    private static void initializeCars() {
        cars.add(new Car("1", "Toyota Corolla"));
        cars.add(new Car("2", "Honda Civic"));
        cars.add(new Car("3", "Ford Focus"));
        cars.add(new Car("4", "Hyundai Elantra"));
        cars.add(new Car("5", "Tesla Model 3"));
    }

    // Display the menu options
    private static void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. View Cars");
        System.out.println("2. Rent a Car");
        System.out.println("3. Return a Car");
        System.out.println("4. Exit");
    }

    // Get the user's menu choice
    private static int getChoice() {
        System.out.print("\nEnter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Display the list of cars
    private static void viewCars() {
        System.out.println("\nAvailable Cars:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    // Handle renting a car
    private static void rentCar() {
        System.out.print("\nEnter the car ID to rent: ");
        String id = scanner.next();
        Car car = findCarById(id);

        if (car == null) {
            System.out.println("Car not found.");
        } else if (car.isRented()) {
            System.out.println("Sorry, this car is already rented.");
        } else {
            car.rent();
            System.out.println("You have successfully rented the " + car.getModel() + "!");
        }
    }

    // Handle returning a car
    private static void returnCar() {
        System.out.print("\nEnter the car ID to return: ");
        String id = scanner.next();
        Car car = findCarById(id);

        if (car == null) {
            System.out.println("Car not found.");
        } else if (!car.isRented()) {
            System.out.println("This car is not currently rented.");
        } else {
            car.returnCar();
            System.out.println("You have successfully returned the " + car.getModel() + "!");
        }
    }

    // Find a car by its ID
    private static Car findCarById(String id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    // Exit the system gracefully
    private static void exitSystem() {
        System.out.println("\nThank you for using the Car Rental System! Goodbye!");
        System.exit(0);
    }
}
