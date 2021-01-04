package com.feng.springbootrabbitmq;

import com.feng.springbootrabbitmq.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootRabbitmqApplicationTests
{
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void test1()
    {
        //rabbitTemplate.send(e, "", "");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 20);
        rabbitTemplate.convertAndSend("myexchange", "myqueue", map);
    }

    @Test
    public void getMsg()
    {
        Object myqueue = rabbitTemplate.receiveAndConvert("myqueue");
        System.out.println("myqueue = " + myqueue);
    }

    @Test
    public void createExchange()
    {
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(directExchange);
        System.out.println("exchange create success");
    }

    @Test
    public void createQueue()
    {
        Queue queue = new Queue("hello-java-queue", true, false, false);
        amqpAdmin.declareQueue(queue);
        System.out.println("queue create success");
    }

    /**
     * 将交换机和队列绑定
     */
    @Test
    public void createBinding()
    {
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE, "hello-java-exchange", "hello.java", null);
        amqpAdmin.declareBinding(binding);
        System.out.println("binding create success");
    }

    @Test
    public void sendMsg()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "feng");
        map.put("age", 28);
        Book book = new Book("西游记999", "吴承恩");
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", book);
        System.out.println("message send success");
    }
}
