package club.vtuka.tuka.mapper;

import club.vtuka.tuka.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Vicky on 2018/1/22.
 */
@Mapper
public interface UserMapper {
    public List<User> findUserInfoList();
}