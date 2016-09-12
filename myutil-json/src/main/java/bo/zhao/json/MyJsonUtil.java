package bo.zhao.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/9/11
 * <p/>
 * package_name:bo.zhao.json
 * <p/>
 * project: myutil
 * JSON转换工具，封装jackson，提供更好用的方法
 */
public class MyJsonUtil {


    public static String findNode(String jsonStr, String filedName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        if (jsonNode.has(filedName)) {
            JsonNode targetNode = jsonNode.get(filedName);
            return targetNode.toString();
        }
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            String result = findNode(jsonNode.toString(), filedName);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
