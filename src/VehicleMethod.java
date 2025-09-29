import java.io.*;

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
            System.out.println("каПусто");
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

        Vehicle vehicle;
        if(className.equals("Car")){
            vehicle = new Car(brand, 0);
        }
        else if(className.equals("Bike")){
            vehicle = new Bike(brand, 0);
        }
        else{
            throw new IOException("Неизвестный тип транспортного средства: " + className);
        }
        for(int i = 0 ; i< size; i++){
            byte[] nameByte = new byte[dis.readInt()];
            dis.readFully(nameByte);
            String name = new String(nameByte);
            double price = dis.readDouble();
            vehicle.addModel(name, price);
        }
        return vehicle;
    }
    public static void writeVehicle(Vehicle v, Writer out) throws IOException{
        PrintWriter pw = new PrintWriter(out);
        pw.println(v.getClass().getSimpleName());
        pw.println(v.getBrand());
        pw.println(v.getSize());

        String[] names = v.getModelsNames();
        double[] prices = v.getModelsPrices();
        for(int i = 0; i< v.getSize(); i++){
            pw.println(names[i]);
            pw.println(prices[i]);
        }
        pw.flush();
    }
    public static Vehicle readVehicle(Reader in) throws IOException, DuplicateModelNameException {
        BufferedReader reader = new BufferedReader(in);

        String className = reader.readLine();
        String brand = reader.readLine();
        int size = Integer.parseInt(reader.readLine());
        Vehicle vehicle;
        if(className.equals("Car")){
            vehicle = new Car(brand, 0);
        }
        else if(className.equals("Bike")){
            vehicle = new Bike(brand, 0);
        }
        else{
            throw new IOException("Неизвестный тип транспортного средства: " + className);
        }
        for(int i = 0; i< size; i++){
            String name = reader.readLine();
            double price = Double.parseDouble(reader.readLine());
            vehicle.addModel(name, price);
        }
        return vehicle;
    }
}
