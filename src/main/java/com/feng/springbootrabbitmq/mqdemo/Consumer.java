package com.feng.springbootrabbitmq.mqdemo;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer
{
    private static final String QUEUE_Name = "newRabbitMQ";

    public static void main(String[] args) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();//定义连接工厂
        factory.setHost(""); //RabbitMQ安装服务器的IP地址，如果是本机则为127.0.0.1
        factory.setPort(5672);//java程序连接时的默认端口，浏览器为15672
        Connection connection = factory.newConnection();//新建连接
        Channel channel = connection.createChannel();//新建通道
        channel.queueDeclare(QUEUE_Name, true, false, false, null); //声明队列，参数信息同上
        DefaultConsumer consumer = new DefaultConsumer(channel)
        { //定义消费方法{
            @Override
            public void handleDelivery(String consumerTag,//消费者的标签，在channel.basicConsume()去指定
                                       Envelope envelope,//消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志，如下所示(收到消息失败后是否需要重新发送)
                                       AMQP.BasicProperties properties,//基础配置信息
                                       byte[] body//队列发送的信息
            ) throws IOException
            {
                String exchange = envelope.getExchange(); //交换机
                String routingKey = envelope.getRoutingKey();//路由key
                long deliveryTag = envelope.getDeliveryTag();//消息id
                String msg = new String(body, "UTF-8");//消息内容
                System.out.println("receive message.." + msg);
            }
        };
        channel.basicConsume(QUEUE_Name,/*队列名称*/false,/*是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动回复，为了避免消息丢失，建议选择手动回复！*/consumer/*消费消息的方法，消费者接收到消息后调用此方法*/);
    }

    public static Connection getConnection() throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();  //定义连接工厂
        factory.setHost("");//RabbitMQ安装服务器的IP地址，如果是本机则为127.0.0.1
        factory.setPort(5672);//java程序连接时的默认端口，浏览器为15672
        factory.setVirtualHost("/");//rabbitmq默认虚拟机
        factory.setUsername("guest");//默认用户名
        factory.setPassword("guest");//默认密码
        Connection connection = factory.newConnection();// 通过工程获取连接
        return connection;//返回连接信息
    }
}
