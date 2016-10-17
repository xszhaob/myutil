import bo.zhao.json.MyJsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/9/12
 * <p/>
 * package_name:PACKAGE_NAME
 * <p/>
 * project: myutil
 */
public class JsonTest {

    @Test
    public void findNodeTest() throws IOException {
        Apple apple = new Apple();
        apple.setName("红富士");
        apple.setColor("red");
        Person person = new Person();
        person.setName("bo.zhao");
        person.setAge(25);
        person.setBirthday(new Date());
        person.setAddress("binJ.hangZ");
        person.setApple(apple);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(person);
//        System.out.println(MyJsonUtil.findNode(jsonStr,"color"));
        System.out.println(MyJsonUtil.asMap(jsonStr));
    }

    private class Person {
        private String name;
        private int age;
        private Date birthday;
        private String address;
        private Apple apple;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Apple getApple() {
            return apple;
        }

        public void setApple(Apple apple) {
            this.apple = apple;
        }
    }

    private class Apple {
        private String name;
        private String color;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
