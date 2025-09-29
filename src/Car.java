import java.io.Serializable;
import java.util.Arrays;

public class Car implements Vehicle, Serializable{
    private String brand;
    private Model[] models;
    @Override
    public String getBrand(){
        return this.brand;
    }
    @Override
    public void setBrand(String brand){
        this.brand = brand;
    }
    private class Model implements Serializable{
        private String name;
        private double price;

        public Model(String name, double price){
            this.name = name;
            this.price = price;
        }
    }
    @Override
    public void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelException{
        int index = -1;
        for(int i = 0; i< models.length; i++){
            if(newName.equals(models[i].name)){
                throw new DuplicateModelNameException(newName);
            }
            if(index == -1 && oldName.equals(models[i].name)){
                index = i;
            }
        }
        if(index != -1){
            models[index].name = newName;
        }
        else{
            throw new NoSuchModelException(oldName);
        }
    }
    @Override
    public String[] getModelsNames(){
        String[] names = new String[models.length];
        for(int i =0; i < models.length; i++){
            names[i] = models[i].name;
        }
        return names;
    }
    @Override
    public double getModelPrice(String name) throws NoSuchModelException{
        int i = 0;
        while(!name.equals(models[i].name) && i < models.length){
            i++;
        }
        if(!name.equals(models[i].name)){
            throw new NoSuchModelException(name);
        }
        else{
            return models[i].price;
        }
    }
    @Override
    public void setModelPrice(String name, double newPrice) throws NoSuchModelException{

        if(newPrice < 0){
            throw new ModelPriceOutOfBoundsException(newPrice);
        }
        else{
            int i = 0;
            while(i < models.length && !name.equals(models[i].name)){
                i++;
            }
            if(i >= models.length){
                throw new NoSuchModelException(name);
            }
            else{
                models[i].price = newPrice;
            }
        }
    }
    @Override
    public double[] getModelsPrices(){
        double[] prices = new double[models.length];
        for(int i =0; i < models.length; i++){
            prices[i] = models[i].price;
        }
        return prices;
    }
    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException{
        if(price < 0){
            throw new ModelPriceOutOfBoundsException(price);
        }
        else{
            for(int i = 0; i < models.length; i++){
                if(models[i].name.equals(name)){
                    throw new DuplicateModelNameException(name);
                }
            }

            Model m = new Model(name, price);
            models = Arrays.copyOf(models, models.length + 1);
            models[models.length-1] = m;
            //2
        }
    }
    @Override
    public void removeModel(String name) throws NoSuchModelException{
        int i = 0;
        while(i < models.length && !name.equals(models[i].name)){
            i++;
        }
        if(i >= models.length){
            throw new NoSuchModelException(name);
        }
        else{
            System.arraycopy(models, i+1, models, i, models.length - i-1);
            models = Arrays.copyOf(models, models.length-1);
            //2
        }
    }
    @Override
    public int getSize(){
        return models.length;
    }
    public Car(String brand, int size){
        this.brand = brand;
        Model[] models = new Model[size];
        for(int i = 0; i < size; i++){
            models[i] = new Model(brand +" "+ (i + 1), 145000);
        }
        this.models = models;
    }
}
