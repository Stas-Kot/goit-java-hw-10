public class Main {
    public static void main(String[] args) {
        PhoneBookReader phoneBookReader = new PhoneBookReader(".\\src\\main\\resources\\file.txt");
//        phoneBookReader.print();

        UserList userList = new UserList(".\\src\\main\\resources\\file2.txt");
        userList.print();


        WordFrequency wordFrequency = new WordFrequency(".\\src\\main\\resources\\file3.txt");
//        wordFrequency.print();
    }
}
