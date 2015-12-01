package com.shishuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.entity.UserRoleRel;

/**
 * 用户角色关联关系管理dao类
 * 
 * @author liuqi
 */

@Repository
public interface UserRoleRelDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////
	/**
	 * 添加角色
	 * 
	 * @param UserRoleRel
	 * @return Integer
	 * 
	 */
	public int addUserRoleRel(UserRoleRel role);

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
	public int deleteUserRoleRel(@Param("userId") long userId);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 获取所有角色列表
	 * 
	 * @param offset
	 * @param rows
	 * @return List<UserRoleRel>
	 * 
	 */
	public List<UserRoleRel> getAllList(@Param("offset") long offset,
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
	 * @return UserRoleRel
	 */
	public List<UserRoleRel> getUserRoleRelById(@Param("userId") long userId);
}
