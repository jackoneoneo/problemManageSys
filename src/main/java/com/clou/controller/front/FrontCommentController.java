package com.clou.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clou.framework.JsonResult;
import com.clou.framework.MyException;
import com.clou.framework.base.BaseController;
import com.clou.inter.service.ICacheService;
import com.clou.inter.service.ICommentService;
import com.clou.model.Comment;
import com.clou.utils.Const;
import com.clou.utils.MyCookie;
import com.clou.utils.Tools;

@Scope("prototype")
@Controller
@RequestMapping("/front/comment")
public class FrontCommentController extends BaseController<Comment> {
	@Autowired
	private ICacheService cacheService;

	@Autowired
	private ICommentService commentService;

	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult addOrUpdate(@ModelAttribute Comment comment) throws MyException {
		if (cacheService.getSetting(Const.SETTING_COMMENTCODE).getValue().equals("true")) {
			if (!comment.getId().equals(Tools.getImgCode(request))) {
				throw new MyException("000010");
			}
		}
		if(!"".equals(MyCookie.getCookie(Const.COOKIE_USERNAME, request).trim())){
			comment.setUserId(MyCookie.getCookie(Const.COOKIE_USERNAME, request));
		}else{
			comment.setUserId("匿名");
		}
		comment.setId(null);
		commentService.save(comment);
		cacheService.delObj( Const.CACHE_COMMENTLIST + comment.getWebpageId() );
		return new JsonResult(1, null);
	}
}
