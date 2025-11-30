public class BrandThread implements Runnable{
    Vehicle vehicle;
    public void run(){
        System.out.println(vehicle.getBrand() + " (Поток: " + Thread.currentThread().getName() + ")");
    }
    public BrandThread(Vehicle vehicle){
        this.vehicle = vehicle;
    }
}
