package me.sunny.demo.basics.language.compile;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import me.sunny.demo.basics.language.compile.scenario.Base;
import org.junit.Test;

public class JavassistTest {
  @Test
  public void test() throws NotFoundException, CannotCompileException,
      IllegalAccessException, InstantiationException, IOException {
    ClassPool cp = ClassPool.getDefault();
    CtClass cc = cp.get("me.sunny.demo.basics.language.compile.scenario.Base");
    CtMethod m = cc.getDeclaredMethod("process");
    m.insertBefore("{ System.out.println(\"start\"); }");
    m.insertAfter("{ System.out.println(\"end\"); }");
    Class c = cc.toClass();
    cc.writeFile("/Users/sunny/WorkPlace/demo/java-demo/demo-basics/src/test/java/me/sunny/demo/basics/compile");
    Base h = (Base)c.newInstance();
    h.process();
  }
}
