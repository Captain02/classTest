package com.jzysoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * 
 * @author jzysoft
 */
@ComponentScan({"com.jzysoft"})
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class JzySoftApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(JzySoftApplication.class, args);
        System.out.println("系统启动成功");
    }
}