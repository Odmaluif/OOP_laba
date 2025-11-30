import java.io.*;
import java.util.DuplicateFormatFlagsException;
import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) throws Exception {
        Vehicle car = new Car("Car", 14);
        car.setModelPrice("Car 1", 160000);
        System.out.println(car);
        Vehicle m = new Moped("Moped", 7);
        Vehicle a = new ATV("ATV", 7);
        Vehicle s = new Scooter("Scooter", 7);
        Vehicle b = VehicleMethod.createVehicle("ATV2", 7, a);
        System.out.println("изначальный объект");
        System.out.println("Тип:" + a.getClass().getSimpleName());
        System.out.println(a);
        System.out.println("созданный объект");
        System.out.println("Тип:" + b.getClass().getSimpleName());
        b.removeModel("ATV2 3");
        System.out.println(b);
        m.addModel("fdkdf", 344);
        System.out.println(m);
        m.setModelPrice("Moped 3", 45);
        System.out.println(m);
        m.setModelName("Moped 3", "Moped 3.1");
        System.out.println(m);
    }
    public static Vehicle test(String args[]) throws Exception {
        String className = args[0];
        String methodName = args[1];
        String modelName = args[2];
        double newPrice = Double.parseDouble(args[3]);
        Class<?> carClass = Class.forName(className);
        Vehicle car = (Vehicle) carClass.getConstructor(String.class, int.class).newInstance(className, 14);
        //System.out.println(car);
        Method method = carClass.getMethod(methodName, String.class, double.class);
        method.invoke(car, modelName, newPrice);
        return car;
    }
}