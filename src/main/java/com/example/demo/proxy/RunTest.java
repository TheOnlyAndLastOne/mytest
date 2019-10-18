package com.example.demo.proxy;

import com.example.demo.Application;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhi.zhao
 * @date 10/17/2019 4:10 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class RunTest {

    static{
        //输出cglib动态代理生成的字节码文件
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib-MethodInterceptor");

        //输出jdk动态代理生成的字节码文件，但是不起作用
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    @Test
    public void runTestWithJdkProxy(){

        boolean male = false;

        UserManager userManager = (UserManager)Proxy.newProxyInstance(UserManager.class
                .getClassLoader(), new Class[]{UserManager.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /*if(male){
                    method.invoke(new MaleUser(), args);
                }else{
                    method.invoke(new FemaleUser(), args);
                }*/
                method.invoke(new FemaleUser(), args);
                return null;
            }
        });

        userManager.addUser();
        System.out.println("userManager.getName:" +userManager.getClass().getName());


        //因为jdk配置生成字节码文件不起作用，所以采用以下方法
        byte [] bytes = ProxyGenerator
                .generateProxyClass( "$Proxy4",new Class <?> [] {UserManager.class});

        String pathDir="C:\\books";
        String path="\\$Proxy0.class";
        File f = new File(pathDir);
        if(!f.exists()) {
            f.mkdir();
        }
        path=f.getAbsolutePath()+path;
        f = new File(path);
        if(f.exists()) {
            f.delete();
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try( FileOutputStream fos = new FileOutputStream(path)){
            fos.write(bytes, 0, bytes.length);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runTestWithCglibProxy(){
        boolean male = true;

        /*UserManager userManager = (UserManager) Enhancer.create(UserManager.class, new Class[]{UserManager.class}, new net.sf.cglib.proxy.InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if(male){
                    method.invoke(new MaleUser(), objects);
                }else{
                    method.invoke(new FemaleUser(), objects);
                }
                return null;
            }
        });*/

        //methodInterceptor实现
        UserManager userManager = (UserManager) Enhancer.create(FemaleUser.class, new Class[]{UserManager.class}, new net.sf.cglib.proxy.MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                methodProxy.invokeSuper(o, objects);
                return null;
            }
        });

        /*UserManager userManager = (UserManager) Enhancer.create(FemaleUser.class, new net.sf.cglib.proxy.InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                method.invoke(new MaleSingleUser(), objects);
                return null;
            }
        });*/


        //多callback实现
        /*Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MaleSingleUser.class);

        Callback callback1 = new net.sf.cglib.proxy.InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("this is callback1");
                method.invoke(new MaleSingleUser(),objects);
                return null;
            }
        };

        Callback callback2 = new net.sf.cglib.proxy.MethodInterceptor() {

            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("this is callback2");
                return methodProxy.invokeSuper(o,objects);
//                return methodProxy.invoke(o,objects);
            }
        };

        enhancer.setCallbacks(new Callback[]{callback1,callback2});

        enhancer.setCallbackFilter(new CallbackFilter() {

            public int accept(Method method) {
                if (method.getName().equals("addUser")) {
                    return 0;
                } else if (method.getName().equals("deleteUser")) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        UserManager userManager = (UserManager) enhancer.create();
        */



        userManager.addUser();

        userManager.deleteUser();

    }

}
