package com.shishuo.cms.action;

import java.util.ArrayList;
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

import com.shishuo.cms.entity.RoleResourceRel;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.ResourceVo;
import com.shishuo.cms.service.ResourceService;
import com.shishuo.cms.service.RoleResourceRelService;

/**
 * 角色资源关系管理控制类
 * 
 * @author liuqi
 *
 */
@Controller
@RequestMapping("/manage/roleresourcerel")
public class RoleResourceRelAction extends BaseAction {

	@Autowired
	private RoleResourceRelService roleResourceRelService;

	@Autowired
	private ResourceService resourceService;

	/**
	 * 进入角色资源关联管理页面
	 */
	@RequestMapping(value = "/asign.htm", method = RequestMethod.GET)
	public String asign(ModelMap modelMap,
			@RequestParam(value = "roleId", defaultValue = "0") long roleId) {
		try {
			List<ResourceVo> tree = new ArrayList<ResourceVo>();// 树形
			List<ResourceVo> allList = resourceService.getAllList();// 所有资源
			List<RoleResourceRel> allRoleResourceRel = roleResourceRelService
					.getRoleResourceRelById(roleId);// 该角色拥有的资源关系

			for (ResourceVo resourceVo : allList) {
				ResourceVo node = new ResourceVo();
				node.setId(resourceVo.getResId());
				node.setpId(resourceVo.getParentResId());
				node.setName(resourceVo.getName());
				node.setLink(resourceVo.getUrl());
				node.setCreateTime(resourceVo.getCreateTime());
				node.setIconcss(resourceVo.getIconcss());
				node.setNocheck(false);
				for (RoleResourceRel roleResourceRel : allRoleResourceRel) {
					if (roleResourceRel.getResId() == resourceVo.getResId()) {
						node.setChecked(true);// 勾选上
					}
				}
				tree.add(node);
			}
			ResourceVo root = new ResourceVo();
			root.setpId(0);
			root.setId(1);
			root.setName("根目录");
			root.setOpen(true);
			root.setNocheck(true);
			tree.add(root);
			modelMap.put("allResource", JSONArray.fromObject(tree).toString());
			modelMap.put("roleId", roleId);
			return "manage/roleresourcerel/asign";
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
	public JsonVo<String> updateRoleResourceRel(
			@RequestParam(value = "roleId", defaultValue = "0") long roleId,
			@RequestParam(value = "selectResIds") String selectResIds) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if(!StringUtils.isBlank(selectResIds)){
				//删除所有关联
				roleResourceRelService.deleteRoleResourceRel(roleId);
				//保存所有关联
				String[] selectResIdArray = selectResIds.split("&");
				for (String resId : selectResIdArray) {
					long rId = Long.valueOf(resId);
					roleResourceRelService.addRoleResourceRel(roleId, rId);
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
