package com.clou.inter.service;

import com.clou.framework.base.IBaseService;
import com.clou.model.Log;

public interface ILogService extends IBaseService<Log>{

	void recover(Log log);

}
