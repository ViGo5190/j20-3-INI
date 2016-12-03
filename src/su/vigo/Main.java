package su.vigo;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Settings settings = Settings.load("settings.ini");
        System.out.println(settings.get("username"));

        for (String setting : settings.keys()) {
            System.out.println(setting);
        }
    }
}
