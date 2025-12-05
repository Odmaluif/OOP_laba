public class NoSuchModelException extends Exception{
    public String attrName;
    public NoSuchModelException(String name){
        super("Модель с именем " + name + " не найдена");
        attrName = name;
    }
}
