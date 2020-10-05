package Sprint3;


public class UsedCars extends Car {

    private int mileage;

    public UsedCars() {}

    public UsedCars(String name, String fuelType, int yearsOfManufacture, double price, int mileage) {
        super(name, fuelType, yearsOfManufacture, price);
        this.mileage = mileage;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "{ " +
                "name: " + this.getName() +
                ", Fuel type: " + this.getFuelType() +
                ", years: " + this.getYearsOfManufacture() +
                ", price: " + this.getPrice() +
                ", mileage: " + mileage +
                " }";
    }



}