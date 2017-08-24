package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.IDataCenterDao;
import com.clou.model.DataCenter;

@Repository("dataCenterDao")
public class DataCenterDao extends BaseDao<DataCenter>
		implements IDataCenterDao {

}