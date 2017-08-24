package com.clou.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clou.framework.JsonResult;
import com.clou.framework.MyException;
import com.clou.framework.auth.AuthPassport;
import com.clou.framework.base.BaseController;
import com.clou.inter.service.ICacheService;
import com.clou.inter.service.ICommentService;
import com.clou.model.Comment;
import com.clou.utils.Const;
import com.clou.utils.Tools;

@Scope("prototype")
@Controller
@RequestMapping("/comment")
public class BackCommentController extends BaseController<Comment> {
	@Autowired
	private ICacheService cacheService;

	@Autowired
	private ICommentService commentService;

	@RequestMapping("/list.do")
	@ResponseBody
	@AuthPassport(authority = Const.AUTH_ADMIN)
	public JsonResult list(@ModelAttribute Comment comment, @RequestParam(defaultValue = "1") Integer currentPage) {
		page.setCurrentPage(currentPage);
		return new JsonResult(1, commentService.findByMap(map, page, null), page);
	}

	@RequestMapping("/detail.do")
	@ResponseBody
	@AuthPassport(authority = Const.AUTH_ADMIN)
	public JsonResult detail(@ModelAttribute Comment comment) {
		if (!comment.getId().equals(Const.NULL_ID)) {
			model = commentService.get(comment.getId());
		} else {
			model = new Comment();
		}
		return new JsonResult(1, model);
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	@AuthPassport(authority = Const.AUTH_COMMENT)
	public JsonResult delete(@ModelAttribute Comment comment) throws MyException {
		comment = commentService.get(comment.getId());
		commentService.delete(comment);
		return new JsonResult(1, null);
	}
}
