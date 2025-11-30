import java.io.Serializable;

public interface Vehicle extends Serializable, Cloneable{
    void setBrand(String brand);
    String getBrand();
    void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelException;
    void setModelPrice(String name, double newPrice) throws NoSuchModelException;
    double[] getModelsPrices();
    String[] getModelsNames();
    double getModelPrice(String name) throws NoSuchModelException;
    void addModel(String name, double price) throws DuplicateModelNameException;
    void removeModel(String name) throws NoSuchModelException;
    int getSize();


}
