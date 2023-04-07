import com.fasterxml.jackson.databind.ObjectMapper;
import iotSystemComponents.Application;
import iotSystemComponents.ApplicationCategory;
import iotSystemComponents.IoTdevice;
import iotSystemComponents.Topic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/*
 * This class is used to parse the NGSI-LD files and create the corresponding objects
 * the NGSI-LD files are in the folders medium-load and increasing-subscriptions
 * this parser still needs some work to be able to adapt to the different models
 *
 */
public class ngsild_parser {

    public static <T> T NGSI_parse(File file, Class<T> cl) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, cl);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ngsild_parser <folder>");
            System.exit(1);
        }
        String folder = args[0];
        File[] folders = new File(folder).listFiles();
        ArrayList<ApplicationCategory> appCategories = new ArrayList<>();
        ArrayList<Application> applications = new ArrayList<>();
        ArrayList<Topic> observations = new ArrayList<>();
        ArrayList<IoTdevice> devices = new ArrayList<>();

        for (File model : folders) {
            File[] files = model.listFiles();
            int fileCount = files.length;
            System.out.println(model + " : " + fileCount);

            for (File file : files) {
                String json = "";
                if (model.getName().contains("applicationCategories")) {
                    try {
                        appCategories.add(NGSI_parse(file, ApplicationCategory.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (model.getName().equals("applications")) {
                    try {
                        applications.add(NGSI_parse(file, Application.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (model.getName().contains("devices")) {
                    try {
                        devices.add(NGSI_parse(file, IoTdevice.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (model.getName().equals("observations")) {
                    try {
                        observations.add(NGSI_parse(file, Topic.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Unknown model: " + model.getName());
                }
            }
        }

    }
}
