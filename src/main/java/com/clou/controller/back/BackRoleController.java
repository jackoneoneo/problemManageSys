package com.clou.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clou.framework.JsonResult;
import com.clou.framework.auth.AuthPassport;
import com.clou.framework.base.BaseController;
import com.clou.inter.service.IRoleService;
import com.clou.model.Role;
import com.clou.utils.Const;
import com.clou.utils.MyString;
import com.clou.utils.Tools;

@Scope("prototype")
@Controller
@RequestMapping("/role")
public class BackRoleController extends BaseController<Role>{

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(@ModelAttribute Role role,@RequestParam(defaultValue="1") Integer currentPage){
		page.setCurrentPage(currentPage);
		map = Tools.getMap("roleName|like",role.getRoleName());
		return new JsonResult(1,roleService.findByMap(map,page,null),page);
	}
	@RequestMapping("/detail.do")
	@ResponseBody
	public JsonResult detail(@ModelAttribute Role role){
		if(!role.getId().equals(Const.NULL_ID)){
			model= roleService.get(role.getId());
		}else{
			model=new Role();
		}
		return new JsonResult(1,model);
	}
	
	@RequestMapping("/addOrUpdate.do")
	@ResponseBody
	@AuthPassport(authority=Const.AUTH_ROLE)
	public JsonResult addOrUpdate(@ModelAttribute Role role){
		if(!MyString.isEmpty(role.getId())){
			roleService.update(role);
		}else{
			role.setId(null);
			roleService.save(role);
		}
		return new JsonResult(1,role);
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	@AuthPassport(authority=Const.AUTH_ROLE)
	public JsonResult delete(@ModelAttribute Role role){
		roleService.delete(role);
		return new JsonResult(1,null);
	}
}
