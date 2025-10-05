import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Bike implements Vehicle, Serializable {
    private String brand;
    @Override
    public void setBrand(String newBrand){
        this.brand = newBrand;
    }
    @Override
    public String getBrand(){
        return this.brand;
    }
    private class Model implements Serializable{
        private String name = null;
        private double price = Double.NaN;
        Model prev;
        Model next;
        public Model(String name, double price){
            this.name = name;
            this.price = price;
        }
        public Model(){

        }
    }
    private int size;
    private Model head;
    private long lastModified;
    public long getLastModified(){
        return lastModified;
    }
    {
        lastModified = System.currentTimeMillis();
    }
    @Override
    public void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelException{
        Model q = head.next;
        Model f = null;
        while(q != head){
            if(newName.equals(q.name)){
                throw new DuplicateModelNameException(newName);
            }
            if(f == null && oldName.equals(q.name)){
                f = q;
            }
            q = q.next;
        }
        if(f == null){
            throw new NoSuchModelException(oldName);
        }
        else{
            f.name = newName;
            this.lastModified = System.currentTimeMillis();
        }
    }
    @Override
    public void setModelPrice(String name, double newPrice)  throws NoSuchModelException{
        if(newPrice < 0){
            throw new ModelPriceOutOfBoundsException(newPrice);
        }
        else{
            Model q = head.next;
            while(q != head && !name.equals(q.name)){ //
                q = q.next;
            }
            if(q == head){
                throw new NoSuchModelException(name);
            }
            else{
                q.price = newPrice;
                this.lastModified = System.currentTimeMillis();
            }
        }
    }
    @Override
    public double[] getModelsPrices(){
        double[] prices = new double[size];
        Model m = head.next;
        int i = 0;
        while(m != head){
            prices[i] = m.price;
            i++;
            m = m.next;
        }
        return prices;
    }
    @Override
    public String[] getModelsNames(){
        String[] names = new String[size];
        Model m = head.next;
        int i = 0;
        while(m != head){
            names[i] = m.name;
            i++;
            m = m.next;
        }
        return names;
    }
    @Override
    public double getModelPrice(String name) throws NoSuchModelException{
        Model m = head.next;
        while(m != head && !name.equals(m.name)){
            m = m.next;
        }
        if(m == head){
            throw new NoSuchModelException(name);
        }
        else{
            return m.price;
        }

    }
    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException{
        if(price < 0){
            throw new ModelPriceOutOfBoundsException(price);
        }
        else{
            Model q = head.next;
            while(q != head){
                if(q.name.equals(name)){
                    throw new DuplicateModelNameException(name);
                }
                q = q.next;
            }
            Model newModel = new Model(name, price);
            head.prev.next = newModel;
            newModel.next = head;
            newModel.prev = head.prev;
            head.prev = newModel;
            size++;
            this.lastModified = System.currentTimeMillis();
        }

    }
    @Override
    public void removeModel(String name) throws NoSuchModelException{
        Model m = head.next;
        while(m != head && !name.equals(m.name)){
            m = m.next;
        }
        if(name.equals(m.name)){
            m.prev.next = m.next;
            m.next.prev = m.prev;
            size--;
            this.lastModified = System.currentTimeMillis();
        }
        else{
            throw new NoSuchModelException(name);
        }
    }
    @Override
    public int getSize(){
        return this.size;
    }
    public Bike(String brand, int size){
        head = new Model();
        this.brand = brand;
        this.size = size;
        head.next = head;
        head.prev = head;
        Model m;
        for(int i =0; i < size; i++){
            m = new Model(brand + " " + (i+1), 145000);
            m.next = head;
            m.prev = head.prev;
            head.prev.next = m;
            head.prev = m;
        }

    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(brand);
        sb.append("\n");
        String[] names = getModelsNames();
        double[] prices = getModelsPrices();
        Model m = head.next;
        while(m != head){
            sb.append(m.name).append(", ").append(m.price).append("\n");
            m = m.next;
        }
        return sb.toString();
    }
    @Override
    public int hashCode(){
        int result = Objects.hash(brand);
        result = 31 * result + Arrays.hashCode(getModelsNames());
        result = 31 * result + Arrays.hashCode(getModelsPrices());
        result = 31 * result + getSize();
        return result;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) obj;
        if(hashCode() != vehicle.hashCode()) return false;
        if(!getBrand().equals(vehicle.getBrand())) return false;
        if(getSize() != vehicle.getSize()) return false;
        if(!Arrays.equals(getModelsNames(), vehicle.getModelsNames())) return false;
        return Arrays.equals(getModelsPrices(), vehicle.getModelsPrices());
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Bike cloned = (Bike) super.clone();
        String[] names = getModelsNames();
        double[] prices = getModelsPrices();
        cloned.head;
        cloned.head.next = head;
        cloned.head.prev = head;
        for(int i = 0; i < getSize(); i++){
            cloned.models[i] = new Car.Model(names[i], prices[i]);
        }
        return cloned;
    }
}
