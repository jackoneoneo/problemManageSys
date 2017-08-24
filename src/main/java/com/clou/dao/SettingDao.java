package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.ISettingDao;
import com.clou.model.Setting;

@Repository("settingDao")
public class SettingDao extends BaseDao<Setting>
		implements ISettingDao {

}