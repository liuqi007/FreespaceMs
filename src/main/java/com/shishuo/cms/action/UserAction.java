/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.action;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.shishuo.cms.constant.SystemConstant;
import com.shishuo.cms.entity.User;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.service.UserService;
import com.shishuo.cms.util.AuthUtils;
import com.shishuo.cms.util.HttpUtils;
import com.shishuo.cms.util.SSUtils;

/**
 * @author Herbert
 * 
 */

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

	/**
	 * Kaptcha 验证码
	 */
	@Autowired
	private DefaultKaptcha captchaProducer;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap modelMap) {
		return "/manage/login";
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request, ModelMap modelMap) {
		request.getSession().removeAttribute(SystemConstant.SESSION_ADMIN);
		return "redirect:" + HttpUtils.getBasePath(request);
	}

	@ResponseBody
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public JsonVo<String> login(
			@RequestParam(value = "account") String account,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "captcha") String captcha,
			HttpServletRequest request, ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();

		try {
			String kaptcha = (String) request.getSession().getAttribute(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (StringUtils.isBlank(password)) {
				json.getErrors().put("password", "密码不能为空");
			} else if (password.length() < 6 && password.length() > 30) {
				json.getErrors().put("password", "密码最少6个字符，最多30个字符");
			}
			// 校验验证码
//			if (StringUtils.isNotBlank(kaptcha)
//					&& kaptcha.equalsIgnoreCase(captcha)) {
//
//			} else {
//				json.getErrors().put("captcha", "验证码错误");
//			}
			json.check();

			userService.userLogin(account, password, request);

		} catch (Exception e) {
			e.printStackTrace();
			// 异常，重置验证码
			request.getSession().removeAttribute(
					com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			json.setResult(false);
			json.getErrors().put("password", "帐号或密码错误");
			json.setMsg("change_captcha");
		}
		return json;
	}

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "captcha.htm", method = RequestMethod.GET)
	public void captcha(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = captchaProducer.createText();
		request.getSession().setAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}


	/**
	 * 
	 * 进入用户管理页面
	 */
	@RequestMapping(value = "/manage.htm", method = RequestMethod.GET)
	public String manage(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			ModelMap modelMap) {
		modelMap.put("pageVo", userService.getAllListPage(pageNum));
		return "manage/admin/manage";
	}

	

	/**
	 * 进入用户列表页面
	 * 
	 */
	@RequestMapping(value = "/page.htm", method = RequestMethod.GET)
	public String allList(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			ModelMap modelMap) {
		modelMap.put("pageVo", userService.getAllListPage(pageNum));
		return "manage/admin/all";
	}

	/**
	 * 进入添加用户页面
	 * 
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addUser(ModelMap modelMap) {
		User user = new User();
		user.setUserId(0);
		user.setName("");
		user.setAccount("");
		user.setPassword("");
		modelMap.put("updateUser", user);
		return "manage/admin/add";
	}
	
	/**
	 * 进入用户修改页面
	 * 
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String update(
			@RequestParam(value = "userId", defaultValue = "0") long userId,
			ModelMap modelMap, HttpServletRequest request) {
		User user = userService.getUserById(userId);
		modelMap.put("updateUser", user);
		return "manage/admin/add";
	}
	
	/**
	 * 添加User
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/addNew.json", method = RequestMethod.POST)
	public JsonVo<String> addNewUser(@RequestParam(value = "name") String name,
			@RequestParam(value = "account") String account,
			@RequestParam(value = "password") String password) {
		JsonVo<String> json = new JsonVo<String>();
		User user = userService.getUserByAccount(account);
		if (user == null) {
		} else {
			json.getErrors().put("account", "帐号不能重复");
		}
		try {
			if (StringUtils.isBlank(name)) {
				json.getErrors().put("name", "名称不能为空");
			}
			if (StringUtils.isBlank(password)) {
				json.getErrors().put("password", "密码不能为空");
			} else if (password.length() < 6) {
				json.getErrors().put("password", "密码不能小于6位");
			} else if (password.length() > 16) {
				json.getErrors().put("password", "密码不能大于16位");
			}
			// 检测校验结果
			validate(json);
			userService.addUser(SSUtils.toText(name.trim()),
					SSUtils.toText(account.trim()), password);
			json.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	

	/**
	 * 修改指定的用户资料
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<String> updateUser(
			@RequestParam(value = "userId") long userId,
			@RequestParam(value = "name") String name,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		User user = userService.getUserById(userId);
		try {
			if (StringUtils.isBlank(name)) {
				json.getErrors().put("name", "名称不能为空");
			}
			// 检测校验结果
			validate(json);
			user.setName(name);
			userService.updateUserByUserId(user);
		} catch (Exception e) {
			e.printStackTrace();
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	
	
	/**
	 * 进入单个admmin页面
	 * 
	 */
	@RequestMapping(value = "/updatePwd.htm", method = RequestMethod.GET)
	public String updatePwd(
			@RequestParam(value = "userId", defaultValue = "0") long userId,
			ModelMap modelMap, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute(SystemConstant.SESSION_ADMIN);
		modelMap.put("user", user);
		return "manage/admin/update";
	}
	
	/**
	 * 修改指定的用户密码
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePwd.json", method = RequestMethod.POST)
	public JsonVo<String> updatePwd(
			@RequestParam(value = "password") String password,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (StringUtils.isBlank(password)) {
				json.getErrors().put("password", "密码不能为空");
			}
			if (password.length() < 6) {
				json.getErrors().put("password", "密码不能小于6位数");
			}
			if (password.length() > 18) {
				json.getErrors().put("password", "密码不能大于18位数");
			}
			// 检测校验结果
			validate(json);
			 User user = (User) request.getSession().getAttribute(SystemConstant.SESSION_ADMIN);
			 userService.updatePwd(user.getUserId(),
					 AuthUtils.getPassword(password));
			json.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * 删除用户
	 * 
	 */

	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public JsonVo<String> delete(@RequestParam(value = "userId") long userId,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			userService.deleteUser(userId);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
