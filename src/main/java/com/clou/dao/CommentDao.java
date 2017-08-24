package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.ICommentDao;
import com.clou.model.Comment;

@Repository("commentDao")
public class CommentDao extends BaseDao<Comment> implements ICommentDao {

}