import com.sun.jdi.request.ThreadDeathRequest;

import java.io.*;
import java.lang.reflect.Executable;
import java.util.DuplicateFormatFlagsException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
    public static void main(String[] args) throws Exception {
        task4();
    }
    public void task1(){
        Vehicle vehicle = new ATV("ATV", 10000);
        Thread thread1 = new PriceThread(vehicle);
        Thread thread2 = new NameThread(vehicle);

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);

        thread2.start();
        thread1.start();
    }
    public static void task2(){
        Vehicle vehicle = new Bike("Bike", 100);
        TransportSynchronizer ts = new TransportSynchronizer(vehicle);
        SynchronizeNameThread snt = new SynchronizeNameThread(ts);
        SynchronizePriceThread spt = new SynchronizePriceThread(ts);
        var thread1 = new Thread(snt);
        var thread2 = new Thread(spt);
        thread2.start();
        thread1.start();
    }
    public static void task3(){
        ReentrantLock rl = new ReentrantLock();
        Vehicle vehicle = new Moped("Moped", 70);
        Runnable runnable1 = new LockNameThread(vehicle, rl);
        Runnable runnable2 = new LockPriceTread(vehicle, rl);
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
    public static void task4(){
        Vehicle moped = new Moped("moped", 4);
        Vehicle bike = new Bike("bike", 4);
        Vehicle atv = new ATV("atv", 4);
        Vehicle car = new Car("car", 4);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new BrandThread(moped));
        executor.execute(new BrandThread(bike));
        executor.execute(new BrandThread(atv));
        executor.execute(new BrandThread(car));
        executor.shutdown();
    }
    public static Vehicle test(String args[]) throws Exception {
        String className = args[0];
        String methodName = args[1];
        String modelName = args[2];
        double newPrice = Double.parseDouble(args[3]);
        Class<?> carClass = Class.forName(className);
        Vehicle car = (Vehicle) carClass.getConstructor(String.class, int.class).newInstance(className, 14);
        //System.out.println(car);
        Method method = carClass.getMethod(methodName, String.class, double.class);
        method.invoke(car, modelName, newPrice);
        return car;
    }
}