import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import iotSystemComponents.Application;
import iotSystemComponents.ApplicationCategory;
import iotSystemComponents.IoTdevice;
import iotSystemComponents.Topic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class NgsiParser {

    public static HashMap<String,ApplicationCategory> applicationCategories = new HashMap<>();
    public static HashMap<String,Application> applications = new HashMap<>();
    public static HashMap<String,Topic> observations = new HashMap<>();
    public static HashMap<String,IoTdevice> devices = new  HashMap<>();
    public static <T> T NGSI_parse(File file, Class<T> cl) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(file, cl);

    }

    public static void main(String[] args) {

        String folder = "medium-load";
        File[] folders = new File(folder).listFiles();

        for (File model : folders) {
            File[] files = model.listFiles();
            int fileCount = files.length;
            System.out.println(model + " : " + fileCount);

            for (File file : files) {
                String id = file.getName().substring(0, file.getName().lastIndexOf('.'));
                try {
                    switch (model.getName()) {
                        case "applicationCategories":
                            //key = filename without extension
                            applicationCategories.put( id, NGSI_parse(file, ApplicationCategory.class));
                            break;
                        case "applications":
                            applications.put( id, NGSI_parse(file, Application.class));
                            break;
                        case "devices":
                            devices.put( id, NGSI_parse(file, IoTdevice.class));
                            break;
                        case "observations":
                            observations.put( id, NGSI_parse(file, Topic.class));
                            break;
                        default:
                            System.out.println("Unknown model: " + model.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
