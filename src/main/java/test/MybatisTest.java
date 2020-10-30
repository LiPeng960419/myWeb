package test;

import com.lipeng.dao.LogMessageMapper;
import com.lipeng.domain.LogMessage;
import com.lipeng.domain.LogMessageExample;
import com.lipeng.domain.LogMessageExample.Criteria;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: lipeng6@ybm100.com
 * @Date: 2020/10/30 10:13
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

    @Autowired
    private LogMessageMapper mapper;

    @Test
    public void insert() {
        Date date = new Date();
        int result = 0;
        // 使用全量insert的时候 如果数据库设置了不能为null 那么这里对象每个参数必须有值，不会使用默认值
        // insertSelective只有当你没有给列赋值 mysql才会去使用默认值
        LogMessage message = LogMessage.builder().type(1).content("消息1111").source("uc").status(1)
                .createUser("lipeng").createTime(date).updateUser("lipeng").updateTime(date)
                .version(1)
                .build();
        result = mapper.insert(message);
        System.out.println(result);

        message = LogMessage.builder().type(2).content("消息2222").source("sc").createUser("xiaowang")
                .createTime(date).updateUser("xiaowang").updateTime(date).build();
        result = mapper.insertSelective(message);
        System.out.println(result);

        LogMessage m1 = LogMessage.builder().type(3).content("消息3333").source("sms").status(1)
                .createUser("lipeng")
                .createTime(date).updateUser("lipeng").updateTime(date).build();
        LogMessage m2 = LogMessage.builder().type(4).content("消息4444").source("voice").status(1)
                .createUser("lipeng").createTime(date).updateUser("lipeng").updateTime(date)
                .build();
        // allowMultiQueries=true  需要这个参数才能执行多条sql，默认是一条
        // 批量插入无法给 插入对象赋值主键
        result = mapper.batchInsertSelective(Arrays.asList(m1, m2));
        System.out.println(result);
    }

    @Test
    public void test() {
        LogMessageExample example = new LogMessageExample();
        example.createCriteria().andIdIn(Arrays.asList(1L, 2L, 3L));
        long count = mapper.countByExample(example);
        System.out.println(count);

        List<LogMessage> logMessages = mapper.selectByExample(example);
        logMessages.forEach(System.out::println);

        LogMessage logMessage = mapper.selectByPrimaryKey(1L);
        System.out.println(logMessage);

        logMessage.setType(2);
        mapper.updateByPrimaryKey(logMessage);
        System.out.println(mapper.selectByPrimaryKey(logMessage.getId()));

        logMessage.setType(1);
        logMessage.setUpdateTime(new Date());
        mapper.updateByPrimaryKeySelective(logMessage);
        System.out.println(mapper.selectByPrimaryKey(logMessage.getId()));
    }

    @Test
    public void delete() {
        int result = mapper.deleteByPrimaryKey(3L);
        System.out.println(result);
        LogMessageExample example = new LogMessageExample();
        example.or().andTypeEqualTo(1).andStatusEqualTo(1);
        example.or().andStatusEqualTo(0).andSourceEqualTo("sc");
        result = mapper.deleteByExample(example);
        System.out.println(result);

        example.clear();

        example.createCriteria().andIdIn(Collections.singletonList(4L));
        result = mapper.deleteByExample(example);
        System.out.println(result);
    }

}