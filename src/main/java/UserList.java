import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UserList {
    private final String path;
    private static final String RELATIVE_PATH = ".\\src\\main\\resources\\out\\user.json";

    public String getPath() {
        return path;
    }

    public UserList(String path){
        this.path = path;
    }

    public void print() {
        File file = new File(getPath());

        if(!file.exists()){
            throw new RuntimeException("File with name " + file.getName() + " not exist");
        }

        String users = "";

        try(FileInputStream inputStream = new FileInputStream(file)) {
            int ch = inputStream.read();

            while(ch != -1) {
                users+=(char) ch;
                ch = inputStream.read();
            }
        } catch(IOException e) {
            System.err.print(e.getMessage());
        }
        String[] arr;
        arr = users.split(System.lineSeparator());
        System.out.println(Arrays.toString(arr));
        User[] usersList = new User[arr.length-1];

        for (int i = 1; i < arr.length; i++) {
            String name = arr[i].split(" ")[0];
            String age = arr[i].split(" ")[1];
            Scanner s = new Scanner(age);
            int num = s.nextInt();
            usersList[i-1] = new User(name, num);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(usersList);
        System.out.println(json);
        File user = new File(RELATIVE_PATH);
        if(!user.exists()){
            user.getParentFile().mkdirs();
            try {
                user.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        try(FileOutputStream outputStream = new FileOutputStream(user)) {
            outputStream.write(json.getBytes());
        } catch(IOException e) {
            System.err.println("Exception!!!" + e.getMessage());
        }
    }




    static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
