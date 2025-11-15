import java.io.*;
import java.util.DuplicateFormatFlagsException;
import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) throws Exception {
        Test(args);

    }
    public static void Test(String args[]) throws Exception {
        String className = args[0];
        Car c = new Car("Car", 23);
        String methodName = args[1];
        String modelName = args[2];
        System.out.println(c.toString());
        double newPrice = Double.parseDouble(args[3]);
        Class<?> carClass = Class.forName(className);
        Vehicle car = (Vehicle) carClass.getConstructor(String.class, int.class).newInstance("Car", 14);
        Method method = carClass.getMethod(methodName, String.class, double.class);
        method.invoke(car, modelName, newPrice);
        System.out.println(car.toString());
    }
}