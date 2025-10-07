import java.io.*;
import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) throws IOException, DuplicateModelNameException, ClassNotFoundException {
        Bike brand = new Bike("Model", 6);
        VehicleMethod.printModelsNames(brand);
        try{
            System.out.println("Байтовый поток");
            FileOutputStream fos = new FileOutputStream("byte.txt");
            VehicleMethod.outputVehicle(brand, fos);
            fos.close();
            FileInputStream fis = new FileInputStream("byte.txt");
            VehicleMethod.printModelsNames(VehicleMethod.inputVehicle(fis));
            fis.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println("Символьный поток");
            FileWriter fw = new FileWriter("symbol.txt");
            VehicleMethod.writeVehicle(brand, fw);
            fw.close();
            FileReader fr = new FileReader("symbol.txt");
            VehicleMethod.printModelsNames(VehicleMethod.readVehicle(fr));
            fr.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println("сериализация, десиреализация");
            FileOutputStream fos2 = new FileOutputStream("serialization.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos2);
            fos2.close();
            oos.writeObject(brand);
            oos.close();
            FileInputStream fis2 = new FileInputStream("serialization.txt");
            fis2.close();
            ObjectInputStream ois = new ObjectInputStream(fis2);
            Bike v = (Bike) ois.readObject();
            ois.close();
            VehicleMethod.printModelsNames(v);
            System.out.println(v.getLastModified());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            System.out.println("Ввод и вывод System.in и System.out");
            System.out.println("Символьный поток транспортного средства бренда " + brand.getBrand());
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            VehicleMethod.writeVehicle(brand, osw);
            System.out.println("Введите символьный поток");
            InputStreamReader isr = new InputStreamReader(System.in);
            VehicleMethod.printModelsNames(VehicleMethod.readVehicle(isr));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}