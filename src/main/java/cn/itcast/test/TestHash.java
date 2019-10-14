package cn.itcast.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

/**
 * 演示存取删除hash, hash类型是无序的
 * 整个redis就相当于一个大的hashmap
 * key      value
 *          value的格式分为string, hash, list, set, zset这五大类型
 *          如果value是hash类型, 那么value也相当于一个HashMap
 * testkey1         field        value
 *                  001         张三
 *                  002         李四
 * testkey2
 *                  001         青龙
 *                  002         白虎
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml"})
public class TestHash {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testPut() {
        redisTemplate.boundHashOps("testHash").put("001", "青龙");
        redisTemplate.boundHashOps("testHash").put("002", "白虎");
        redisTemplate.boundHashOps("testHash").put("003", "朱雀");
        redisTemplate.boundHashOps("testHash").put("004", "玄武");
    }

    @Test
    public void testGetOne() {
        String value = (String)redisTemplate.boundHashOps("testHash").get("003");
        System.out.println("======" + value);
    }

    @Test
    public void testGetAll() {
        Map<String, String> testHash = (Map<String, String>)redisTemplate.boundHashOps("testHash").entries();
        Set<Map.Entry<String, String>> entries = testHash.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("====key===" + entry.getKey());
            System.out.println("====value===" + entry.getValue());
        }
    }

    @Test
    public void testDeleteOne() {
        redisTemplate.boundHashOps("testHash").delete("003");
    }

    @Test
    public void testDeleteAll() {
        redisTemplate.delete("testHash");
    }
}
