package club.vtuka.tuka.service;

import club.vtuka.tuka.mapper.DressCollocationItemMapper;
import club.vtuka.tuka.model.DressCollocationItem;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicky on 2018/5/9.
 */
//@ContextConfiguration(locations = {"classpath*:application.properties"})
@ComponentScan
@MapperScan("club.vtuka.tuka.mapper")
public class DressCollocationItemServiceTest {
    @Autowired
    public DressCollocationItemMapper itemMapper;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(DressCollocationItemServiceTest.class);

    @Test
    public void insertBatch(){

        DressCollocationItem a = new DressCollocationItem(Long.valueOf("1"), "http://test.com", "test", "a", "http://a.com", BigDecimal.ONE, false);
        DressCollocationItem b = new DressCollocationItem(Long.valueOf("2"), "http://test.com", "test", "a", "http://a.com", BigDecimal.ONE, false);
        DressCollocationItem c = new DressCollocationItem(Long.valueOf("3"), "http://test.com", "test", "a", "http://a.com", BigDecimal.ONE, false);

        List<DressCollocationItem> itemList = new ArrayList<>();
        itemList.add(a);
        itemList.add(b);
        itemList.add(c);

        int result = itemMapper.insertBatch(itemList);
        logger.info(String.valueOf(result));
    }
}
