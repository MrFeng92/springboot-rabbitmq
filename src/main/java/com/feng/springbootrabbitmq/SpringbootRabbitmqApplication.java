package com.feng.springbootrabbitmq;

import com.feng.springbootrabbitmq.listener.MyApplicationListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableRabbit
@SpringBootApplication
public class SpringbootRabbitmqApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

}
