package club.vtuka.tuka.controller;

import club.vtuka.tuka.model.DressCollocationInfo;
import club.vtuka.tuka.model.DressCollocationItem;
import club.vtuka.tuka.service.DressCollationInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Vicky on 2018/8/9.
 */
@RestController
public class DressCollocationController {
    private static final Logger logger = LoggerFactory.getLogger(DressCollocationController.class);


    @Autowired
    private DressCollationInfoService infoService;
    @Autowired
    private DressCollocationItem itemService;

    /**
     * 手动分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/api/collocations0")
    public List<DressCollocationInfo> list0(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize){
        if(pageNo == null || pageNo < 0 || pageSize < 0){
            logger.error("传入参数不正确");
            return null;
        }
        List<DressCollocationInfo> infoList = infoService.getListByPage(pageNo, pageSize);
        infoList.size();
        return infoList;
    }

    /**
     * 利用 PageHelper 分页插件进行分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/api/collocations")
    public PageInfo list(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, String orderByItem, String orderByPattern){
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?10:pageSize;
        orderByItem =  orderByItem == null ? "ID" : orderByItem;
        orderByPattern = orderByPattern == null ? "DESC" : orderByPattern;
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy(new StringBuilder().append(orderByItem).append(" ").append(orderByPattern).toString());
        List<DressCollocationInfo> infoList = infoService.getList();
        PageInfo<DressCollocationInfo> pageInfo = new PageInfo<>(infoList);
        return pageInfo;
    }

    /**
     * 新增搭配
     * @param dressInfo
     * @param dressItemList
     * TODO
     */
    public void add(@RequestBody DressCollocationInfo dressInfo, @RequestBody List<DressCollocationItem> dressItemList){
        if(null != dressInfo){
            infoService.add(dressInfo);
        }
        if(null != dressItemList){
//            itemService.itemService
        }
    }

    /**
     * 更新搭配信息
     * @param dressInfo
     * @param dressItemList
     * TODO
     */
    public void update(@RequestBody DressCollocationInfo dressInfo, @RequestBody List<DressCollocationItem> dressItemList){
        if(null != dressInfo){
            infoService.update(dressInfo);
        }
    }

/*
    @RequestMapping(value="/changeShowStatus",method = RequestMethod.GET)
    @ResponseBody
    public Result changeShowStatusById(@Param("id")Long id, @Param("isShow") Boolean isShow){
        if(id == null || isShow == null){
            return new Result(Result.wrongParam,null);
        }
        DressCollocationInfo info = new DressCollocationInfo();
        info.setIsShow(isShow);
        info.setId(id);
        int updateResult = infoService.updateDressCollocationInfo(info);
        return new Result(updateResult,null);
    }

    @RequestMapping(value="/deleteInfoById",method=RequestMethod.GET)
    @ResponseBody
    public Result deleteInfoById(@Param("id")Long id){
        if(id == null){
            return new Result(Result.wrongParam,null);
        }
        DressCollocationInfo info = new DressCollocationInfo();
        info.setIsDelete(true);
        info.setId(id);
        int result = infoService.updateDressCollocationInfo(info);
        return new Result(result,null);
    }
*/
}
