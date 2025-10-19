import java.io.*;
import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) throws IOException, DuplicateModelNameException, ClassNotFoundException, CloneNotSupportedException {

        Car brand = new Car("Model", 6);
        Vehicle bike = new Bike("Model", 6);

        Vehicle clone = (Vehicle) brand.clone();

        System.out.println("Оригинал:");
        VehicleMethod.printModelsNames(brand);
        System.out.println("Клон:");
        VehicleMethod.printModelsNames(clone);
        System.out.println(brand.equals(clone));

        clone.addModel("Model 23", 2321);
        System.out.println("добавлена модель в клон");

        System.out.println("Оригинал:");
        VehicleMethod.printModelsNames(brand);
        System.out.println("Клон:");
        VehicleMethod.printModelsNames(clone);
        System.out.println(brand.equals(clone));
    }
}