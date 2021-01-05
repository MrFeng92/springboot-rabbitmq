package com.feng.springbootrabbitmq.mqdemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Producer
{
    private static final String QUEUE_Name = "newRabbitMQ";//队列名称

    public static void main(String[] args) throws Exception
    {
        Connection connection = null;//新建连接，设置为null，备用
        Channel channel = null;//新建通道，设置为null，备用
        try
        {
            ConnectionFactory factory = new ConnectionFactory();//定义连接工厂
            factory.setHost("");//RabbitMQ安装服务器的IP地址，如果是本机则为127.0.0.1
            factory.setPort(5672);//java程序连接时的默认端口，浏览器为15672
            factory.setUsername("guest");//RabbitMQ默认用户名
            factory.setPassword("guest");//RabbitMQ默认密码
            factory.setVirtualHost("/");//rabbitmq默认虚拟机名称为“/”，虚拟机相当于一个独立的mq服务器
            //创建与RabbitMQ服务的TCP连接
            connection = factory.newConnection();
            //创建与Exchange的通道，每个连接可以创建多个通道，每个通道代表一个会话任务
            channel = connection.createChannel();
            /*声明队列，如果Rabbit中没有此队列将自动创建*/
            channel.queueDeclare(QUEUE_Name/*队列名称*/, true/*是否持久化*/, false/*队列是否独占此连接*/, false/*队列不再使用时是否自动删除此队列*/, null/*队列参数*/);
            String message = "hello，RabbitMQ" + System.currentTimeMillis();//编辑要发送的信息，并输出系统时间

            /**
             * 这里没有指定交换机，消息将发送给默认交换机，每个队列也会绑定那个默认的交换机，但不能显示绑定或解除绑定
             *默认的交换机，routingKey等于队列名称
             */
            channel.basicPublish/*消息发布方法 */(""/*Exchange的名称，如果没有指定，则使用DefaultExchange*/, "queue"/*routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列*/, null/*消息包含的属性*/, message.getBytes())/*消息体*/;
            System.out.println("Send Message is:'" + message + "'");//输出发送的信息
        } catch (Exception ex)
        {
            ex.printStackTrace();//输出异常信息
        } finally
        {
            if (channel != null)
            {
                channel.close();//关闭释放通道
            }
            if (connection != null)
            {
                connection.close();//关闭释放连接
            }
        }
    }
}
