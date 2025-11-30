public class NameThread extends Thread{
    private Vehicle vehicle;
    @Override
    public void run(){
        VehicleMethod.printModelsNames(vehicle);
    }
    public NameThread(Vehicle vehicle){
        this.vehicle = vehicle;
    }
}
