import java.util.concurrent.locks.ReentrantLock;

public class LockPriceTread implements Runnable{
    Vehicle vehicle;
    ReentrantLock rl = new ReentrantLock();
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
