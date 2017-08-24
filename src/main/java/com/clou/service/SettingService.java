package com.clou.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clou.framework.base.BaseService;
import com.clou.framework.base.IBaseDao;
import com.clou.inter.service.ISettingService;
import com.clou.model.Setting;

@Service
public class SettingService extends BaseService<Setting>
		implements ISettingService {

	@Resource(name="settingDao")
	public void setDao(IBaseDao<Setting> dao) {
		super.setDao(dao, new Setting());
	}
}
