package su.vigo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniLoader extends BufferedReader implements Iterable<Setting> {

    public IniLoader(String fileName) throws FileNotFoundException {
        super(new FileReader(fileName));
    }

    private static final Pattern LINE_PATTERN;

    static {
        LINE_PATTERN = Pattern.compile("^\\s*(\\w+)\\s*=\\s*(.*)\\s*$");
    }

    private Setting findNext() throws IOException {
        String line = readLine();
        if (line == null) {
            return null;
        }
        Matcher m = LINE_PATTERN.matcher(line);

        if (m.find()) {
            return new Setting(m.group(1), m.group(2));
        }

        return findNext();
    }

    private class Iterator implements java.util.Iterator<Setting> {

        Setting setting;

        @Override
        public boolean hasNext() {
            try {
                return (setting = findNext()) != null;
            } catch (IOException e) {
                return false;
            }

        }

        @Override
        public Setting next() {
            return setting;
        }
    }

    @Override
    public java.util.Iterator<Setting> iterator() {
        return new Iterator();
    }
}
