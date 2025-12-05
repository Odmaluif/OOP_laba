public class TransportSynchronizer {
    private Vehicle transport;
    private volatile int current = 0;
    private final Object lock = new Object();
    private boolean set = false;

    public TransportSynchronizer(Vehicle transport) {
        this.transport = transport;
    }
    public void printPrice() throws InterruptedException {
        double val;
        synchronized(lock) {
            double[] prices = transport.getModelsPrices();
            if (!canPrintPrice()) throw new InterruptedException();
            while (set) {
                lock.wait();
            }
            val = prices[current];
            System.out.println("Print price: " + String.format("%.2f", val));
            set = true;
            lock.notifyAll();
        }
    }

    public void printModel() throws InterruptedException {
        synchronized(lock) {
            String[] models = transport.getModelsNames();
            if (!canPrintModel()) throw new InterruptedException();
            while (!set) {
                lock.wait();
            }
            System.out.println("Print model: " + models[current++]);
            set = false;
            lock.notifyAll();
        }
    }

    public boolean canPrintPrice() {
        return (!set && current < transport.getSize()) || (set && current < transport.getSize() - 1);
    }

    public boolean canPrintModel() {
        return current < transport.getSize();
    }


}
