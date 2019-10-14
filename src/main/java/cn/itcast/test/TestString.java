package cn.itcast.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class TestString {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSet() {
        redisTemplate.boundValueOps("testKey").set("xxxxxxx");
    }

    @Test
    public void testGet() {
        String testKey = (String) redisTemplate.boundValueOps("testKey").get();
        System.out.println("======" + testKey);
    }

    @Test
    public void testDelete() {
        redisTemplate.delete("testKey");
    }
}
