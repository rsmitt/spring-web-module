package ru.edu.springweb;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8089);
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}
