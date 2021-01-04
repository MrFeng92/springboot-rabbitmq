package com.feng.springbootrabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.feng.springbootrabbitmq.pojo.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookService
{
//    @RabbitListener(queues = "hello-java-queue")
//    public void receive(Book book)
//    {
//        System.out.println(book.toString());
//    }

    @RabbitListener(queues = "hello-java-queue")
    public void receive(Message msg) throws IOException
    {
        byte[] body = msg.getBody();

        ObjectMapper mapper=new ObjectMapper();
        Book book = mapper.readValue(body, Book.class);
        System.out.println(book.toString());
        System.out.println(msg);
        System.out.println(body.toString());
    }
}
