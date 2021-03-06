package club.vtuka.tuka.controller;

import club.vtuka.tuka.model.DressCollocationInfo;
import club.vtuka.tuka.model.RespResult;
import club.vtuka.tuka.service.DressCollationInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Vicky on 2018/8/9.
 */
@RestController
public class DressCollocationController {
    private static final Logger logger = LoggerFactory.getLogger(DressCollocationController.class);


    @Autowired
    private DressCollationInfoService infoService;


    /**
     * 手动分页（废）
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
    public RespResult list(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, String orderByItem, String orderByPattern){
        pageNo=pageNo==null?1:pageNo;
        pageSize=pageSize==null?10:pageSize;
        orderByItem =  orderByItem == null ? "ID" : orderByItem;
        orderByPattern = orderByPattern == null ? "DESC" : orderByPattern;

        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy(new StringBuilder().append(orderByItem).append(" ").append(orderByPattern).toString());

        List<DressCollocationInfo> infoList = infoService.getList();
        PageInfo<DressCollocationInfo> pageInfo = new PageInfo<>(infoList);

        return RespResult.ok(RespResult.success,pageInfo);
    }

    /**
     * 新增搭配
     * @param dressInfo
     */
    @PostMapping("/api/collocations/info")
    public RespResult add(@RequestBody DressCollocationInfo dressInfo){
        if(null == dressInfo){
            return RespResult.error();
        }

        int result = infoService.add(dressInfo);

        if(RespResult.successInt == result){
            return RespResult.ok();
        }else{
            return RespResult.error();
        }
    }

    /**
     * 更新搭配信息
     * @param dressInfo
     */
    @PutMapping("/api/collocations/info")
    public RespResult update(@RequestBody DressCollocationInfo dressInfo){
        if(null == dressInfo){
            return RespResult.error();
        }
        int result = infoService.update(dressInfo);
        if(RespResult.successInt == result){
            return RespResult.ok();
        }else{
            return RespResult.error();
        }
    }

    /**
     * 删除（逻辑），参数为对象
     * @param dressInfo
     * @return
     */
   @DeleteMapping("/api/collocations/info0")
    public RespResult delete(@RequestBody DressCollocationInfo dressInfo){
        if(null == dressInfo){
            return RespResult.error();
        }
        DressCollocationInfo info = new DressCollocationInfo();
        info.setId(dressInfo.getId());
        info.setIsDelete(Boolean.FALSE);

        int result = infoService.update(info);
        if(RespResult.successInt == result){
            return RespResult.ok();
        }else{
            return RespResult.error();
        }
    }

    /**
     * 删除（逻辑），参数为id值
     * @param id
     * @return
     */
    @DeleteMapping("/api/collocations/info")
    public RespResult delete(@Param("id")Long id){
        if(id == null){
            return RespResult.error(RespResult.wrongParam);
        }

        DressCollocationInfo info = new DressCollocationInfo();
        info.setIsDelete(true);
        info.setId(id);

        int result = infoService.update(info);

        if(RespResult.successInt == result){
            return RespResult.ok();
        }else{
            return RespResult.error();
        }
    }

}
