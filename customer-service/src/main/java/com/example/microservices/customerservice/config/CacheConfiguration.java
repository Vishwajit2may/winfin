package com.example.microservices.customerservice.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

@Configuration
public class CacheConfiguration {

	
	  @Bean 
	  public CacheManagerCustomizer<ConcurrentMapCacheManager> customizer(){
		  CacheManagerCustomizer <ConcurrentMapCacheManager> customizer =  (cacheManager) -> { 
			  cacheManager.setAllowNullValues(false);
		  //cacheManager.setStoreByValue(true);
		  System.out.print("Customized invoked.."); 
		  }; 
		  return customizer; 
	  }
	 
	
	/*
	 * class ConcurrentCustomizer implements
	 * CacheManagerCustomizer<ConcurrentMapCacheManager>{
	 * 
	 * @Override public void customize(ConcurrentMapCacheManager cacheManager) { //
	 * TODO Auto-generated method stub cacheManager.setAllowNullValues(false);
	 * cacheManager.setStoreByValue(true); System.out.print("Customized invoked..");
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	
	/*
	 * @Bean public EhCacheCacheManager cacheManager() { return new
	 * EhCacheCacheManager(ecCaccheManager()); }
	 * 
	 * private CacheManager ecCaccheManager() { EhCacheManagerFactoryBean
	 * factoryBean = new EhCacheManagerFactoryBean();
	 * //factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
	 * factoryBean.setShared(true); return factoryBean.getObject(); }
	 */
}
