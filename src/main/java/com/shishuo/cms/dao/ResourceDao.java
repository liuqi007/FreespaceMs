package com.shishuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.entity.Resource;
import com.shishuo.cms.entity.vo.ResourceVo;

@Repository
public interface ResourceDao {

	public int addResource(Resource resource);

	public int deleteResource(@Param("resId") long resId);

	public List<ResourceVo> getResourceListById(@Param("resId") long resId);

	public List<ResourceVo> getAllList();

	public void updateResourceByresId(Resource resource);
}
