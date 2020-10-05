package Sprint3;

import java.util.Objects;

public class Car implements Comparable<Car> {
    private String name;
    private String fuelType; //diesel, petrol, electricity
    private int yearsOfManufacture;
    private double price;

    public Car() {}

    public Car(String name, String fuelType, int yearsOfManufacture, double price) {
        this.name = name;
        this.fuelType = fuelType;
        this.yearsOfManufacture = yearsOfManufacture;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getYearsOfManufacture() {
        return yearsOfManufacture;
    }

    public void setYearsOfManufacture(int yearsOfManufacture) {
        this.yearsOfManufacture = yearsOfManufacture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{ " +
                "name: " + name +
                ", fuel type: " + fuelType +
                ", years: " + yearsOfManufacture +
                ", price: " + price +
                " }";
    }

    @Override
    public int compareTo(Car anotherCar) {
        return Double.compare(this.price, anotherCar.price);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return yearsOfManufacture == car.yearsOfManufacture &&
                Double.compare(car.price, price) == 0 &&
                Objects.equals(name, car.name) &&
                Objects.equals(fuelType, car.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fuelType, yearsOfManufacture, price);
    }
}