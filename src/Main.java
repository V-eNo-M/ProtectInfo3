import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            Rabin rab = new Rabin();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
