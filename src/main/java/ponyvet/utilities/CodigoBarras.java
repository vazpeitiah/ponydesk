package ponyvet.utilities;

public class CodigoBarras {
    
    private static final String NUMBERS = "0123456789";
    
    public static final int EAN13 = 13;
    
    public static String generarCodigoGenerico (int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * NUMBERS.length());
            builder.append(NUMBERS.charAt(character));
        }
        return builder.toString();
    }
    
}
