package ponyvet.utilities;

import java.util.regex.Pattern;

public class Validar {
    
    public static boolean isFloat(String text){
        return Pattern.matches("[+]?([0-9]+([.][0-9]*)?|[.][0-9]+)", text);
    }
    
    public static boolean isAlphaNumericKey(String text){
        return Pattern.matches("[A-Za-z0-9]*", text);
    }
    
    public static boolean isAlphaNumericText(String text){
        return Pattern.matches("[A-Za-z0-9Ññ#%.,¿?'!¡& ]*", text);
    }
    
    public static boolean isNumericOnly(String text){
        return Pattern.matches("[0-9]*", text);
    }
    
    public static boolean isCellPhoneNumber(String text){
        return Pattern.matches("^\\d{10}$", text);
    }
}
