package club.vtuka.tuka.service;

import club.vtuka.tuka.mapper.DressCollocationInfoMapper;
import club.vtuka.tuka.model.DressCollocationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vicky on 2018/8/9.
 */
@Service
public class DressCollationInfoService {

    @Autowired
    private DressCollocationInfoMapper infoMapper;

    /**
     * 手动分页，获取分页搭配列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<DressCollocationInfo> getDressCollationInfoListByPage(int pageNo,int pageSize){
        List<DressCollocationInfo> infoList = infoMapper.selectBatchByPage(pageNo, pageSize);
        return infoList;
    }

    /**
     * 获取全部搭配列表
     * @return
     */
    public List<DressCollocationInfo> getDressCollationInfoList(){
        List<DressCollocationInfo> infoList = infoMapper.selectBatch();
        return infoList;
    }

    public int updateDressCollocationInfo(DressCollocationInfo info){
        int result = infoMapper.updateByPrimaryKeySelective(info);
        return result;
    }

}
