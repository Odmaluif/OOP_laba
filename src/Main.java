import java.io.*;
import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) throws IOException, DuplicateModelNameException, ClassNotFoundException, CloneNotSupportedException {

        Car brand = new Car("Model", 6);
        Vehicle bike = new Bike("Model", 6);

        Vehicle clone = (Vehicle) brand.clone();

        System.out.println("Перевод в строку оригинала: " + brand.toString());
        System.out.println("Перевод в строку клона: " + clone.toString());
        System.out.println(brand.equals(clone));

        System.out.println("Хэш код оригинала: " + brand.hashCode());
        System.out.println("Хэш код клона: " + clone.hashCode() );

        clone.addModel("Model 23", 2321);
        System.out.println("добавлена модель в клон");

        System.out.println("Перевод в строку оригинала: " + brand.toString());
        System.out.println("Перевод в строку клона: " + clone.toString());
        System.out.println(brand.equals(clone));

        System.out.println("Хэш код оригинала: " + brand.hashCode());
        System.out.println("Хэш код клона: " + clone.hashCode() );

    }
}