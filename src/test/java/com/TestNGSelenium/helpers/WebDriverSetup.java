package com.TestNGSelenium.helpers;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverSetup {

	 public static WebDriver createInstance(String browserName) {
	        WebDriver driver = null;
	    
	        String browser = "";
	        if( System.getProperty("browser") != null ) 
	        	browser =  System.getProperty("browser").toLowerCase();
	        
	        if (browser==null || browser=="")
	        {
	        	browser = browserName.toLowerCase();
	        }
	       
	        if (browser.contains("firefox")) {
	        	
	        	
	        	  	
	        		  	
	        	FirefoxProfile profile = new FirefoxProfile();
		        profile.setPreference("app.update.auto", false);
		        profile.setPreference("app.update.enabled", false);
	            driver = new FirefoxDriver(profile);
	            return driver;
	        }
	        if (browser.contains("internet")) {
	            driver = new InternetExplorerDriver();
	            return driver;
	        }
	        if (browser.contains("chrome")) {  
	            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
	            driver = new ChromeDriver();
	            return driver;
	        }
	        
	        if(browser.contains("headless"))
	        {
	            	DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
	            	String[] phantomArgs = new  String[] {
	            		    "--webdriver-loglevel=NONE"
	            		};
	            	capabilities.setCapability("phantomjs.cli.args", Collections.singletonList("--ignore-ssl-errors=yes"));
	            	capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
	            	driver = new PhantomJSDriver(capabilities);
	            	return driver;   
	        }
	       
	        return driver;  
	        
	    }
	 public static WebDriver createInstance(String browserName,Map<String,String> dataMap) throws IOException {
	        WebDriver driver = null;
	    
	        String browser = "";
	        if( System.getProperty("browser") != null ) 
	        	browser =  System.getProperty("browser").toLowerCase();
	        
	        if (browser==null || browser=="")
	        {
	        	browser = browserName.toLowerCase();
	        }
	       
	        if (browser.contains("firefox")) {
	        	FirefoxProfile profile = new FirefoxProfile();
		        profile.setPreference("app.update.auto", false);
		        profile.setPreference("app.update.enabled", false);
		        
	        	String modifyHeaderPath = System.getProperty("user.dir")+"//src//test//resources//modify_headers-0.7.1.1-fx.xpi";
             System.out.println("modifyHeaderPath"+modifyHeaderPath);
             
             profile.addExtension(new File(modifyHeaderPath));
             
             int i=0;
             for (String name: dataMap.keySet()){
             	String key =name.toString();
             	String value = dataMap.get(name).toString();
             	System.out.println(key + " " + value);
             	System.out.println(i);
             	profile.setPreference("modifyheaders.headers.count", i+1);
                 profile.setPreference("modifyheaders.headers.action"+i, "Add");
                 profile.setPreference("modifyheaders.headers.name"+i, key);
                 profile.setPreference("modifyheaders.headers.value"+i,value);
                 profile.setPreference("modifyheaders.headers.enabled"+i, true);
                 i=i+1;
     }
             
             profile.setPreference("modifyheaders.config.active", true);
             profile.setPreference("modifyheaders.config.alwaysOn", true);

  	
	        	
	            driver = new FirefoxDriver(profile);
	            return driver;
	        }
	        if (browser.contains("internet")) {
	            driver = new InternetExplorerDriver();
	            return driver;
	        }
	        if (browser.contains("chrome")) {
	            driver = new ChromeDriver();
	            return driver;
	        }
	        
	        if(browser.contains("headless"))
	        {
	            	DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
	            	String[] phantomArgs = new  String[] {
	            		    "--webdriver-loglevel=NONE"
	            		};
	            	capabilities.setCapability("phantomjs.cli.args", Collections.singletonList("--ignore-ssl-errors=yes"));
	            	capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
	            	driver = new PhantomJSDriver(capabilities);
	            	return driver;   
	        }
	       
	        return driver;  
	        
	    }
	
}
