package ru.edu.springweb;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class App {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);

        StandardContext context = (StandardContext) tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        File file = new File("target/classes");
        WebResourceRoot webResourceRoot = new StandardRoot(context);

        webResourceRoot.addPreResources(new DirResourceSet(webResourceRoot, "/WEB-INF/classes", file.getAbsolutePath(), "/"));
        context.setResources(webResourceRoot);
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

}
