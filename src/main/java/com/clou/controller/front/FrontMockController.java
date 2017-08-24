package com.clou.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clou.framework.base.BaseController;
import com.clou.inter.service.ICacheService;
import com.clou.inter.service.IDataCenterService;
import com.clou.inter.service.IInterfaceService;
import com.clou.model.Interface;

@Scope("prototype")
@Controller
@RequestMapping("/mock")
public class FrontMockController extends BaseController<Interface>{

	@Autowired
	private IInterfaceService interfaceService;
	
	@RequestMapping("/trueExam.do")
	@ResponseBody
	public void trueExam(@RequestParam String id) throws Exception {
		printMsg(interfaceService.get(id).getTrueExam());
	}
	
	@RequestMapping("/falseExam.do")
	@ResponseBody
	public void falseExam(@RequestParam String id) throws Exception {
		printMsg(interfaceService.get(id).getFalseExam());
	}
}
