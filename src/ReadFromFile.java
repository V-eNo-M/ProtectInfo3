import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 1 on 15.11.2015.
 */
public class ReadFromFile {
    private String key;
    private final String fileNameKey = new String("key.txt");
    /*private final String fileNameText = new String("text.txt");
    private final String fileNameCrText = new String("crypto.txt");*/

    public ReadFromFile() throws FileNotFoundException {
        Read();
    }
    //читаем из файла число
    private void Read() throws FileNotFoundException {
        Scanner in = null;
        try {
            in = new Scanner(new File(fileNameKey));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        key = in.nextLine();
        in.close();
    }

    public String getKey() {
        return key;
    }
}

