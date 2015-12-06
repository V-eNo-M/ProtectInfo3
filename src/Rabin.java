import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by 1 on 03.12.2015.
 */
public class Rabin {
    private final int SIZE = 10000;
    private boolean[] easy = new boolean[SIZE];
    private int p;  //закрытый клч
    private int q;  //закрытый клч
    private int n;  //открытый клч
    private BigInteger c;   //зашифрованное число

    public Rabin() throws FileNotFoundException {
        buildEasy();
        createPandQ();
        crypto();
        decrypto();
    }

    private void buildEasy() {
        Arrays.fill(easy, true);
        easy[1] = false;
        for (int i=2; i*i < SIZE; i++)
            if (easy[i])
                for (int j=i*i; j < SIZE; j+=i)
                    easy[j] = false;
    }

    private int generateEasy() {
        Random rand = new Random();
        int m = 0;
        boolean correct = false;
        while(!correct) {
            m = rand.nextInt(10000);
            if (m < 1000)
                m+=1000;
            if (easy[m] == true && m % 4 == 3) {
                correct = true;
            }
        }
        return m;
    }

    private void createPandQ() {
        boolean correct = false;
        p = generateEasy();
        while(!correct) {
            q = generateEasy();
            if (p != q) {
                correct  = true;
            }
        }
        n = p * q;
    }

    public BigInteger crypto() throws FileNotFoundException {
        ReadFromFile read = new ReadFromFile();
        c = new BigInteger(read.getKey());
        c = c.multiply(c);
        System.out.println("Зашифрованный текст =  "+ c);
        return c;
    }

    public void decrypto() {
        int a1 = Integer.valueOf(c.pow((p + 1) / 4).mod(BigInteger.valueOf(p)).toString());
        int a2 = (Integer.valueOf(c.pow((p + 1) / 4).mod(BigInteger.valueOf(p)).toString()))*-1;
        int b1 = Integer.valueOf(c.pow((q + 1)/4).mod(BigInteger.valueOf(q)).toString());
        int b2 = (Integer.valueOf(c.pow((q + 1)/4).mod(BigInteger.valueOf(q)).toString()))*-1;

    }

    private int chinaTheorem(int a, int b, int p1, int q1) {
        int result = 0;
        int M = p1 * q1;
        // x  = a mod p1   x = b mod q1


        return result;
    }

}
