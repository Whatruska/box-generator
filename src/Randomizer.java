import entities.BoxOptions;
import entities.ExtraBoxOptions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {
    private static final Random random = new Random();

    public static ExtraBoxOptions generateExtraBoxOptions () {
        ExtraBoxOptions options = new ExtraBoxOptions();
        Field[] fields = ExtraBoxOptions.class.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                field.set(options, random.nextBoolean());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return options;
    }

    public static BoxOptions generateBoxOptions () {
        BoxOptions options = new BoxOptions();
        Field[] fields = BoxOptions.class.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            Class type = field.getType();
            try {
                if (type == String.class) {
                    field.set(options, generateRandomString());
                } else if (type == ExtraBoxOptions.class) {
                    field.set(options, generateExtraBoxOptions());
                } else if (type == int.class) {
                    boolean isMaxSumField = field.getName().contains("max");
                    field.set(options, generateRandomInt(isMaxSumField ? 2000 : 12));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return options;
    }

    public static List<BoxOptions> generateBoxOptionsList() {
        List<BoxOptions> optionsList = new ArrayList<>();

        int number = generateRandomInt(10) + 1;

        for (int i = 0; i < number; i++) {
            optionsList.add(generateBoxOptions());
        }

        return optionsList;
    }

    public static int generateRandomInt(int bound) {
        return Math.abs(random.nextInt() % bound);
    }

    public static String generateRandomString () {
        int length = random.nextInt() % 10 + 5;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            boolean isUpper = random.nextBoolean();
            char startChar = isUpper ? 'A' : 'a';
            char ch = (char) (startChar + Math.abs(random.nextInt() % 26));
            builder.append(ch);
        }

        return builder.toString();
    }
}
