import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scooter implements Vehicle, Serializable, Cloneable {
    private String brand;
    private Map<String, Double> models;
    public Scooter(String brand, int size) {
        this.brand = brand;
        this.models = new HashMap<>();
        for (int i = 0; i < size; i++) {
            models.put(brand + " " + (i + 1), 145000.0);
        }
    }
    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelException {
        if (!models.containsKey(oldName)) {
            throw new NoSuchModelException(oldName);
        }
        if (models.containsKey(newName)) {
            throw new DuplicateModelNameException(newName);
        }
        double price = models.remove(oldName);
        models.put(newName, price);
    }

    @Override
    public void setModelPrice(String name, double newPrice) throws NoSuchModelException {
        if (newPrice < 0) {
            throw new ModelPriceOutOfBoundsException(newPrice);
        }
        if (!models.containsKey(name)) {
            throw new NoSuchModelException(name);
        }
        models.put(name, newPrice);
    }

    @Override
    public double[] getModelsPrices() {
        double[] prices = new double[models.size()];
        int i = 0;
        for (Double price : models.values()) {
            prices[i++] = price;
        }
        return prices;
    }

    @Override
    public String[] getModelsNames() {
        return models.keySet().toArray(new String[0]);
    }

    @Override
    public double getModelPrice(String name) throws NoSuchModelException {
        if (!models.containsKey(name)) {
            throw new NoSuchModelException(name);
        }
        double price = models.get(name);
        return price;

    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException(price);
        }
        if (models.containsKey(name)) {
            throw new DuplicateModelNameException(name);
        }
        models.put(name, price);
    }

    @Override
    public void removeModel(String name) throws NoSuchModelException {
        if (!models.containsKey(name)) {
            throw new NoSuchModelException(name);
        }
        models.remove(name);
    }

    @Override
    public int getSize() {
        return models.size();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) obj;
        return getBrand().equals(vehicle.getBrand()) &&
                Arrays.equals(getModelsNames(), vehicle.getModelsNames()) &&
                Arrays.equals(getModelsPrices(), vehicle.getModelsPrices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, Arrays.hashCode(getModelsNames()), Arrays.hashCode(getModelsPrices()));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Scooter cloned = (Scooter) super.clone();
        cloned.models = new HashMap<>(this.models);
        return cloned;
    }
}
