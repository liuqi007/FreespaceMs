package com.shishuo.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.dao.RoleResourceRelDao;
import com.shishuo.cms.entity.RoleResourceRel;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.AuthException;

/**
 * 角色管理service类
 * 
 * @author liuqi
 * 
 */
@Service
public class RoleResourceRelService {

	@Autowired
	private RoleResourceRelDao roleResourceRelDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 添加管理员
	 * 
	 * @param name
	 * @return RoleResourceRel
	 */
	public RoleResourceRel addRoleResourceRel(long roleId, long resId)
			throws AuthException {
		RoleResourceRel role = new RoleResourceRel();
		role.setRoleId(roleId);
		role.setResId(resId);
		role.setCreateTime(new Date());
		roleResourceRelDao.addRoleResourceRel(role);
		return role;
	}

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除管理员
	 * 
	 * @param roleId
	 * @return Integer
	 */
	public int deleteRoleResourceRel(long roleId) {
		return roleResourceRelDao.deleteRoleResourceRel(roleId);
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 通过Id获得指定管理员资料
	 */
	public List<RoleResourceRel> getRoleResourceRelById(long roleId) {
		return roleResourceRelDao.getRoleResourceRelById(roleId);
	}

	/**
	 * 获得所有管理员的分页数据
	 * 
	 * @param offset
	 * @param rows
	 * @return List<RoleResourceRel>
	 */
	public List<RoleResourceRel> getAllList(long offset, long rows) {
		return roleResourceRelDao.getAllList(offset, rows);
	}

	/**
	 * 获得所有管理员的数量
	 * 
	 * @return Integer
	 */
	public int getAllListCount() {
		return roleResourceRelDao.getAllListCount();
	}

	/**
	 * 获得所有管理员的分页
	 * 
	 * @param Integer
	 * @return PageVo<RoleResourceRel>
	 */
	public PageVo<RoleResourceRel> getAllListPage(int pageNum) {
		PageVo<RoleResourceRel> pageVo = new PageVo<RoleResourceRel>(pageNum);
		pageVo.setRows(10);
		List<RoleResourceRel> list = this.getAllList(pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		pageVo.setCount(this.getAllListCount());
		return pageVo;
	}
}
