package su.vigo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Settings {
    String get(String key);

    static Settings load(String path) throws IOException {
        return new SettingsImpl(path);
    }
}

class SettingsImpl extends HashMap<String, String> implements Settings {

    private static final Pattern LINE_PATTERN;

    static {
        LINE_PATTERN = Pattern.compile("^\\s*(\\w+)\\s*=\\s*(.*)\\s*$");
    }

    SettingsImpl(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Matcher matcher = LINE_PATTERN.matcher(line);
                if (matcher.find()) put(matcher.group(1), matcher.group(2));
            }
        }

    }

    @Override
    public String get(String key) {
        return null;
    }
}