import java.io.*;
import java.util.*;

public class Graham {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static long iterationsCount = 0;

    public static int convexHull(Point[] s) {
        iterationsCount = 0;
        int n = s.length;
        if (n <= 2) return n;

        // 1. Находим точку с минимальной y и максимальной x координатой
        int minIdx = 0;
        for (int i = 1; i < n; i++) {
            iterationsCount++;
            if (s[i].y < s[minIdx].y ||
                    (s[i].y == s[minIdx].y && s[i].x > s[minIdx].x)) {
                minIdx = i;
            }
        }

        // Меняем местами с первой точкой
        swap(s, 0, minIdx);

        // 2. Сортируем оставшиеся точки по полярному углу относительно S[0]
        Arrays.sort(s, 1, n, (a, b) -> {
            iterationsCount++;
            int orient = orientation(s[0], a, b);
            if (orient == 0) {
                return Integer.compare(dist(s[0], a), dist(s[0], b));
            }
            return orient > 0 ? -1 : 1;
        });

        // 3. Инициализация
        int k = 1;

        // 4. Построение оболочки
        for (int p = 2; p < n; p++) {
            iterationsCount++;
            // Удаляем точки, пока не получим правый поворот
            while (k > 0 && orientation(s[k - 1], s[k], s[p]) <= 0) {
                iterationsCount++;
                k--;
            }

            // Помещаем текущую точку на следующую позицию
            swap(s, p, k + 1);
            k++;
        }

        return k + 1; // Количество точек в оболочке
    }

    private static int orientation(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    private static int dist(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    private static void swap(Point[] arr, int i, int j) {
        Point temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) throws IOException {
        DataGenerator.generateTestFiles();

        // Сбор статистики
        PrintWriter stats = new PrintWriter("results.csv");
        stats.println("Size,Time(ns),Iterations");

        File dir = new File(".");
        File[] files = dir.listFiles((d, name) -> name.startsWith("data"));

        for (File file : files) {
            // Чтение данных
            Scanner sc = new Scanner(file);
            int size = sc.nextInt();
            Point[] points = new Point[size];
            for (int i = 0; i < size; i++) {
                points[i] = new Point(sc.nextInt(), sc.nextInt());
            }
            sc.close();

            // Замер времени
            long startTime = System.nanoTime();
            int hullSize = convexHull(points);
            long endTime = System.nanoTime();

            stats.println(size + "," + (endTime - startTime) + "," + iterationsCount);
        }

        stats.close();
    }
}
