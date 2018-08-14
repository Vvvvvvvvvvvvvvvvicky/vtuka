package club.vtuka.tuka.controller;

import club.vtuka.tuka.model.DressCollocationInfo;
import club.vtuka.tuka.service.DressCollationInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vicky on 2018/8/9.
 */
@Controller
@RequestMapping("/dress")
public class DressCollocationInfoController {
    private static final Logger logger = LoggerFactory.getLogger(DressCollocationInfoController.class);


    @Autowired
    private DressCollationInfoService infoService;

    @RequestMapping(value="/getInfoListByLimit")
    public List<DressCollocationInfo> getDressCollationInfoList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize){
        if(pageNo == null || pageNo < 0 || pageSize < 0){
            logger.error("传入参数不正确");
            return null;
        }
        List<DressCollocationInfo> infoList = infoService.getDressCollationInfoListByPage(pageNo, pageSize);
        return infoList;
    }

    @RequestMapping(value="/getInfoListByPage", method = RequestMethod.GET)
    public Map<String,Object> getInfoListByPage(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize){
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?10:pageSize;
        PageHelper.startPage(pageNo,pageSize);
        List<DressCollocationInfo> infoList = infoService.getDressCollationInfoList();
        PageInfo<DressCollocationInfo> pageInfo = new PageInfo<>(infoList);
        HashMap<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("pageInfo",pageInfo);
        return resultMap;
    }

    @RequestMapping("/index")
    public String freemarker2(Map<String, Object> map){
        map.put("name", "Joe");
        return "index";
    }
}
