import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class WordFrequency {
    private final String path;

    public String getPath() {
        return path;
    }

    public WordFrequency(String path) {
        this.path = path;
    }

    public void print() {
        File file = new File(getPath());

        if (!file.exists()) {
            throw new RuntimeException("File with name " + file.getName() + " not exist");
        }
        String text = "";

        try (FileInputStream inputStream = new FileInputStream(file)) {
            int ch = inputStream.read();

            while (ch != -1) {
                text += (char) ch;
                ch = inputStream.read();
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
        String[] arr;
        text = text.replaceAll("\r\n", " ");
        while (text.contains("  ")) {
            String replaced = text.replace("  ", " ");
            text = replaced;
        }
        arr = text.split(" ");
        countWordFreq(arr);
    }

    static class Pair {
        private final String word;
        private final int count;

        public Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return getWord() + " " + getCount();
        }
    }


    public static void countWordFreq(String[] text) {
        List<Pair> list = new ArrayList<>();
        for (String word : text) {
            if (word != null) {
                list.add(new Pair(word, countOccurrences(text, word)));
            }

        }

        list.sort(new FreqComparator());


        for (Pair pair : list) {
            System.out.println(pair);
        }

    }

    static class FreqComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair a, Pair b) {
            return a.count > b.count ? -1 : a.count == b.count ? 0 : 1;
        }
    }


    static int countOccurrences(String[] s, String c) {
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] != null) {
                if (s[i].equals(c)) {
                    count++;
                    s[i] = null;
                }
            }
        }
        return count;
    }
}
