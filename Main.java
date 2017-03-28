import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        final Dictionary dictionary = new Dictionary(
                IntStream.range(0, args.length).parallel()
                        .mapToObj(i -> args[i])
                        .map(readPath)
                        .filter(Objects::nonNull)
                        .flatMap(BufferedReader::lines)
        );

        final String savePath = System.console().readLine("Give a file containing words you already know [File Path] ").trim();
        final Set<String> alreadyKnown;
        if (!savePath.isEmpty())
            alreadyKnown = Arrays.stream(new Dictionary(new BufferedReader(new FileReader(savePath))
                    .lines())
                    .getWords())
                    .collect(Collectors.toSet());
        else alreadyKnown = Collections.emptySet();

        IntStream.range(0, dictionary.size())
                .map(i -> dictionary.size() - 1 - i)
                .mapToObj(dictionary::get)
                .filter(w -> !alreadyKnown.contains(w))
                .skip(Integer.parseInt(System.console().readLine("How inconspicuous do you want your words to be? [Positive Integer] ")))
                .limit(Integer.parseInt(System.console().readLine("How many words do you want? [Positive Integer] ")))
                .forEach(System.out::println);
    }

    public static final Function<String, BufferedReader> readPath = p -> {
        try {
            return new BufferedReader(new FileReader(new File(p)));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    };
}
