public class SynchronizeNameThread implements Runnable{
    private TransportSynchronizer ts;
    @Override
    public void run() {
        while(ts.canPrintModel()){
            try {
                ts.printModel();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public SynchronizeNameThread(TransportSynchronizer ts){
        this.ts = ts;
    }
}
