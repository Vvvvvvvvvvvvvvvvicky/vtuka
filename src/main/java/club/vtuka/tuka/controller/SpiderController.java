package club.vtuka.tuka.controller;

import club.vtuka.tuka.constant.SysConstant;
import club.vtuka.tuka.service.SpiderService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vicky on 2018/5/6.
 */
@RequestMapping("/spider")
@Controller
public class SpiderController {
    private static final Logger logger = LoggerFactory.getLogger(SpiderController.class);

    @Autowired
    private SpiderService spiderService;

    @RequestMapping("/start")
    @ResponseBody
    public String startSpider(){
        logger.info("开始爬虫....");
        Date startDate = new Date();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //这里我们使用CountDownLatch同步工具类，它允许一个或多个线程一直等待，
        //直到其他线程的操作执行完后再执行。也就是说可以让主线程等待线程池内的线程执行结束再继续执行，
        // 里面维护了一个计数器，开始的时候构造计数器的初始数量，
        // 每个线程执行结束的时候调用countdown()方法，计数器就减1，调用await()方法，
        // 假如计数器不为0就会阻塞，假如计数器为0了就可以继续往下执行
        CountDownLatch countDownLatch = new CountDownLatch(1);
        executorService.submit(()->{
            spiderService.startSpider(SysConstant.BASE_URL,new HashMap<>());
            countDownLatch.countDown();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        Date endDate = new Date();

        FastDateFormat format = FastDateFormat.getInstance(SysConstant.DEFAULT_DATE_FORMAT);
        logger.info("爬虫结束....");
        logger.info("[开始时间:" + format.format(startDate) + ",结束时间:" + format.format(endDate) + ",耗时:"
                + (endDate.getTime() - startDate.getTime()) + "ms]");
        return "success！";
    }

}
