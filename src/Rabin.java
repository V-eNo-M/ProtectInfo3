import java.io.FileNotFoundException;import java.lang.reflect.Array;import java.math.BigInteger;import java.util.Arrays;import java.util.Random;/** * Created by 1 on 03.12.2015. */public class Rabin {    private final int SIZE = 10000;    private boolean[] easy = new boolean[SIZE];    private int p;  //закрытый клч    private int q;  //закрытый клч    private int n;  //открытый клч    private BigInteger c;   //зашифрованное число    public Rabin() throws FileNotFoundException {        buildEasy();        createPandQ();        crypto();        decrypto();    }    private void buildEasy() {        Arrays.fill(easy, true);        easy[1] = false;        for (int i=2; i*i < SIZE; i++)            if (easy[i])                for (int j=i*i; j < SIZE; j+=i)                    easy[j] = false;    }    private int generateEasy() {        Random rand = new Random();        int m = 0;        boolean correct = false;        while(!correct) {            m = rand.nextInt(10000);            if (m < 1000)                m+=1000;            if (easy[m] == true && m % 4 == 3) {                correct = true;            }        }        return m;    }    private void createPandQ() {        boolean correct = false;        p = generateEasy();        while(!correct) {            q = generateEasy();            if (p != q) {                correct  = true;            }        }        n = p * q;    }    public BigInteger crypto() throws FileNotFoundException {        ReadFromFile read = new ReadFromFile();        c = new BigInteger(read.getKey());        c = c.multiply(c);        c = c.mod(BigInteger.valueOf(n));        System.out.println("Зашифрованный текст =  "+ c);        return c;    }    public void decrypto() {        int a1 = Integer.valueOf(c.pow((p + 1) / 4).mod(BigInteger.valueOf(p)).toString());        int a2 = (Integer.valueOf(c.pow((p + 1) / 4).mod(BigInteger.valueOf(p)).toString()))*-1;        int b1 = Integer.valueOf(c.pow((q + 1)/4).mod(BigInteger.valueOf(q)).toString());        int b2 = (Integer.valueOf(c.pow((q + 1)/4).mod(BigInteger.valueOf(q)).toString()))*-1;        int r1 = chinaTheorem(p, q ,a1, b1 );        int r2 = chinaTheorem(p, q ,a2, b1 );        int r3 = chinaTheorem(p, q ,a1, b2 );        int r4 = chinaTheorem(p, q ,a2, b2 );        System.out.println("P = " + p);        System.out.println("q = " + q);        System.out.println("n = " + n);        System.out.println("r1 = " + r1);        System.out.println("r2 = " + r2);        System.out.println("r3 = " + r3);        System.out.println("r4 = " + r4);    }    private int chinaTheorem(int a, int b, int p1, int q1) {            int m = a * b;            int x = 0;            int y = m / a;            int s = inverse(y, a);            while (s < 0)                s += a;            int c = (p1 * s) % a;            x = (x + c * y) % m;            y = m / b;            s = inverse(y, b);            while (s < 0)                s += b;            c = (q1 * s) % b;            x = (x + c * y) % m;            while (x < 0)            x += n;        return x;    }    private int d, x , y;    public int inverse(int a, int n)    {        extendedEuclid(a, n);        if (d == 1)            return x;        return 0;    }    public void extendedEuclid(int a, int b)    {        int q, r, x1, x2, y1, y2;        if (b == 0) {            d = a;            x = 1;            y = 0;        }        x2 = 1;        x1 = 0;        y2 = 0;        y1 = 1;        while (b > 0)        {            q = a / b;            r = a - q * b;            x = x2 - q * x1;            y = y2 - q * y1;            a = b;            b = r;            x2 = x1;            x1 = x;            y2 = y1;            y1 = y;        }        d = a;        x = x2;        y = y2;    }}