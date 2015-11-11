package com.shishuo.cms.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shishuo.cms.entity.vo.ResourceVo;
import com.shishuo.cms.service.ResourceService;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/resource")
public class ResourceAction extends BaseAction {

	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 获取菜单栏
	 * @param modelMap
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllResource.htm", method = RequestMethod.GET)
	public void getAllResource(ModelMap modelMap) {
		try {
			List<ResourceVo> allList = resourceService.getAllList();
			modelMap.put("allResource", allList);
		} catch (Exception e) {
			modelMap.addAttribute("g_folderId", 0);
			logger.fatal(e.getMessage());
		}
	}
}
