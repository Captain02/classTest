package com.jzysoft.commonmoudle.lib.config;

import com.jzysoft.commonmoudle.lib.plugin.PagePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author zpf
 * @title: Pagingconfig
 * @projectName jzysoft
 * @description: 分页配置
 * @date 2019/5/8 0008上午 10:01
 */
@Configuration
public class Pagingconfig {

    @Bean
    public PagePlugin pagePlugin(){
        PagePlugin pagePlugin =new PagePlugin();
        Properties p=new Properties();
        p.setProperty("dialect","mysql");
        p.setProperty("pageSqlId",".*listPage.*");
        pagePlugin.setProperties(p);
        return pagePlugin;
    }

 /*   @Bean
    public PageData pd(){
        PageData pd=new PageData();
        return pd;
    }*/

    //提供SqlSeesion

}
