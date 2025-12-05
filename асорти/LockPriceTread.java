import java.util.concurrent.locks.ReentrantLock;

public class LockPriceTread implements Runnable{
    private Vehicle vehicle;
    private ReentrantLock rl = new ReentrantLock();
    public void run(){
        rl.lock();
        try{
            VehicleMethod.printModelsPrices(vehicle);
        }finally {
            rl.unlock();
        }
    }
    public LockPriceTread(Vehicle vehicle, ReentrantLock rl){
        this.vehicle = vehicle;
        this.rl = rl;
    }
}
