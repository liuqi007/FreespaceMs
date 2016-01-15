package com.shishuo.cms.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.RoleVo;
import com.shishuo.cms.service.RoleService;
import com.shishuo.cms.service.UserRoleRelService;

/**
 * 用户角色关系管理控制类
 * 
 * @author liuqi
 *
 */
@Controller
@RequestMapping("/manage/userrolerel")
public class UserRoleRelAction extends BaseAction {

	@Autowired
	private UserRoleRelService userRoleRelService;

	@Autowired
	private RoleService roleService;

	/**
	 * 进入用户角色关联管理页面
	 */
	@RequestMapping(value = "/asign.htm", method = RequestMethod.GET)
	public String asign(ModelMap modelMap,
			@RequestParam(value = "userId", defaultValue = "0") long userId) {
		try {
			List<RoleVo> allList = roleService.getAllList();// 所有资源
			modelMap.put("allRole", allList);
			modelMap.put("userId", userId);
			return "manage/userrolerel/asign";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 修改指定的角色资料
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<String> updateUserRoleRel(
			@RequestParam(value = "roleId", defaultValue = "0") long roleId,
			@RequestParam(value = "selectResIds") String selectResIds) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if(!StringUtils.isBlank(selectResIds)){
				//删除所有关联
				userRoleRelService.deleteUserRoleRel(roleId);
				//保存所有关联
				String[] selectResIdArray = selectResIds.split("&");
				for (String resId : selectResIdArray) {
					long rId = Long.valueOf(resId);
					userRoleRelService.addUserRoleRel(roleId, rId);
				}
			}
			json.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
