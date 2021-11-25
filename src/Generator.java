import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import entities.BoxOptions;
import entities.ExtraBoxOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Generator {
    private static final String OUTPUT_DIRECTORY = "output/";
    private static final XStream X_STREAM = new XStream(new DomDriver());

    public static void generateXmlFile () {
        X_STREAM.alias("options", BoxOptions.class);
        X_STREAM.alias("extra", ExtraBoxOptions.class);

        File file = new File(OUTPUT_DIRECTORY + "data.xml");
        try {
            PrintWriter writer = new PrintWriter(file);
            int number = Randomizer.generateRandomInt(5) + 1;
            BoxOptions[] arr = new BoxOptions[number];
            for (int i = 0; i < number; i++) {
                BoxOptions options = Randomizer.generateBoxOptions();
                arr[i] = options;
            }
            writer.println(X_STREAM.toXML(arr));
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
