package bo.zhao.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.MemberKey;

import java.io.IOException;
import java.nio.channels.Pipe;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public static final ObjectMapper mapper = new ObjectMapper();


    public static String findNode(String jsonStr, String filedName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        if (jsonNode.has(filedName)) {
            JsonNode targetNode = jsonNode.get(filedName);
            return targetNode.toString();
        }
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            JsonNode tempNode = elements.next();
            if (tempNode.isObject()) {
                JsonNode node = objectMapper.readTree(tempNode.toString());
                if (node.has(filedName)) {
                    return node.get(filedName).toString();
                } else {
                    String result = findNode(node.toString(), filedName);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }


    public static Map<String, Object> asMap(String jsonStr) throws IOException {
        JsonNode jsonNode = mapper.readTree(jsonStr);
        return asMap(jsonNode);
    }

    public static Map<String,Object> asMap(JsonNode jsonNode) throws IOException {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> next = fields.next();
            String value = next.getValue().asText();
            if (next.getValue().isContainerNode()) {
                value = mapper.writeValueAsString(next.getValue());
            }
            resultMap.put(next.getKey(),value);
        }
        return resultMap;
    }
}
