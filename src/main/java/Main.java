import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IOException {

        fileUtils fileUtils = new fileUtils();


        fileUtils.phoneNumber("PhoneFile.txt");
        System.out.println("----------------------------");


        fileUtils.printWordFrequencies("words.txt");
        System.out.println("----------------------------");


        fileUtils.loadgson("user.txt", "user.json");

        System.out.println("----------------------------");

    }


}