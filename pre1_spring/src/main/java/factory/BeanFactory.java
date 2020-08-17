package factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 * <p>
 * Bean：在计算机英语中，有可重用组件的含义
 * JavaBean：用Java语言编写的可重用组件
 * JavaBean >>  实体类
 * <p>
 * 创建service和dao对象
 * <p>
 * 第一个：需要一个配置文件来配置service和dao
 * 配置的内容：唯一标志=全限定类名
 * xml或properties
 * 第二个：通过读取配置文件中的内容，反射创建对象
 */
public class BeanFactory {
    /**
     * 定义一个Properties对象
     */
    private static Properties props;
    
    /**
     * 定义一个Map，存放创建的对象，称之为容器
     */
    private static Map<String, Object> beans;

    // 使用静态代码块为Properties对象赋值
    static {
        try {
            // 实例化对象
            props = new Properties();
            //获取properties文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);

            // 实例化容器
            beans = new HashMap<>();
            // 取初配置文件中所有key
            Enumeration<Object> keys = props.keys();
            //遍历枚举
            while (keys.hasMoreElements()) {
                // 取初每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = props.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                // 把key和value存入map
                beans.put(key, value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 根据bean的名称获取对象 单例
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }


    /**
     * 根据Bean的名称获取Bean对象 多例
     */
    /*public static Object getBean(String beanName) {
        Object bean = null;
        String beanPath = props.getProperty(beanName);
        System.out.println(beanPath);
        try {
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }*/
}
