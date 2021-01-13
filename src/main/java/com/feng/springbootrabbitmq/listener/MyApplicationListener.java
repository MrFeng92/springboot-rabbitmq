package com.feng.springbootrabbitmq.listener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent>
{
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event)
    {
        System.out.println("监听开始了。。。");
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();//解决userService一直为空
        System.out.println("app的类"+applicationContext.getClass());
    }
}
