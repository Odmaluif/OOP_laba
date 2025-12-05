
public class PriceThread extends Thread{
    private Vehicle vehicle;
    @Override
    public void run(){
        VehicleMethod.printModelsPrices(vehicle);
    }
    public PriceThread(Vehicle vehicle){
        this.vehicle = vehicle;
    }
}
