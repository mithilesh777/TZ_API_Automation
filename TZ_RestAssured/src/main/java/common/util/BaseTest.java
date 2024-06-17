package common.util;

import common.util.data.DataGenerator;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;

public class BaseTest {

    public BaseTest() {
    }

    @DataProvider(name = "common")
    public Object[][] getData(Method method) {
        List<LinkedHashMap<String, Object>> list = DataGenerator.dataGenerator(this.getClass().getSimpleName(), method.getName());
        System.out.println("list: "+ list.size());
        return (Object[][])list.stream().map((object) -> {
            return new Object[]{object};
        }).toArray((x$0) -> {
            return new Object[x$0][];
        });
    }
}
