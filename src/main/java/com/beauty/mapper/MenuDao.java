package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单表
 */

@Mapper
@Repository
public interface MenuDao extends BaseMapper<MenuEntity> {

    /**
     * 查询角色的菜单
     *
     * @param roleId
     * @return
     */
    @Select("select * from menu where id in (select menu_id from role_menu where role_id = #{roleId})")
    List<MenuEntity> selectByRoleId(@Param("roleId") String roleId);
}