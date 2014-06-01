import org.apache.commons.beanutils.PropertyUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 14-6-1.
 */
public class MyTest {
    private String name = "jackie";
    private int age;

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

    public static void main(String[] args) throws Exception {
        MyTest my = new MyTest();
        my.setName("peter");
        PropertyDescriptor pd = new PropertyDescriptor("name", MyTest.class);
        Method method = pd.getReadMethod();
        System.out.print(method.getDefaultValue());
        System.out.print(method.invoke(my));
        System.out.print(PropertyUtils.isReadable(my, "name1"));
        //System.out.print(PropertyUtils.getProperty(my, "name1").toString());
    }
}
