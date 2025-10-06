import java.io.*;
import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) throws IOException, DuplicateModelNameException, ClassNotFoundException, CloneNotSupportedException {

        Vehicle brand = new Bike("Model", 6);
        Vehicle bike = new Bike("Model", 6);
        VehicleMethod.printModelsNames(brand);
        VehicleMethod.printModelsNames(bike);
        if(bike.equals(brand)){
            System.out.println("одинаковые");
        }
        else{
            System.out.println("чет не работает");
        }

        System.out.println(brand.toString());
        System.out.println(bike.toString());
        Bike cloneBike = (Bike) ((Bike) bike).clone();
        System.out.println(cloneBike.equals(bike));
        VehicleMethod.printModelsNames(cloneBike);

          /*
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
            Bike v = (Bike) ois.readObject();
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
        }*/
    }
}