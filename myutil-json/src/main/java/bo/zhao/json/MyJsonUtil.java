package bo.zhao.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.io.IOException;
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

    public static void main(String[] args) {

    }


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
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            JsonNode next = elements.next();
            System.out.println(next);
            if (next.isObject()) {
                JsonNode node = mapper.readTree(next.toString());
                resultMap.putAll(asMap(node));
            } else if (next.isArray()) {
                Iterator<JsonNode> arrElements = next.elements();
                while (arrElements.hasNext()) {
                    resultMap.putAll(asMap(arrElements.next()));
                }
            } else {
                resultMap.put(next.asText(),next.asText());
            }
        }
        return resultMap;
    }
}
