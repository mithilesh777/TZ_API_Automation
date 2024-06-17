package common.util.data;

import common.util.Config;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataGenerator {

        public DataGenerator() {
        }

        public static List<LinkedHashMap<String, Object>> dataGenerator(String testDataName, String testName) {
            new ArrayList();
            List<LinkedHashMap<String, Object>> enabledList = new ArrayList();
            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode node = mapper.readTree(new File(System.getProperty("user.dir")+File.separator+Config.getConfigPropValues(Config.TEST_DATA_DIR)+File.separator+Config.getConfigPropValues(Config.ENV) +File.separator+testDataName+".json"));
                System.out.println(node.toString());
                node = node.path(testName);
                System.out.println(node.toString());
                List<LinkedHashMap<String, Object>> list = (List)mapper.readValue(node.toString(), new TypeReference<List<Object>>() {
                });

                for(int i = 0; i < list.size(); ++i) {
                    if ((Boolean)((LinkedHashMap)list.get(i)).get("isEnabled")) {
                        enabledList.add(list.get(i));
                    }
                }
            } catch (JsonProcessingException var7) {
                var7.printStackTrace();
            } catch (IOException var8) {
                var8.printStackTrace();
            }

            return enabledList;
        }

        public static <T> T getGenericData(Class<T> type, String filePath) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode node = mapper.readTree(new File(filePath));
                return type.cast(mapper.readValue(node.toString(), type));
            } catch (JsonProcessingException var5) {
                var5.printStackTrace();
            } catch (IOException var6) {
                var6.printStackTrace();
            }

            return null;
        }
}
