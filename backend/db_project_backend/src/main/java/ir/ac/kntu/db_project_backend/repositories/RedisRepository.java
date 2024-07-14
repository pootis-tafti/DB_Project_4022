package ir.ac.kntu.db_project_backend.repositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {
    
    @Autowired
    private RedisTemplate<String,String> template;

    public void save(String key, String value, long timeout, TimeUnit unit) {
        template.opsForValue().set(key, value);
        template.expire(key, timeout, unit);
    }

    public String find(String key) {
        return template.opsForValue().get(key);
    }

    public void delete(String key) {
        template.delete(key);
    }
}
