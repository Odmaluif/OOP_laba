public class SynchronizePriceThread implements Runnable{
    private TransportSynchronizer ts;
    @Override
    public void run() {
        while(ts.canPrintPrice()){
            try {
                ts.printPrice();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public SynchronizePriceThread(TransportSynchronizer ts){
        this.ts = ts;
    }
}
