package Sprint3;


import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class App {


    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String input = null;
        ArrayList<Car> listOfCars = new ArrayList<>();
        Car car = new UsedCars();

        while (true) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("n - enter new car");
            System.out.println("c - add to cart");
            System.out.println("l - list of cars (for printing & sorting)");
            System.out.println("q - quit & exit");

            System.out.println("Choose operation:");
            input = userInput.nextLine();
            if (input.equals("q")) {
                System.exit(0);
            } else if (input.equals("n")) {

                // Creating car object
                System.out.println("Is the car used or new? (y/n):");
                String yn = userInput.nextLine();

                Car returnedCar = createInstance(yn);

                System.out.println("Car added to list: " + returnedCar);

                // writing car data to file
                FileWriter fw1 = null;
                try {
                    fw1 = new FileWriter(new File("./data/cars.csv"), true);
                    BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(String.valueOf(returnedCar));
                    bw1.newLine();
                    bw1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (input.equals("c")) {

                listOfCars = readFromCarsFile(listOfCars);
                ArrayList<Car> carCart = new ArrayList<>();
                System.out.println("Choose from list: ");
                for (Car i : listOfCars) {
                    System.out.println((listOfCars.indexOf(i) + 1) + ". " + i);
                }
                System.out.println("Enter car name to add to cart:");
                input = userInput.nextLine();

                int x = listOfCars.size();
                for (Car p : listOfCars) {
                    x--;
                    if (p.getName().equals(input)) {
                        System.out.println("Car '" + p.getName() + "' is now in the Cart");
                        carCart.add(p);
                        writeOneToCarCart(p);
                        break;
                    } else if (x==0){
                        System.out.println("There is no car '" + input + "' in the list");
                    }

                }

            } else if (input.equals("l")) {
                System.out.println("--------------------------------------------------------------");
                System.out.println("p - print list of cars");
                System.out.println("d - delete from list");
                System.out.println("s - sort list");
                System.out.println("q - quit & exit");
                System.out.println("Choose operation:");
                input = userInput.nextLine();

                if (input.equals("q")) {
                    System.exit(0);
                } else if (input.equals("p")) {

                    listOfCars = readFromCarsFile(listOfCars);

                    System.out.println("Car list:");
                    for (Car i : listOfCars) {
                        System.out.println((listOfCars.indexOf(i) + 1) + ". " + i);
                    }


                } else if (input.equals("d")) {

                    listOfCars = readFromCarsFile(listOfCars);

                    System.out.println("Choose from list: ");
                    for (Car i : listOfCars) {
                        System.out.println((listOfCars.indexOf(i) + 1) + ". " + i);
                    }
                    System.out.println("Enter car name to be removed:");
                    input = userInput.nextLine();

                    String finalInput = input;
                    listOfCars.removeIf(new Predicate<Car>() {
                        @Override
                        public boolean test(Car car) {
                            return car.getName().equals(finalInput);
                        }
                    });

                    saveToCarListFile(listOfCars);

                    listOfCars = readFromCarsFile(listOfCars);

                    System.out.println("Here is the list after removal: ");
                    for (Car i : listOfCars) {
                        System.out.println((listOfCars.indexOf(i) + 1) + ". " + i);
                    }

                } else if (input.equals("s")) {
                    listOfCars = new ArrayList<>();

                    listOfCars = readFromCarsFile(listOfCars);

                    System.out.println("Before sorting:");
                    for (Car i : listOfCars) {
                        System.out.println((listOfCars.indexOf(i) + 1) + ". " + i);
                    }
                    System.out.println("--------------------------------------------------------------");
                    System.out.println("Choose type of sort:");
                    System.out.println("p - price sort");
                    System.out.println("n - name sort");
                    input = userInput.nextLine();
                    switch (input){
                        case "p":
                            listOfCars.sort(Comparator.naturalOrder());

                            break;

                        case "n":
                            listOfCars.sort(Comparator.comparing(Car::getName));
                            break;

                        default:
                            System.out.println("Wrong operation selected!!!");
                    }

                    System.out.println("After sorting:");
                    for (Car i : listOfCars) {
                        System.out.println((listOfCars.indexOf(i) + 1) + ". " + i);
                    }

                } else {
                    System.out.println("Wrong operation selected!!!");
                }
            } else {
                System.out.println("Operation failed successfully!!!");
                break;
            }
        }
    }

    public static Car createInstance (String yn){
        Scanner userInput = new Scanner(System.in);
        Car car = null;

        switch (yn) {
            case "y":
                car = new UsedCars();
                System.out.println("Enter car name:");
                car.setName(userInput.nextLine());
                System.out.println("fuel type (d - diesel, p - petrol, e - electricity):");
                car.setFuelType(userInput.nextLine());
                System.out.println("years of manufacture:");
                car.setYearsOfManufacture(userInput.nextInt());
                System.out.println("price:");
                car.setPrice(userInput.nextDouble());
                userInput.nextLine();
                System.out.println("mileage:");
                ((UsedCars) car).setMileage(userInput.nextInt());
                break;
            case "n":
                car = new Car();
                System.out.println("Enter car name:");
                car.setName(userInput.nextLine());
                System.out.println("fuel type (d - diesel, p - petrol, e - electricity):");
                car.setFuelType(userInput.nextLine());
                System.out.println("years of manufacture:");
                car.setYearsOfManufacture(userInput.nextInt());
                System.out.println("price:");
                car.setPrice(userInput.nextDouble());
                userInput.nextLine();
                break;
            default:
                System.out.println("Please, specify is the car is used or new? (y/n):");
        }
        return car;
    }

    static void writeOneToCarCart(Car car){
        FileWriter fw1 = null;
        try {
            fw1 = new FileWriter(new File("./data/carCart.csv"), true);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(String.valueOf(car));
            bw1.newLine();
            bw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveToCarListFile(ArrayList<Car> listOfCars){
        File fileName = new File("./data/cars.csv");

        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < listOfCars.size(); i++){
                bw.write(listOfCars.get(i).toString());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Car> readFromCarsFile(ArrayList<Car> listOfCars){
        listOfCars = new ArrayList<>();
        FileReader fr = null;
        try {
            fr = new FileReader("./data/cars.csv");
            BufferedReader br = new BufferedReader(fr);
            String fileLine = br.readLine();
            while (fileLine != null) {
                String[] splitString = fileLine.split("(:\\s)|(,\\s)|(\\{\\s)|(\\s})");
                UsedCars car = new UsedCars();
                car.setName(splitString[2]);
                car.setFuelType(splitString[4]);
                car.setYearsOfManufacture(Integer.parseInt(splitString[6]));
                car.setPrice(Double.parseDouble(splitString[8]));
                if(splitString.length > 9){
                    car.setMileage(Integer.parseInt(splitString[10]));
                }
                listOfCars.add(car);
                fileLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfCars;
    }
}