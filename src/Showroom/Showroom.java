package Showroom;

import java.util.ArrayList;
import java.util.List;

public class Showroom {
	private List<Car> cars;

	public Showroom() {
		this.cars = new ArrayList<>();
	}

	public synchronized void addCar(Car car) {
		// Add car to the showroom
		cars.add(car);
		System.out.println("Car added: " + car.getName());
	}

	public synchronized void updateCar(int id, String newDescription, double newPrice, String newEngine,
			String newType) {
		// Update car details
		for (Car car : cars) {
			if (car.getId() == id) {
				car.setDescription(newDescription);
				car.setPrice(newPrice);
				car.setEngine(newEngine);
				car.setType(newType);
				System.out.println("Car updated: " + car.getName());
				return;
			}
		}
		System.out.println("Car not found with ID: " + id);
	}

	public synchronized void deleteCar(int id) {
		if (cars.removeIf(car -> car.getId() == id)) {
			System.out.println("Car deleted with ID: " + id);
		}else {
			System.out.println("Car Not Found with ID: " + id);
		}
	}

	public synchronized void displayCars() {
		for (Car car : cars) {
			System.out.println("Car details - ID: " + car.getId() + ", Name: " + car.getName() + ", Description: "
					+ car.getDescription() + ", Engine: " + car.getEngine() + ", Type: " + car.getType() + ", Price: $"
					+ car.getPrice());
		}
	}
}
