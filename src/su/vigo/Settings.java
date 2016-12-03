package su.vigo;


import java.io.*;
import java.util.HashMap;

public interface Settings {
    String get(String key);

    static Settings load(String path) throws IOException {
        return new SettingsImpl(path);
    }
}

class SettingsImpl extends HashMap<String, String> implements Settings {

    SettingsImpl(String filePath) throws IOException {
        try (IniLoader iniLoader = new IniLoader(filePath)) {
            for (Setting setting : iniLoader) {
                put(setting.key, setting.value);
            }
        }
    }

    @Override
    public String get(String key) {
        return super.get(key);
    }
}