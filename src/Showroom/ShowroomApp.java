package Showroom;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShowroomApp {
    private static final int MENU_OPTION_EXIT = 0;
    private static final int MENU_OPTION_INSERT = 1;
    private static final int MENU_OPTION_UPDATE = 2;
    private static final int MENU_OPTION_DELETE = 3;
    private static final int MENU_OPTION_VIEW = 4;

    public static void main(String[] args) {
        Showroom showroom = new Showroom();
        Scanner scanner = new Scanner(System.in);

        ShowroomThread showroomThread = new ShowroomThread(showroom, scanner);
        showroomThread.start();

        try {
            showroomThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinal Showroom Cars:");
        showroom.displayCars();

        scanner.close();
    }

    private static class ShowroomThread extends Thread {
        private Showroom showroom;
        private Scanner scanner;

        public ShowroomThread(Showroom showroom, Scanner scanner) {
            this.showroom = showroom;
            this.scanner = scanner;
        }

        @Override
        public void run() {
            int userChoice = -1;
            do {
                try {
                    // Display menu
                    System.out.println("Menu:");
                    System.out.println("1. Insert Car");
                    System.out.println("2. Update Car");
                    System.out.println("3. Delete Car");
                    System.out.println("4. View Cars");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");
                    userChoice = scanner.nextInt();

                    switch (userChoice) {
                        case MENU_OPTION_INSERT:
                            insertCar(showroom, scanner);
                            break;

                        case MENU_OPTION_UPDATE:
                            updateCar(showroom, scanner);
                            break;

                        case MENU_OPTION_DELETE:
                            deleteCar(showroom, scanner);
                            break;

                        case MENU_OPTION_VIEW:
                            viewCars(showroom);
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                    userChoice = -1;
                }

            } while (userChoice != MENU_OPTION_EXIT);
        }

        private void insertCar(Showroom showroom, Scanner scanner) {
            int newId = 0;
            try {
                System.out.print("ID: ");
                newId = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input for ID. Please enter a valid integer.");
                scanner.nextLine();
                return;
            }
            scanner.nextLine();

            System.out.print("Name: ");
            String newName = scanner.nextLine();
            System.out.print("Description: ");
            String newDescription = scanner.nextLine();
            String newEngine;
            do {
                System.out.print("Engine(V6/V8/V10/V12): ");
                newEngine = scanner.nextLine();
            } while (!isValidEngine(newEngine));

            String newType = null;
            if (newEngine.equals("V6")) {
                do {
                    System.out.print("Type(Regular/SUV/Classic): ");
                    newType = scanner.nextLine();
                } while (!isValidType(newType));
            } else if (newEngine.equals("V8")) {
                do {
                    System.out.print("Type(SUV/Sport/Classic): ");
                    newType = scanner.nextLine();
                } while (!isValidType(newType));
            } else if (newEngine.equals("V10")) {
                do {
                    System.out.print("Type(Sport/Classic/Luxury/Hypercar): ");
                    newType = scanner.nextLine();
                } while (!isValidType(newType));
            } else if (newEngine.equals("V12")) {
                do {
                    System.out.print("Type(Sport/Luxury/Hypercar): ");
                    newType = scanner.nextLine();
                } while (!isValidType(newType));
            }

            double newPrice;
            try {
                System.out.print("Price: ");
                newPrice = scanner.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input for Price. Please enter a valid number.");
                scanner.nextLine();
                return;
            }

            Car newCar = new Car(newId, newName, newDescription, newPrice, newEngine, newType);

            showroom.addCar(newCar);
            System.out.println("Car added successfully!");
        }

        private void updateCar(Showroom showroom, Scanner scanner) {
            int updateId = 0;
            try {
                System.out.print("ID: ");
                updateId = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input for ID. Please enter a valid integer.");
                scanner.nextLine();
                return;
            }
            scanner.nextLine();

            System.out.print("Enter the new description: ");
            String updatedDescription = scanner.nextLine();
            String updatedEngine;
            do {
                System.out.print("Engine(V6/V8/V10/V12): ");
                updatedEngine = scanner.nextLine();
            } while (!isValidEngine(updatedEngine));

            String updatedType = null;
            if (updatedEngine.equals("V6")) {
                do {
                    System.out.print("Type(Regular/SUV/Classic): ");
                    updatedType = scanner.nextLine();
                } while (!isValidType(updatedType));
            } else if (updatedEngine.equals("V8")) {
                do {
                    System.out.print("Type(SUV/Sport/Classic): ");
                    updatedType = scanner.nextLine();
                } while (!isValidType(updatedType));
            } else if (updatedEngine.equals("V10")) {
                do {
                    System.out.print("Type(Sport/Classic/Luxury/Hypercar): ");
                    updatedType = scanner.nextLine();
                } while (!isValidType(updatedType));
            } else if (updatedEngine.equals("V12")) {
                do {
                    System.out.print("Type(Sport/Luxury/Hypercar): ");
                    updatedType = scanner.nextLine();
                } while (!isValidType(updatedType));
            }

            double updatedPrice;
            try {
                System.out.print("Enter the new price: ");
                updatedPrice = scanner.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input for Price. Please enter a valid number.");
                scanner.nextLine();
                return;
            }

            showroom.updateCar(updateId, updatedDescription, updatedPrice, updatedEngine, updatedType);
        }

        private void deleteCar(Showroom showroom, Scanner scanner) {
            int deleteId = 0;
            try {
                System.out.print("Enter the ID of the car to delete: ");
                deleteId = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input for ID. Please enter a valid integer.");
                scanner.nextLine();
                return;
            }

            showroom.deleteCar(deleteId);
        }

        private void viewCars(Showroom showroom) {
            System.out.println("\nShowroom Cars:");
            showroom.displayCars();
        }
    }

    private static boolean isValidEngine(String engine) {
        return engine.equals("V6") || engine.equals("V8") || engine.equals("V10") || engine.equals("V12");
    }

    private static boolean isValidType(String type) {
        return type.equals("Regular") || type.equals("SUV") || type.equals("Sport") || type.equals("Classic")
                || type.equals("Luxury") || type.equals("Hypercar");
    }
}
