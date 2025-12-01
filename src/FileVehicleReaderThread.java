import java.io.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class FileVehicleReaderThread implements Runnable{
    private BlockingQueue<Vehicle> bq;
    String fileName;
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String brand = br.readLine();
            if(brand != null){
                Vehicle vehicle = new Car(brand, 5);
                bq.put(vehicle);
                System.out.println("Добавлено транспортное средство: " + brand);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public FileVehicleReaderThread(String fileName, BlockingQueue<Vehicle> bq){
        this.fileName = fileName;
        this.bq = bq;

    }
}
