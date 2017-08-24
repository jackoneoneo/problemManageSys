package com.clou.utils;

import com.clou.framework.SpringContextHolder;
import com.clou.inter.dao.ICacheDao;
import com.clou.inter.service.ICacheService;
import com.clou.inter.service.ISearchService;
import com.clou.service.CacheService;
import com.clou.service.LuceneSearchService;
import com.clou.service.SolrSearchService;


public class GetBeanBySetting {
	 public static ISearchService getSearchService(){
	    	ICacheService cacheService = SpringContextHolder.getBean("cacheService", CacheService.class);
	        if(cacheService.getSetting(Const.SEARCH_TYPE).getValue().equals("solrSearch")){
	            return SpringContextHolder.getBean("solrSearch",SolrSearchService.class);
	        }else{
	            return SpringContextHolder.getBean("luceneSearch",LuceneSearchService.class);
	        }
	    }
	 public static ICacheDao getCacheDao(){
	        if( Config.getRedisIp().trim().equals("") ){
	            return SpringContextHolder.getBean("memoryCacheDao",ICacheDao.class);
	        }else{
	            return SpringContextHolder.getBean("redisCacheDao",ICacheDao.class);
	        }
	    }
}
