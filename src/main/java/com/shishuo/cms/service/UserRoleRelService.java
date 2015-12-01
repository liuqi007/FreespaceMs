package com.shishuo.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.dao.UserRoleRelDao;
import com.shishuo.cms.entity.UserRoleRel;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.AuthException;

/**
 * 用户角色管理service类
 * 
 * @author liuqi
 * 
 */
@Service
public class UserRoleRelService {

	@Autowired
	private UserRoleRelDao uerRoleRelDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 添加管理员
	 * 
	 * @param name
	 * @return UserRoleRel
	 */
	public UserRoleRel addUserRoleRel(long userId, long roleId)
			throws AuthException {
		UserRoleRel role = new UserRoleRel();
		role.setUserId(userId);
		role.setRoleId(roleId);
		role.setCreateTime(new Date());
		uerRoleRelDao.addUserRoleRel(role);
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
	public int deleteUserRoleRel(long roleId) {
		return uerRoleRelDao.deleteUserRoleRel(roleId);
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 通过Id获得指定管理员资料
	 */
	public List<UserRoleRel> getUserRoleRelById(long roleId) {
		return uerRoleRelDao.getUserRoleRelById(roleId);
	}

	/**
	 * 获得所有管理员的分页数据
	 * 
	 * @param offset
	 * @param rows
	 * @return List<UserRoleRel>
	 */
	public List<UserRoleRel> getAllList(long offset, long rows) {
		return uerRoleRelDao.getAllList(offset, rows);
	}

	/**
	 * 获得所有管理员的数量
	 * 
	 * @return Integer
	 */
	public int getAllListCount() {
		return uerRoleRelDao.getAllListCount();
	}

	/**
	 * 获得所有管理员的分页
	 * 
	 * @param Integer
	 * @return PageVo<UserRoleRel>
	 */
	public PageVo<UserRoleRel> getAllListPage(int pageNum) {
		PageVo<UserRoleRel> pageVo = new PageVo<UserRoleRel>(pageNum);
		pageVo.setRows(10);
		List<UserRoleRel> list = this.getAllList(pageVo.getOffset(), pageVo.getRows());
		pageVo.setList(list);
		pageVo.setCount(this.getAllListCount());
		return pageVo;
	}
}
