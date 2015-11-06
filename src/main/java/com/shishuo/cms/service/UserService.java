/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.constant.SystemConstant;
import com.shishuo.cms.dao.UserDao;
import com.shishuo.cms.entity.User;
import com.shishuo.cms.entity.vo.UserVo;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.AuthException;
import com.shishuo.cms.util.AuthUtils;
import com.shishuo.cms.util.PropertyUtils;

/**
 * 管理员
 * 
 * @author Administrator
 * 
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 添加管理员
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * @return User
	 */
	public User addUser(String name, String password)
			throws AuthException {
		Date now = new Date();
		User user = new User();
		user.setName(name);
		user.setPassword(AuthUtils.getPassword(password));
		user.setCreateTime(now);
		userDao.addUser(user);
		return user;
	}

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除管理员
	 * 
	 * @param userId
	 * @return Integer
	 */
	public int deleteUser(long userId) {
		return userDao.deleteUser(userId);
	}

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改管理员资料
	 * 
	 * @param userId
	 * @param name
	 * @param password
	 * @param status
	 * @return User
	 * @throws AuthException
	 */

	public void updateUserByAmdinId(long userId, String password)
			throws AuthException {
		String pwd = AuthUtils.getPassword(password);
		userDao.updateUserByuserId(userId, pwd);
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 管理员登陆
	 * 
	 * @param email
	 * @param password
	 * @param request
	 * @throws IOException
	 */
	public void userLogin(String name, String password,
			HttpServletRequest request) throws AuthException,
			IOException {
		UserVo user = userDao.getUserByName(name);
		if (user == null) {
			throw new AuthException("邮箱或密码错误");
		}
		String loginPassword = AuthUtils.getPassword(password);
		if (loginPassword.equals(user.getPassword())) {
			HttpSession session = request.getSession();
			user.setPassword("");
			if (name.equals(PropertyUtils
					.getValue("shishuocms.user"))) {
				user.setUser(true);
			} else {
				user.setUser(false);
			}
			session.setAttribute(SystemConstant.SESSION_ADMIN,
					user);
		} else {
			throw new AuthException("邮箱或密码错误");
		}
	}

	/**
	 * 通过Id获得指定管理员资料
	 */
	public User getUserById(long userId) {
		return userDao.getUserById(userId);
	}

	/**
	 * 获得所有管理员的分页数据
	 * 
	 * @param offset
	 * @param rows
	 * @return List<User>
	 */
	public List<User> getAllList(long offset, long rows) {
		return userDao.getAllList(offset, rows);
	}

	/**
	 * 获得所有管理员的数量
	 * 
	 * @return Integer
	 */
	public int getAllListCount() {
		return userDao.getAllListCount();
	}

	/**
	 * 获得所有管理员的分页
	 * 
	 * @param Integer
	 * @return PageVo<User>
	 */
	public PageVo<User> getAllListPage(int pageNum) {
		PageVo<User> pageVo = new PageVo<User>(pageNum);
		pageVo.setRows(20);
		List<User> list = this.getAllList(pageVo.getOffset(),
				pageVo.getRows());
		pageVo.setList(list);
		pageVo.setCount(this.getAllListCount());
		return pageVo;
	}

	/**
	 * 通过email获得管理员资料
	 * 
	 * @param email
	 * @return User
	 */
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	public long getSuperAdminId() {
		User user = getUserByName(PropertyUtils
				.getValue("shishuocms.admin"));
		return user.getUserId();
	}
}
