import java.io.*;
import java.util.Random;

public class DataGenerator {
    public static void generateTestFiles() throws IOException {
        Random rand = new Random();
        int[] sizes = {100, 200, 500, 1000, 2000, 5000, 10000};

        for (int size : sizes) {
            for (int i = 0; i < 10; i++) { // 10 файлов каждого размера
                String filename = "data" + size + "_" + i + ".txt";
                try (PrintWriter out = new PrintWriter(filename)) {
                    out.println(size);
                    for (int j = 0; j < size; j++) {
                        out.println(rand.nextInt(10000) + " " + rand.nextInt(10000));
                    }
                }
            }
        }
    }
}
