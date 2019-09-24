package me.sunny.demo.cookbook.drools;

import me.sunny.demo.cookbook.drools.points.PointDomain;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.testng.annotations.Test;

public class PointsTest {

    @Test
    public void junitTest1(){
        PointDomain pointDomain = new PointDomain();
        pointDomain.setUserName("Hello Kitty");
        pointDomain.setBackMondy(100d);
        pointDomain.setBuyMoney(500d);
        pointDomain.setBackNums(1);
        pointDomain.setBuyNums(5);
        pointDomain.setBillThisMonth(5);
        pointDomain.setBirthDay(true);
        pointDomain.setPoint(0L);
        ruleExe(pointDomain);
    }

    @Test
    public void junitTest2(){
        PointDomain pointDomain = new PointDomain();
        pointDomain.setUserName("Hello Kitty");
        pointDomain.setBackMondy(100d);
        pointDomain.setBuyMoney(500d);
        pointDomain.setBackNums(1);
        pointDomain.setBuyNums(5);
        pointDomain.setBillThisMonth(5);
        pointDomain.setBirthDay(false);
        pointDomain.setPoint(0L);
        ruleExe(pointDomain);
    }

    @Test
    public void junitTest3(){
        PointDomain pointDomain = new PointDomain();
        pointDomain.setUserName("Hello Kitty");
        pointDomain.setBackMondy(99d);
        pointDomain.setBuyMoney(500d);
        pointDomain.setBackNums(1);
        pointDomain.setBuyNums(5);
        pointDomain.setBillThisMonth(5);
        pointDomain.setBirthDay(false);
        pointDomain.setPoint(0L);
        ruleExe(pointDomain);
    }

    private void ruleExe(PointDomain pointDomain) {
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm:ss");
        KieServices kieServices = KieServices.Factory.get();
        //默认自动加载 META-INF/kmodule.xml
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //kmodule.xml 中定义的 ksession name
        KieSession kieSession = kieContainer.newKieSession("all-rules");
        kieSession.insert(pointDomain);

        int count = kieSession.fireAllRules();
        System.out.println(count);
        System.out.println("最终积分：" + pointDomain.getPoint());
        kieSession.dispose();
    }
}
