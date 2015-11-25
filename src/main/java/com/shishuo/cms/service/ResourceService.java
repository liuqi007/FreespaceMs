package com.shishuo.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shishuo.cms.dao.ResourceDao;
import com.shishuo.cms.entity.Resource;
import com.shishuo.cms.entity.vo.ResourceVo;
import com.shishuo.cms.exception.AuthException;

@Service
public class ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@CacheEvict(value = "resource", allEntries = true)
	public Resource addResource(Resource resource) {
		resourceDao.addResource(resource);
		return resource;
	}

	public void updateResourceByresId(Resource resource)
			throws AuthException {
		resourceDao.updateResourceByresId(resource);
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
