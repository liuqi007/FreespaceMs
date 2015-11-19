package com.shishuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.entity.Role;

/**
 * 角色管理dao类
 * 
 * @author liuqi
 */

@Repository
public interface RoleDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////
	/**
	 * 添加角色
	 * 
	 * @param Role
	 * @return Integer
	 * 
	 */
	public int addRole(Role role);

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除角色
	 * 
	 * @param roleId
	 * @return Integer
	 * 
	 */
	public int deleteRole(@Param("roleId") long roleId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改角色的信息
	 * 
	 * @param roleId
	 * @param name
	 * @param password
	 */
	public void updateRoleByRoleId(Role role);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 获取所有角色列表
	 * 
	 * @param offset
	 * @param rows
	 * @return List<Role>
	 * 
	 */
	public List<Role> getAllList(@Param("offset") long offset,
			@Param("rows") long rows);

	/**
	 * 获取所有角色的数量
	 * 
	 * @return Integer
	 * 
	 */
	public int getAllListCount();

	/**
	 * 通过Id获得指定角色资料
	 * 
	 * @param roleId
	 * @return Role
	 */
	public Role getRoleById(@Param("roleId") long roleId);

	/**
	 * 通过名称获得角色资料
	 * 
	 * @param name
	 * @return User
	 */
	public List<Role> getRoleByName(String name);
}
