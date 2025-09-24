import java.util.DuplicateFormatFlagsException;

public class Main {
    public static void main(String[] args) {
        Vehicle brand = new Bike("Model", 6);
        VehicleMethod.printModelsNames(brand);

        try {
            brand.addModel("Model 5", 1300000);
        } catch (DuplicateModelNameException e) {
            System.out.println(e.getMessage());
        }
        VehicleMethod.printModelsNames(brand);
        try{
            brand.setModelName("Model 2", "Model 4");
        }catch (DuplicateModelNameException | NoSuchModelException e){
            System.out.println(e.getMessage());
        }
        try{
            brand.setModelPrice("Model 9", 343);
        }catch(NoSuchModelException e){
            System.out.println(e.getMessage());
        }
        VehicleMethod.printModelsNames(brand);
        try{
            brand.removeModel("Model 4");
        }catch (NoSuchModelException e){
            System.out.println(e.getMessage());
        }
        VehicleMethod.printModelsNames(brand);
        VehicleMethod.printModelsPrices(brand);
    }
}