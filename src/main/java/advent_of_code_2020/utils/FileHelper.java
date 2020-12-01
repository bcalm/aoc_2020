package advent_of_code_2020.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileHelper {

    private final String filePath;

    public FileHelper(String filePath) {
        this.filePath = filePath;
    }

    private String read() throws IOException {
        File file = new File(this.filePath);
        Scanner sc = new Scanner(file);
        StringBuilder content = new StringBuilder();
        while (sc.hasNextLine()) {
            content.append(sc.nextLine());
            content.append("\n");
        }
        return String.valueOf(content);
    }

    public String getInputs() {
        String input = "";
        try {
            input = this.read();
        } catch (IOException e) {
            input = "File is not present";
        }
        return input;
    }
}
