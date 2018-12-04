package com.wondertek.baiying.spring;

        import com.wondertek.baiying.test.MyTestBean;
        import org.junit.Test;
        import org.springframework.beans.factory.BeanFactory;
        import org.springframework.beans.factory.xml.XmlBeanFactory;
        import org.springframework.core.io.ClassPathResource;

/**
 * Created by wd on 2018/1/22.
 */
public class SpringBeanTest {

    @Test
    public void simpleLoadTest() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("myTestBean");
        System.out.println(myTestBean.getName());
    }
}
