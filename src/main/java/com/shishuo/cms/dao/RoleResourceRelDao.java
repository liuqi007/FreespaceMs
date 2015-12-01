package com.shishuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.entity.RoleResourceRel;

/**
 * 角色资源关联关系管理dao类
 * 
 * @author liuqi
 */

@Repository
public interface RoleResourceRelDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////
	/**
	 * 添加角色
	 * 
	 * @param RoleResourceRel
	 * @return Integer
	 * 
	 */
	public int addRoleResourceRel(RoleResourceRel role);

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
	public int deleteRoleResourceRel(@Param("roleId") long roleId);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 获取所有角色列表
	 * 
	 * @param offset
	 * @param rows
	 * @return List<RoleResourceRel>
	 * 
	 */
	public List<RoleResourceRel> getAllList(@Param("offset") long offset,
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
	 * @return RoleResourceRel
	 */
	public List<RoleResourceRel> getRoleResourceRelById(@Param("roleId") long roleId);
}
