import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Scanner;

public final class VehicleMethod {
    public static double getArithmeticMean(Vehicle vehicle){
        double[] prices = vehicle.getModelsPrices();
        double sum = 0;
        double average;
        double quantity = vehicle.getSize();
        if(prices != null && vehicle.getSize() != 0){
            for(int i = 0; i < vehicle.getSize(); i++){
                sum += prices[i];
            }
            average = sum / quantity;
        }
        else{
            average = 0;
        }
        return average;
    }
    public static void printModelsNames(Vehicle vehicle){
        String[] models = vehicle.getModelsNames();
        if(models != null && vehicle.getSize() != 0){
            for(int i =0; i< vehicle.getSize(); i++){
                System.out.println(models[i]);
            }
        }
        else{
            System.out.println("Пусто");
        }
    }
    public static void printModelsPrices(Vehicle vehicle){
        double[] prices = vehicle.getModelsPrices();
        if(prices != null && vehicle.getSize() != 0){
            for(int i =0; i< vehicle.getSize(); i++){
                System.out.println(prices[i]);
            }
        }
        else{
            System.out.println("Пусто");
        }
    }
    public static void outputVehicle(Vehicle vehicle, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        byte[] className = vehicle.getClass().getSimpleName().getBytes();
        byte[] brand = vehicle.getBrand().getBytes();
        dos.writeInt(className.length);
        dos.write(className);
        dos.writeInt(brand.length);
        dos.write(brand);
        dos.writeInt(vehicle.getSize());
        String[] names = vehicle.getModelsNames();
        double[] prices = vehicle.getModelsPrices();
        for(int i =0; i < vehicle.getSize(); i++){
            byte[] name = names[i].getBytes();
            dos.writeInt(name.length);
            dos.write(name);
            dos.writeDouble(prices[i]);
        }
        dos.flush();
    }
    public static Vehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException {
        DataInputStream dis = new DataInputStream(in);

        int classNameLength = dis.readInt();
        byte[] classNameBytes = new byte[classNameLength];
        dis.readFully(classNameBytes);
        String className = new String(classNameBytes);

        int brandLength = dis.readInt();
        byte[] brandBytes = new byte[brandLength];
        dis.readFully(brandBytes);
        String brand = new String(brandBytes);

        int size = dis.readInt();

        Vehicle vehicle = createVehicleByType(className, brand);
        for(int i = 0 ; i< size; i++){
            byte[] nameByte = new byte[dis.readInt()];
            dis.readFully(nameByte);
            String name = new String(nameByte);
            double price = dis.readDouble();
            vehicle.addModel(name, price);
        }
        return vehicle;
    }
    public static void writeVehicle(Vehicle v, Writer out) {
        PrintWriter pw = new PrintWriter(out);

        // Использование printf() для форматированного вывода
        pw.printf("Тип: %s%n", v.getClass().getSimpleName());
        pw.printf("Марка: %s%n", v.getBrand());
        pw.printf("Количество моделей: %d%n", v.getSize());

        String[] names = v.getModelsNames();
        double[] prices = v.getModelsPrices();
        pw.println("Модели и цены:");
        for (int i = 0; i < v.getSize(); i++) {
            // Форматирование: название + цена с 2 знаками после запятой + валюта
            pw.printf("  %s: %.2f руб.%n", names[i], prices[i]);
        }
        pw.flush();
    }
    public static Vehicle readVehicle(Reader in) throws IOException, DuplicateModelNameException {
        Scanner scanner = new Scanner(in);

        // Чтение с разбором форматированных меток
        scanner.next(); // "Тип:"
        String className = scanner.nextLine().trim();

        scanner.next(); // "Марка:"
        String brand = scanner.nextLine().trim();

        scanner.next(); // "Количество"
        scanner.next(); // "моделей:"
        int size = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        scanner.nextLine(); // "Модели и цены:"

        Vehicle vehicle = createVehicleByType(className, brand);

        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine().trim();
            // Разбор строки вида "Corolla: 1200000.00 руб."
            line = line.replace(" руб.", "");
            String[] parts = line.split(": ");
            String modelName = parts[0];
            double price = Double.parseDouble(parts[1]);

            vehicle.addModel(modelName, price);
        }

        scanner.close();
        return vehicle;
    }
    private static Vehicle createVehicleByType(String className, String brand) {
        try {
            switch (className) {
                case "Car":
                    return new Car(brand, 0); // размер 0 - модели добавятся позже
                case "Bike":
                    return new Bike(brand, 0);
                case "Scooter":
                    return new Scooter(brand, 0);
                case "ATV":
                    return new ATV(brand, 0);
                case "Moped":
                    return new Moped(brand, 0);
                default:
                    throw new IllegalArgumentException("Неизвестный тип транспортного средства: " + className);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка создания " + className, e);
        }
    }
    public static Vehicle createVehicle(String brand, int size, Vehicle vehicle) throws Exception{
        try {
            Class<?> carClass = vehicle.getClass();
            Constructor<?> constructor = carClass.getConstructor(String.class, int.class);
            return (Vehicle) constructor.newInstance(brand, size);
        }catch (Exception e){
            System.err.println("Ошибка создания объекта: " + e.getMessage());
            return null;
        }
    }
    public static double getAveragePrice(Vehicle... vehicles) {
        if (vehicles == null || vehicles.length == 0) {
            return 0;
        }

        double sum = 0;
        int totalModels = 0;

        for (Vehicle vehicle : vehicles) {
            double[] prices = vehicle.getModelsPrices();
            for (double price : prices) {
                sum += price;
            }
            totalModels += vehicle.getSize();
        }

        return totalModels > 0 ? sum / totalModels : 0;
    }
}
