import java.io.*;
import java.util.DuplicateFormatFlagsException;
import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) throws Exception {
        /*String className = args[0];
        Car c = new Car("gfd", 23);
        String methodName = args[1];
        String modelName = args[2];
        double newPrice = Double.parseDouble(args[4]);
        Class<?> carClass = Class.forName(className);
        Vehicle car = (Vehicle) carClass.getConstructor(String.class, int.class);*/
        if (args.length >= 3) {
            String className = args[0];
            String methodName = args[1];
            String modelName = args[2];
            double newPrice = args.length >= 4 ? Double.parseDouble(args[3]) : 150000;

            // Создаем объект средствами рефлексии
            Class<?> clazz = Class.forName(className);
            Vehicle vehicle = (Vehicle) clazz.getConstructor(String.class, int.class)
                    .newInstance("ReflectionVehicle", 3);

            // Находим и вызываем метод
            Method method = clazz.getMethod(methodName, String.class, double.class);
            method.invoke(vehicle, modelName, newPrice);
            // Выводим информацию
            System.out.println("Марка: " + vehicle.getBrand());
            System.out.println("Модели и цены:");
            String[] names = vehicle.getModelsNames();
            double[] prices = vehicle.getModelsPrices();
            for (int i = 0; i < vehicle.getSize(); i++) {
                System.out.println(names[i] + ": " + prices[i]);
            }
        }
    }
}