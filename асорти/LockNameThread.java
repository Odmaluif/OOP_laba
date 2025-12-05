import java.util.concurrent.locks.ReentrantLock;

public class LockNameThread implements Runnable{
    private Vehicle vehicle;
    private ReentrantLock rl = new ReentrantLock();
    public void run(){
        rl.lock();
        try{
            VehicleMethod.printModelsNames(vehicle);
        }finally {
            rl.unlock();
        }
    }
    public LockNameThread(Vehicle vehicle, ReentrantLock rl){
        this.vehicle = vehicle;
        this.rl = rl;
    }
}
