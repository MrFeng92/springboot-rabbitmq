package com.feng.springbootrabbitmq;

public class Test
{
    public static void main(String[] args)
    {
        while (true){
            System.out.println("第一层。。。。");
            while (true){
                System.out.println("第二层。。。。");
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
