package sample;

/**
 *
 * @author Administrator
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String value = "-2013.22";
        System.out.println(value.matches("[+-]?\\d*(\\.\\d+)?"));
    }
}
