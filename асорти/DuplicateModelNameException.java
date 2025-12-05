public class DuplicateModelNameException extends Exception{
    public String attrName;
    public DuplicateModelNameException(String name){
        super("Модель с именем " + name + " уже существует");
        attrName = name;
    }
}
