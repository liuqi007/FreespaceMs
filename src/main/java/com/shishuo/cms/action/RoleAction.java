package com.shishuo.cms.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shishuo.cms.entity.Role;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.service.RoleService;
import com.shishuo.cms.util.SSUtils;

/**
 * 角色管理控制类
 * @author liuqi
 *
 */
@Controller
@RequestMapping("/manage/role")
public class RoleAction extends BaseAction {

	@Autowired
	private RoleService roleService;

	/**
	 * 
	 * 进入角色管理页面
	 */
	@RequestMapping(value = "/manage.htm", method = RequestMethod.GET)
	public String manage(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			ModelMap modelMap) {
		modelMap.put("pageVo", roleService.getAllListPage(pageNum));
		return "manage/role/manage";
	}

	

	/**
	 * 进入角色列表页面
	 * 
	 */
	@RequestMapping(value = "/page.htm", method = RequestMethod.GET)
	public String allList(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			ModelMap modelMap) {
		modelMap.put("pageVo", roleService.getAllListPage(pageNum));
		return "manage/role/all";
	}

	/**
	 * 进入添加角色页面
	 * 
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addRole(ModelMap modelMap) {
		Role role = new Role();
		role.setRoleId(0);
		role.setName("");
		modelMap.put("updateRole", role);
		return "manage/role/add";
	}
	
	/**
	 * 进入角色修改页面
	 * 
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String update(
			@RequestParam(value = "roleId", defaultValue = "0") long roleId,
			ModelMap modelMap, HttpServletRequest request) {
		Role role = roleService.getRoleById(roleId);
		modelMap.put("updateRole", role);
		return "manage/role/add";
	}
	
	/**
	 * 添加Role
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/addNew.json", method = RequestMethod.POST)
	public JsonVo<String> addNewRole(@RequestParam(value = "name") String name) {
		JsonVo<String> json = new JsonVo<String>();
		List<Role> roleList = roleService.getRoleByName(name);
		if (roleList != null && !roleList.isEmpty()) {
			json.getErrors().put("name", "角色不能重复");
		}
		try {
			// 检测校验结果
			validate(json);
			roleService.addRole(SSUtils.toText(name.trim()));
			json.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	

	/**
	 * 修改指定的角色资料
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<String> updateRole(
			@RequestParam(value = "roleId") long roleId,
			@RequestParam(value = "name") String name,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (StringUtils.isBlank(name)) {
				json.getErrors().put("name", "名称不能为空");
			}
			Role role = roleService.getRoleById(roleId);
			String oldName = role.getName();
			if(!StringUtils.trim(name).equals(oldName)){//修改了名称
				List<Role> rl = roleService.getRoleByName(name);
				if(rl!=null && !rl.isEmpty()){//新密码重复
					json.getErrors().put("name", "该角色已经存在");
				}
			}
			// 检测校验结果
			validate(json);
			role.setName(name);
			roleService.updateRoleByRoleId(role);
		} catch (Exception e) {
			e.printStackTrace();
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * 删除角色
	 * 
	 */

	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public JsonVo<String> delete(@RequestParam(value = "roleId") long roleId,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			roleService.deleteRole(roleId);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
