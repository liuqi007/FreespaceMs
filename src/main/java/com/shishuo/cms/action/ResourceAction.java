package com.shishuo.cms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		List<ResourceVo> allResource = resourceService.getAllList();
		List<ResourceVo> tree = new ArrayList<ResourceVo>();
		for (ResourceVo resourceVo : allResource) {
			resourceVo.setId(resourceVo.getResId());
			resourceVo.setpId(resourceVo.getParentResId());
		}
		tree.addAll(allResource);
		ResourceVo root = new ResourceVo();
		root.setpId(0);
		root.setId(1);
		root.setName("根目录");
		root.setUrl("/");
		root.setOpen(true);
		root.setNocheck(true);
		tree.add(root);
		modelMap.put("allResource",  JSONArray.fromObject(tree).toString());
		return "manage/resource/manage";
	}

	/**
	 * 进入资源列表页面
	 * 
	 */
	@RequestMapping(value = "/page.htm", method = RequestMethod.GET)
	public String allList(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			ModelMap modelMap) {
		// modelMap.put("pageVo", resourceService.getAllListPage(pageNum));
		return "manage/resource/all";
	}

	/**
	 * 进入添加资源页面
	 * 
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addResource(ModelMap modelMap) {
		Resource resource = new Resource();
		// resource.setResourceId(0);
		resource.setName("");
		modelMap.put("updateResource", resource);
		return "manage/resource/add";
	}

	/**
	 * 进入资源修改页面
	 * 
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String update(
			@RequestParam(value = "resourceId", defaultValue = "0") long resourceId,
			ModelMap modelMap, HttpServletRequest request) {
		// Resource resource = resourceService.getResourceById(resourceId);
		// modelMap.put("updateResource", resource);
		return "manage/resource/add";
	}

	/**
	 * 添加Resource
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/addNew.json", method = RequestMethod.POST)
	public JsonVo<String> addNewResource(
			@RequestParam(value = "name") String name) {
		JsonVo<String> json = new JsonVo<String>();
		// List<Resource> resourceList =
		// resourceService.getResourceByName(name);
		// if (resourceList != null && !resourceList.isEmpty()) {
		// json.getErrors().put("name", "资源不能重复");
		// }
		// try {
		// // 检测校验结果
		// validate(json);
		// resourceService.addResource(SSUtils.toText(name.trim()));
		// json.setResult(true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// json.setResult(false);
		// json.setMsg(e.getMessage());
		// }
		return json;
	}

	/**
	 * 修改指定的资源资料
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<String> updateResource(
			@RequestParam(value = "resourceId") long resourceId,
			@RequestParam(value = "name") String name,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (StringUtils.isBlank(name)) {
				json.getErrors().put("name", "名称不能为空");
			}
			// Resource resource = resourceService.getResourceById(resourceId);
			// String oldName = resource.getName();
			// if(!StringUtils.trim(name).equals(oldName)){//修改了名称
			// List<Resource> rl = resourceService.getResourceByName(name);
			// if(rl!=null && !rl.isEmpty()){//新密码重复
			// json.getErrors().put("name", "该资源已经存在");
			// }
			// }
			// 检测校验结果
			validate(json);
			// resource.setName(name);
			// resourceService.updateResourceByResourceId(resource);
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
	public JsonVo<String> delete(
			@RequestParam(value = "resourceId") long resourceId,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			resourceService.deleteResource(resourceId);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
