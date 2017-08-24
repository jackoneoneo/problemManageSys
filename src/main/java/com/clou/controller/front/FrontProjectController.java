package com.clou.controller.front;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clou.enumeration.WebPageType;
import com.clou.framework.JsonResult;
import com.clou.framework.MyException;
import com.clou.framework.auth.AuthPassport;
import com.clou.framework.base.BaseController;
import com.clou.inter.service.ICacheService;
import com.clou.inter.service.IDataCenterService;
import com.clou.inter.service.IInterfaceService;
import com.clou.inter.service.IMenuService;
import com.clou.inter.service.IWebPageService;
import com.clou.model.DataCenter;
import com.clou.model.Interface;
import com.clou.model.User;
import com.clou.model.WebPage;
import com.clou.utils.Config;
import com.clou.utils.Const;
import com.clou.utils.MyString;
import com.clou.utils.Page;
import com.clou.utils.Tools;

/**
 * 项目主页
 * @author Ehsan
 *
 */
@Scope("prototype")
@Controller
public class FrontProjectController extends BaseController<User> {
	@Autowired
	IMenuService menuService;
	@Autowired
	private IDataCenterService dataCenterService;
	@Autowired
	private IInterfaceService interfaceService;
	@Autowired
	private ICacheService cacheService;
	@Autowired
	private IWebPageService webPageService;
	
	/**
	 * 前端项目主页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/project.do")
	public String project() throws Exception {
		return "resources/html/project/index.html";
	}
	
	@RequestMapping("/front/project/list.do")
	@ResponseBody
	public JsonResult list(@ModelAttribute Interface interFace,
			@RequestParam(defaultValue = "1") Integer currentPage,String password,String visitCode) throws MyException{
		page.setCurrentPage(currentPage);
		List<DataCenter> modules = null;
		List<Interface> interfaces = null;
		if( !Tools.moduleIdIsLegal(interFace.getModuleId()) || interFace.getModuleId().equals("open")){
			// 查询所有推荐的
			map = Tools.getMap( "status", Byte.valueOf("3"), "type", "MODULE");
			modules = dataCenterService.findByMap(map, null, null);
			interfaces = new ArrayList<Interface>();
			
			map.clear();
			map.put("interfaces", interfaces);
			map.put("modules", modules);
			DataCenter dc = new DataCenter();
			dc.setName("开放接口");
			dc.setId("open");
			dc.setRemark("网友整理的常用免费接口");
			return new JsonResult(1, map, page);
			
		}else{
			DataCenter dc = dataCenterService.get(interFace.getModuleId());
			Tools.canVisitModule(dc.getPassword(), password, visitCode, request);
			map = Tools.getMap("parentId", interFace.getModuleId());
			modules = dataCenterService.findByMap(map, null, null);
			
			map.clear();
			map = Tools.getMap("moduleId", interFace.getModuleId());
			interfaces = interfaceService.findByMap( map, " new Interface(id,moduleId,interfaceName,version,createTime,updateBy,updateTime,remark)", page, null );
			
			map.clear();
			map.put("interfaces", interfaces);
			map.put("modules", modules);
			map.put("project", dc);
			return new JsonResult(1, map, page);
		}
	}
	
	
	@RequestMapping("/front/project/menu.do")
	@ResponseBody
	public JsonResult menu(@RequestParam String moduleId) throws MyException{
		if( !Tools.moduleIdIsLegal(moduleId) ){
			throw new MyException("000020");
		}
		returnMap.put("project", cacheService.getModule(moduleId));
		returnMap.put("modules", dataCenterService.findByMap(Tools.getMap("parentId", moduleId), null, null));
		//map = Tools.getMap("moduleId",moduleId, "type", WebPageType.DICTIONARY.name());
		return new JsonResult(1,returnMap);
	}
	
}
