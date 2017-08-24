package com.clou.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clou.framework.base.BaseService;
import com.clou.framework.base.IBaseDao;
import com.clou.inter.service.ICommentService;
import com.clou.model.Comment;

@Service
public class CommentService extends BaseService<Comment>
		implements ICommentService {

	@Resource(name="commentDao")
	public void setDao(IBaseDao<Comment> dao) {
		super.setDao(dao, new Comment());
	}
}
