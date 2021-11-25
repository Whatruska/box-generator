import entities.BoxOptions;
import entities.ExtraBoxOptions;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

public class Generator {
    private static final String OUTPUT_FILE_PATH = "output/data.json";
    private static JsonGenerator jsonGenerator;

    public static void generateJSON () {
        try {
            jsonGenerator = Json.createGenerator(new FileWriter(OUTPUT_FILE_PATH));
            jsonGenerator.writeStartArray();
            List<BoxOptions> options = Randomizer.generateBoxOptionsList();
            options.forEach(Generator::generateJSONFromBoxOptions);
            jsonGenerator.writeEnd();
            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateJSONFromBoxOptions (BoxOptions optionsDto) {
        jsonGenerator.writeStartObject();
        Field[] fields = BoxOptions.class.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                if (field.getName().contains("extras")) {
                    generateJSONFromExtraOptions((ExtraBoxOptions) field.get(optionsDto));
                } else {
                    jsonGenerator.write(field.getName(), field.get(optionsDto).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jsonGenerator.writeEnd();
    }

    private static void generateJSONFromExtraOptions (ExtraBoxOptions optionsDto) {
        jsonGenerator.writeStartObject("extra");
        Field[] fields = ExtraBoxOptions.class.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                jsonGenerator.write(field.getName(), field.get(optionsDto).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        jsonGenerator.writeEnd();
    }
}
