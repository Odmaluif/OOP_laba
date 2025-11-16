import java.io.Serializable;
import java.util.*;

public class ATV implements Vehicle, Serializable, Cloneable {
    private String brand;
    private List<Model> models;

    private class Model implements Serializable {
        String name;
        double price;

        Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public ATV(String brand, int size) {
        this.brand = brand;
        this.models = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            models.add(new Model(brand + " " + (i + 1), 145000.0));
        }
    }
    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelException {
        int index = -1;
        for (int i = 0; i < models.size(); i++) {
            if (newName.equals(models.get(i).name)) {
                throw new DuplicateModelNameException(newName);
            }
            if (index == -1 && oldName.equals(models.get(i).name)) {
                index = i;
            }
        }
        if (index != -1) {
            models.get(index).name = newName;
        } else {
            throw new NoSuchModelException(oldName);
        }
    }

    @Override
    public void setModelPrice(String name, double newPrice) throws NoSuchModelException {
        if (newPrice < 0) {
            throw new ModelPriceOutOfBoundsException(newPrice);
        }
        for (Model model : models) {
            if (name.equals(model.name)) {
                model.price = newPrice;
                return;
            }
        }
        throw new NoSuchModelException(name);
    }

    @Override
    public double[] getModelsPrices() {
        double[] prices = new double[models.size()];
        for (int i = 0; i < models.size(); i++) {
            prices[i] = models.get(i).price;
        }
        return prices;
    }

    @Override
    public String[] getModelsNames() {
        String[] names = new String[models.size()];
        for (int i = 0; i < models.size(); i++) {
            names[i] = models.get(i).name;
        }
        return names;
    }

    @Override
    public double getModelPrice(String name) throws NoSuchModelException {
        for (Model model : models) {
            if (name.equals(model.name)) {
                return model.price;
            }
        }
        throw new NoSuchModelException(name);
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException(price);
        }
        for (Model model : models) {
            if (model.name.equals(name)) {
                throw new DuplicateModelNameException(name);
            }
        }
        models.add(new Model(name, price));
    }

    @Override
    public void removeModel(String name) throws NoSuchModelException {
        for (int i = 0; i < models.size(); i++) {
            if (name.equals(models.get(i).name)) {
                models.remove(i);
                return;
            }
        }
        throw new NoSuchModelException(name);
    }

    @Override
    public int getSize() {
        return models.size();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(brand).append("\n");
        for (Model model : models) {
            sb.append(model.name).append(", ").append(model.price).append("\n");
        }
        return sb.toString();
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
        ATV cloned = (ATV) super.clone();
        cloned.models = new ArrayList<>();
        for (Model model : this.models) {
            cloned.models.add(new Model(model.name, model.price));
        }
        return cloned;
    }
}