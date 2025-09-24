public final class VehicleMethod {
    public static double getArithmeticMean(Vehicle vehicle){
        double[] prices = vehicle.getModelsPrices();
        double sum = 0;
        double average;
        double quantity = vehicle.getSize();
        if(prices != null && vehicle.getSize() != 0){
            for(int i = 0; i < vehicle.getSize(); i++){
                sum += prices[i];
            }
            average = sum / quantity;
        }
        else{
            average = 0;
        }
        return average;
    }
    public static void printModelsNames(Vehicle vehicle){
        String[] models = vehicle.getModelsNames();
        if(models != null && vehicle.getSize() != 0){
            for(int i =0; i< vehicle.getSize(); i++){
                System.out.println(models[i]);
            }
        }
        else{
            System.out.println("Пусто");
        }
    }
    public static void printModelsPrices(Vehicle vehicle){
        double[] prices = vehicle.getModelsPrices();
        if(prices != null && vehicle.getSize() != 0){
            for(int i =0; i< vehicle.getSize(); i++){
                System.out.println(prices[i]);
            }
        }
        else{
            System.out.println("каПусто");
        }
    }
}
