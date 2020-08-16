import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;
import service.AccountService;

public class test {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

    /**
     * 构造函数注入测试
     */
    @Test
    public void test() {
        AccountService service = (AccountService) ac.getBean("accountService");
        service.saveAccount();
    }
    /**
     * set方法注入测试
     */
    @Test
    public void test1() {
        AccountService service = (AccountService) ac.getBean("accountService2");
        service.saveAccount();
    }
    /**
     * 复杂类型注入测试
     */
    @Test
    public void test2() {
        AccountService service = (AccountService) ac.getBean("accountService3");
        service.saveAccount();
    }

}
