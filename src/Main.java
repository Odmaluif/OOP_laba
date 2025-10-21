import java.io.*;
import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) throws IOException, DuplicateModelNameException, ClassNotFoundException, CloneNotSupportedException, NoSuchModelException {

        Car brand = new Car("Model", 6);
        Vehicle bike = new Bike("Model", 6);

        Vehicle clone = (Vehicle) brand.clone();

        System.out.println("Строка оригинала: " + brand);
        System.out.println("Строка клона: " + clone);
        System.out.println(brand.equals(clone));

        System.out.println("Хэш код оригинала: " + brand.hashCode());
        System.out.println("Хэш код клона: " + clone.hashCode() );

        clone.setModelPrice("Model 3", 2321);
        System.out.println("добавлена модель в клон");

        System.out.println("Строка оригинала: " + brand);
        System.out.println("Строка клона: " + clone);
        System.out.println(brand.equals(clone));

        System.out.println("Хэш код оригинала: " + brand.hashCode());
        System.out.println("Хэш код клона: " + clone.hashCode() );

        System.out.println(brand.equals(bike));
        System.out.println(bike.equals(brand));

    }
}