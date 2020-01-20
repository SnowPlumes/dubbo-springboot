package me.lv.common.dao;

import me.lv.common.entity.BaseEntity;
import me.lv.common.entity.BaseEntityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T extends BaseEntity, E extends BaseEntityExample> {
	int countByExample(E example);

	int deleteByExample(E example);

	int deleteByPrimaryKey(Long id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(E example);

	T selectByPrimaryKey(Long pk);

	int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

	int updateByExample(@Param("record") T record, @Param("example") E example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}
