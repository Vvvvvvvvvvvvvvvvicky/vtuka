package club.vtuka.tuka.mapper;

import club.vtuka.tuka.model.DressCollocationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DressCollocationInfoMapper {
    int insert(DressCollocationInfo record);

    int insertSelective(DressCollocationInfo record);

    DressCollocationInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DressCollocationInfo record);

    int updateByPrimaryKey(DressCollocationInfo record);

    int insertBatch(List<DressCollocationInfo> list);

    List<DressCollocationInfo> selectBatchByPage(@Param("arg1") int startPage,@Param("arg2") int batchSize);

    List<DressCollocationInfo> selectBatch();

}