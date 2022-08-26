import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PhoneBookReader {
    private final String path;

    public String getPath() {
        return path;
    }

    public PhoneBookReader(String path) {
        this.path = path;
    }

    public void print() {
        File file = new File(getPath());

        if (!file.exists()) {
            throw new RuntimeException("File with name " + file.getName() + " not exist");
        }
        String numbers = "";

        try (FileInputStream inputStream = new FileInputStream(file)) {
            int ch = inputStream.read();

            while (ch != -1) {
                numbers += (char) ch;
                ch = inputStream.read();
            }
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
        String[] arr;
        arr = numbers.split(System.lineSeparator());

        for (String number : arr) {
            boolean validNumber = false;
            if (number.charAt(0) == '(' && number.charAt(4) == ')' && number.charAt(5) == ' ' && number.charAt(9) == '-' && number.length() == 14) {
                for (int i = 1; i < number.length(); i++) {
                    if (i == 4 || i == 5 || i == 9) {
                    continue;
                    }
                     if (Character.isDigit(number.charAt(i))) {
                            validNumber = true;
                        } else {
                            validNumber = false;
                            break;
                        }

                }
                if (validNumber) {
                    System.out.println(number);
                }
            } else if (number.charAt(3) == '-' && number.charAt(7) == '-' && number.length() == 12) {
                for (int i = 0; i < number.length(); i++) {
                    if (i == 3 || i == 7) {
                        continue;
                    }
                    if (Character.isDigit(number.charAt(i))) {
                        validNumber = true;
                    } else {
                        validNumber = false;
                        break;
                    }
                }
                if (validNumber) {
                    System.out.println(number);
                }
            }
        }
    }
}