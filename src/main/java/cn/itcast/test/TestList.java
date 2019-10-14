package cn.itcast.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 演示list类型
 * redis相当于一个大的hashMap
 * key      value
 *          演示list类型其实就是演示value就是一个List集合
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class TestList {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testLpush() {
        redisTemplate.boundListOps("testList").leftPush("001");
        redisTemplate.boundListOps("testList").leftPush("002");
        redisTemplate.boundListOps("testList").leftPush("003");
        redisTemplate.boundListOps("testList").leftPush("004");
    }

    @Test
    public void testRpush() {
        redisTemplate.boundListOps("testList").rightPush("001");
        redisTemplate.boundListOps("testList").rightPush("002");
        redisTemplate.boundListOps("testList").rightPush("003");
        redisTemplate.boundListOps("testList").rightPush("004");
    }

    /*
    List是有序的，数据可重复
    存的顺序是1234，取的时候是4321
     */
    @Test
    public void testRange() {
        List<String> testList = redisTemplate.boundListOps("testList").range(0, 10);
        for (String s : testList) {
            System.out.println("======" + s);
        }
    }

    @Test
    public void testDelete() {
        redisTemplate.delete("testList");
    }
}
