/***********************************************************
 * @Description : 在上面的测试中我们在每个测试方法里都mock了一个List对象，
 * 为了避免重复的mock，是测试类更具有可读性，我们可以使用下面的注解方式来快速模拟对象
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/10 0:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Mockito.C25使用注解来快速模拟;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MockitoExample1 {
    @Mock
    private List mockList;

    @Test
    public void shortHand(){
        mockList.add(1);
        verify(mockList).add(1);
    }
}
/**
 * java.lang.NullPointerException
 * 	at Mockito.C25使用注解来快速模拟.shortHand(C25使用注解来快速模拟.java:24)
 * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 * 	at java.lang.reflect.Method.invoke(Method.java:498)
 * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
 * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
 * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
 * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
 * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
 * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
 * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
 * 	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
 * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
 * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
 * 	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
 * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
 * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
 * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:160)
 * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
 * 	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
 * 	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
 * 	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
 */
