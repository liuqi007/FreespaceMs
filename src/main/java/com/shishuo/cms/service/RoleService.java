package com.shishuo.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.dao.RoleDao;
import com.shishuo.cms.entity.Role;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.entity.vo.RoleVo;
import com.shishuo.cms.exception.AuthException;

/**
 * 角色管理service类
 * 
 * @author liuqi
 * 
 */
@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 添加管理员
	 * 
	 * @param name
	 * @return Role
	 */
	public Role addRole(String name)
			throws AuthException {
		Date now = new Date();
		Role role = new Role();
		role.setName(name);
		role.setCreateTime(now);
		roleDao.addRole(role);
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
	public int deleteRole(long roleId) {
		return roleDao.deleteRole(roleId);
	}

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改管理员资料
	 * 
	 * @param roleId
	 * @param name
	 * @param password
	 * @param status
	 * @return Role
	 * @throws AuthException
	 */

	public void updateRoleByRoleId(Role role)
			throws AuthException {
		roleDao.updateRoleByRoleId(role);
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 通过Id获得指定管理员资料
	 */
	public Role getRoleById(long roleId) {
		return roleDao.getRoleById(roleId);
	}

	/**
	 * 获得所有管理员的分页数据
	 * 
	 * @param offset
	 * @param rows
	 * @return List<Role>
	 */
	public List<Role> getRolesByPage(long offset, long rows) {
		return roleDao.getRolesByPage(offset, rows);
	}

	/**
	 * 获得所有管理员的数量
	 * 
	 * @return Integer
	 */
	public int getAllListCount() {
		return roleDao.getAllListCount();
	}

	/**
	 * 获得所有管理员的分页
	 * 
	 * @param Integer
	 * @return PageVo<Role>
	 */
	public PageVo<Role> getAllListPage(int pageNum) {
		PageVo<Role> pageVo = new PageVo<Role>(pageNum);
		pageVo.setRows(10);
		List<Role> list = this.getRolesByPage(pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		pageVo.setCount(this.getAllListCount());
		return pageVo;
	}
	
	/**
	 * 通过名称获得角色资料
	 * 
	 * @param name
	 * @return User
	 */
	public List<Role> getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}
	
	public List<RoleVo> getAllList() {
		return roleDao.getAllList();
	}
}
