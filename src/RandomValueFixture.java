import java.util.Collections;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class RandomValueFixture {

    private static final Random RANDOM = new Random();

    private static final char LETTER_A = 'A';

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static int digit() {
        return digit(1);
    }

    public static int digit(int howMany) {
        var digits = String.join("", Collections.nCopies(howMany, "9"));
        return RANDOM.nextInt(Integer.parseInt(digits));
    }

    public static char letter() {
        return (char) (LETTER_A + RANDOM.nextInt(24));
    }

    public static String letter(int howMany) {
        return Stream.generate(() -> (char) (LETTER_A + RANDOM.nextInt(24)))
                .limit(howMany)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }


    public static String workbench() {
        return format("%03d-%03d-%03d-R-%02d",
                digit(3),
                digit(3),
                digit(3),
                digit(2));
    }

    public static String ql() {
        return format(
                "000%d%c%c%d%c%c%d%c",
                digit(),
                letter(),
                letter(),
                digit(),
                letter(),
                letter(),
                digit(),
                letter()
        );
    }

}
