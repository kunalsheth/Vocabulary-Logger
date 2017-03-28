import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Dictionary {

    protected final String[] wordsByFrequency;

    public Dictionary(final Stream<String> corpus) {
        final Map<String, Integer> bag = new ConcurrentHashMap<>();

        corpus.parallel()
                .map(Purifier::purify)
                .flatMap(Arrays::stream)
                .forEach(w -> bag.put(
                        w, bag.getOrDefault(w, 0) + 1
                ));

        wordsByFrequency = bag.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }

    public String get(final int index) {
        return wordsByFrequency[index];
    }

    public int size() {
        return wordsByFrequency.length;
    }

    public String[] getWords() {
        return wordsByFrequency.clone();
    }
}