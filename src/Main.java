import java.io.*;
import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) throws IOException, DuplicateModelNameException, ClassNotFoundException {
        Vehicle brand = new Bike("Model", 6);
        VehicleMethod.printModelsNames(brand);
        try{
            System.out.println("Байтовый поток");
            FileOutputStream fos = new FileOutputStream("byte.txt");
            VehicleMethod.outputVehicle(brand, fos);
            FileInputStream fis = new FileInputStream("byte.txt");
            VehicleMethod.printModelsNames(VehicleMethod.inputVehicle(fis));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println("Символьный поток");
            FileWriter fw = new FileWriter("symbol.txt");
            VehicleMethod.writeVehicle(brand, fw);
            FileReader fr = new FileReader("symbol.txt");
            VehicleMethod.printModelsNames(VehicleMethod.readVehicle(fr));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println("сериализация, десиреализация");
            FileOutputStream fos2 = new FileOutputStream("serialization.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos2);
            oos.writeObject(brand);
            FileInputStream fis2 = new FileInputStream("serialization.txt");
            ObjectInputStream ois = new ObjectInputStream(fis2);
            Vehicle v = (Vehicle) ois.readObject();
            VehicleMethod.printModelsNames(v);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println("Ввод и вывод System.in и System.out");
            InputStreamReader isr = new InputStreamReader(System.in);
            Vehicle vehicle = VehicleMethod.readVehicle(isr);

            System.out.println("\n");
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            VehicleMethod.writeVehicle(vehicle, osw);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

//        Vehicle brand = new Car("Model", 6);
//        VehicleMethod.printModelsNames(brand);
//
//        try {
//            brand.addModel("Model 5", 1300000);
//        } catch (DuplicateModelNameException e) {
//            System.out.println(e.getMessage());
//        }
//        VehicleMethod.printModelsNames(brand);
//        try{
//            brand.setModelName("Model 2", "Model 4");
//        }catch (DuplicateModelNameException | NoSuchModelException e){
//            System.out.println(e.getMessage());
//        }
//        try{
//            brand.setModelPrice("Model 9", 343);
//        }catch(NoSuchModelException e){
//            System.out.println(e.getMessage());
//        }
//        VehicleMethod.printModelsNames(brand);
//        try{
//            brand.removeModel("Model 4");
//        }catch (NoSuchModelException e){
//            System.out.println(e.getMessage());
//        }
//        VehicleMethod.printModelsNames(brand);
//        VehicleMethod.printModelsPrices(brand);
    }
}