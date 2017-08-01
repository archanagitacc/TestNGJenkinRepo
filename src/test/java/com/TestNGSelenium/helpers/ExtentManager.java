package com.TestNGSelenium.helpers;


	import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

	public class ExtentManager {
		private static ExtentReports instance;
		
		public static synchronized ExtentReports getInstance() {
			if (instance == null) {
				
				System.out.println(System.getProperty("user.dir"));
				
				instance  = new ExtentReports(System.getProperty("user.dir")+"/target/MyTestNGExtentReport.html", false,DisplayOrder.NEWEST_FIRST, NetworkMode.OFFLINE);
				
				instance.addSystemInfo("HostName", "Archana")
				.addSystemInfo("Environment", "QA")
				.addSystemInfo("UserName", "Archana Shinde");
			}
			
			return instance;
		}
	}

