package club.vtuka.tuka.controller;

import club.vtuka.tuka.model.DressCollocationInfo;
import club.vtuka.tuka.service.DressCollationInfoService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Vicky on 2018/8/9.
 */
@RestController
@RequestMapping("/dress")
public class DressCollocationInfoController {
    private static final Logger logger = LoggerFactory.getLogger(DressCollocationInfoController.class);


    @Autowired
    private DressCollationInfoService infoService;

    @RequestMapping(value="/getInfoList")
    public List<DressCollocationInfo> getDressCollationInfoList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize){
        if(pageNo == null || pageNo < 0 || pageSize < 0){
            logger.error("传入参数不正确");
            return null;
        }
        List<DressCollocationInfo> infoList = infoService.getDressCollationInfoListByPage(pageNo, pageSize);
        return infoList;
    }
}
