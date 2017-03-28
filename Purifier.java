import java.util.regex.Pattern;

public class Purifier {
    public static final Pattern p = Pattern.compile("[\\W\\d]+", Pattern.UNICODE_CHARACTER_CLASS);

    public static String[] purify(final String text) {
        return p.splitAsStream(text.toLowerCase())
                .filter(w -> w.length() > 3)
                .toArray(String[]::new);
    }
}
