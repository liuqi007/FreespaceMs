package com.shishuo.cms.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shishuo.cms.constant.SystemConstant;
import com.shishuo.cms.entity.Resource;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.ResourceVo;
import com.shishuo.cms.service.ResourceService;

/**
 * 资源管理控制类
 * 
 * @author liuqi
 *
 */
@Controller
@RequestMapping("/manage/resource")
public class ResourceAction extends BaseAction {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 
	 * 进入资源管理页面
	 */
	@RequestMapping(value = "/manage.htm", method = RequestMethod.GET)
	public String manage(ModelMap modelMap) {
		try {
			List<ResourceVo> allResource = resourceService.getAllList();
			List<ResourceVo> tree = new ArrayList<ResourceVo>();
			for (ResourceVo resourceVo : allResource) {
				ResourceVo node = new ResourceVo();
				node.setId(resourceVo.getResId());
				node.setpId(resourceVo.getParentResId());
				node.setName(resourceVo.getName());
				node.setLink(resourceVo.getUrl());
				node.setCreateTime(resourceVo.getCreateTime());
				node.setIconcss(resourceVo.getIconcss());
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
			return "manage/resource/manage";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 添加或修改资源
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate.json", method = RequestMethod.POST)
	public JsonVo<String> addOrUpdate(Resource resource) {
		JsonVo<String> json = new JsonVo<String>();

		try {
			long resId = resource.getResId();
			if (resId == 0) {// 添加
				resource.setCreateTime(new Date());
				resource.setUpdateTime(new Date());
				resource.setIconcss(SystemConstant.DEFAULT_STYLE);
				resource.setType(SystemConstant.RESOURCE_TYPE_MENU);
				validate(json);
				resourceService.addResource(resource);
			} else if (resId == 1) {// 根节点
				throw new Exception("根节点不能修改");
			}
			else {// 修改
				resource.setUpdateTime(new Date());
				validate(json);
				resourceService.updateResourceByresId(resource);
			}
			json.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * 删除资源
	 * 
	 */

	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public JsonVo<String> delete(@RequestParam(value = "resId") long resId,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			resourceService.deleteResource(resId);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
