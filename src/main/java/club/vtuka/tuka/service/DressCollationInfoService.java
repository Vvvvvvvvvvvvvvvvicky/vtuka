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

    public List<DressCollocationInfo> getDressCollationInfoListByPage(int pageNo,int pageSize){
        List<DressCollocationInfo> infoList = infoMapper.selectBatchByPage(pageNo, pageSize);
        return infoList;
    }

    public List<DressCollocationInfo> getDressCollationInfoList(){
        List<DressCollocationInfo> infoList = infoMapper.selectBatch();
        return infoList;
    }

}
