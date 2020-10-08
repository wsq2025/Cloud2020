package com.future.springcloud.mapper;

import com.future.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wsq
 * @date 2020/10/7  14:15
 */
@Mapper
public interface UserMapper {
    /**
     * 保存
     */
    void save(User user);

    /**
     * 查询
     * @param id
     * @return
     */
    User get(Long id);
}
