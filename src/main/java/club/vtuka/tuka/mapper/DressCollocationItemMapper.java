package club.vtuka.tuka.mapper;

import club.vtuka.tuka.model.DressCollocationItem;

import java.util.List;

public interface DressCollocationItemMapper {
    int insert(DressCollocationItem record);

    int insertSelective(DressCollocationItem record);

    DressCollocationItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DressCollocationItem record);

    int updateByPrimaryKey(DressCollocationItem record);

    int insertBatch(List<DressCollocationItem> list);
}