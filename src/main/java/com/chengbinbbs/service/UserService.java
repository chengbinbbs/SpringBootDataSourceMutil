package com.chengbinbbs.service;

import com.chengbinbbs.mapper.UserMapper;
import com.chengbinbbs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现
 *
 * @author zhangcb
 * @created 2017-07-10 10:35.
 */
@Service("userInfoService")
public class UserService {
    int i=1;
    @Autowired
    private UserMapper userMapper;

    public User findByUserName(String name) {
        return userMapper.findByUserName(name);
    }

    /**
     * @Retryable：标注此注解的方法在发生异常时会进行重试
        参数说明：value：抛出指定异常才会重试
        include：和value一样，默认为空，当exclude也为空时，默认所以异常
        exclude：指定不处理的异常
        maxAttempts:最大重试次数，默认3次
        backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，multiplier（指定延迟倍数）
        默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为2，则第一次重试为1秒，第二次为
        2秒，第三次为4秒
     * @param user
     * @return
     */
    @Retryable(value = {RuntimeException.class},maxAttempts = 4,backoff = @Backoff(delay = 1000l,multiplier = 1))
    public int insert(User user) {
        userMapper.insert(user);
        throw new RuntimeException("插入异常!");
    }

    //@Recover：用于@Retryable重试失败后处理方法，此方法里的异常一定要是@Retryable方法里抛出的异常，否则不会调用这个方法
    @Recover
    public Integer recover(RuntimeException e){
        return 6;
    }

    @Transactional
    public int save(User user) {
        userMapper.insert(user);
        //模拟事务异常回滚
        throw new RuntimeException("插入异常!");
    }
}
