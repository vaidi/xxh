package com.erlong.mybatis.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public class EhCacheUtil {

        private static CacheManager manage;

        static {
            //载入EhCache配置文件，创建CacheManager
            manage = CacheManager.create(EhCacheUtil.class.getResourceAsStream("ehcache.xml"));
            System.out.println("111");
        }

        /**
         * 将数据存入缓存
         *
         * @param cacheName
         * @param key
         * @param value
         */
        public static void put(String cacheName, Serializable key, Serializable value) {
            manage.getCache(cacheName).put(new Element(key, value));
        }

        /**
         * 从缓存中取出数据
         *
         * @param cacheName
         * @param key
         * @return
         */
        public static Serializable get(String cacheName, Serializable key) {
            try {
                Element e = manage.getCache(cacheName).get(key);
                if (e != null) {
                    return e.getValue();
                }
            } catch (IllegalStateException e1) {
                e1.printStackTrace();
            } catch (CacheException e1) {
                e1.printStackTrace();
            } catch (ClassCastException e1) {
                e1.printStackTrace();
            }
            return null;
        }

}
