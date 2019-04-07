package com.spring.springbootdemo.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author dong
 * @Created by 2019/4/7
 */
@Component
public class RedisMessage implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String body = new String(message.getBody());
        String topic = new String(bytes);
        System.out.println(body);
        System.out.println(topic);
    }
}
