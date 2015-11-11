package com.shishuo.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shishuo.cms.dao.ResourceDao;
import com.shishuo.cms.entity.Resource;
import com.shishuo.cms.entity.vo.ResourceVo;

@Service
public class ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@CacheEvict(value = "resource", allEntries = true)
	public Resource addResource(long resId, long folderId) {
		Resource resource = new Resource();
		resource.setCreateTime(new Date());
		resourceDao.addResource(resource);
		return resource;
	}

	@CacheEvict(value = "resource", allEntries = true)
	public int deleteResource(long resId) {
		return resourceDao.deleteResource(resId);
	}

	@Cacheable("resource")
	public List<ResourceVo> getAllList() {
		return resourceDao.getAllList();
	}
	
	public List<ResourceVo> getResourceListById(long resId) {
		List<ResourceVo> list = resourceDao
				.getResourceListById(resId);
		return list;
	}
}
