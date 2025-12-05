public class ModelPriceOutOfBoundsException extends  RuntimeException {
    public ModelPriceOutOfBoundsException(double price){
        super("Цена " + price + " должна быть больше 0");
    }
}
