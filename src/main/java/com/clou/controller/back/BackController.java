package com.clou.controller.back;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clou.dto.LoginInfoDto;
import com.clou.framework.JsonResult;
import com.clou.framework.auth.AuthPassport;
import com.clou.framework.base.BaseController;
import com.clou.inter.service.ICacheService;
import com.clou.inter.service.IMenuService;
import com.clou.model.Setting;
import com.clou.model.User;
import com.clou.utils.Const;
import com.clou.utils.MyCookie;

@Scope("prototype")
@Controller
public class BackController extends BaseController<User> {
	@Autowired
	IMenuService menuService;
	@Autowired
	private ICacheService cacheService;
	
	/**
	 * 后台管理主页面
	 */
	@RequestMapping("/admin.do")
	@AuthPassport
	public String showHomePage() throws Exception {
		return "resources/html/backHtml/index.html";
	}
	
	/**
	 * 跳转到登陆界面
	 *@return
	 *@throws Exception
	 */
	@RequestMapping("/loginOrRegister.do")
	public String loginOrRegister() throws Exception {
		return "resources/html/backHtml/loginOrRegister.html";
	}
	
	
	/**
	 * 删除错误提示
	 */
	@RequestMapping("/back/closeErrorTips.do")
	@ResponseBody
	@AuthPassport(authority = Const.AUTH_ADMIN)
	public JsonResult closeErrorTips() throws Exception {
		cacheService.delStr(Const.CACHE_ERROR_TIP);
		return new JsonResult(1, null);
	}
	
	/**
	 * 后台页面初始化
	 */
	@RequestMapping("/back/init.do")
	@ResponseBody
	@AuthPassport
	public JsonResult init() throws Exception {
		Map<String, String> settingMap = new HashMap<String, String>();
		for (Setting setting : cacheService.getSetting()) {
			settingMap.put(setting.getKey(), setting.getValue());
		}
		String token = MyCookie.getCookie(Const.COOKIE_TOKEN, false, request);
		returnMap.put("settingMap", settingMap);
		returnMap.put("menuList", menuService.getLeftMenu(map));
		LoginInfoDto user = (LoginInfoDto) cacheService.getObj(Const.CACHE_USER + token);
		returnMap.put("sessionAdminName", user.getUserName());
		returnMap.put("sesssionAdminTrueName",user.getTrueName());
		returnMap.put("sessionAdminAuthor", user.getAuthStr());
		returnMap.put("sessionAdminRoleIds", user.getRoleId());
		returnMap.put("sessionAuth", user.getAuth());
		returnMap.put("sessionAdminId", user.getId());
		returnMap.put("errorTips", cacheService.getStr(Const.CACHE_ERROR_TIP));
		return new JsonResult(1, returnMap);
	}
}
