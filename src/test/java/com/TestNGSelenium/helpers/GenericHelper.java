package com.TestNGSelenium.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.TestNGSelenium.Pojos.User;
import com.TestNGSelenium.helpers.WebDriverManager;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class GenericHelper extends WebDriverManager{

	public static WebDriver driver = WebDriverManager.getDriver(); 
	public static int SCREENSHOT_NUMBER = 1;


	public static String readProperty(String strg){

		File file = new File("src\\test\\resources\\dev.properties");
		String path = file.getAbsolutePath(); 
		System.out.println(path);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(prop.getProperty(strg));
		return prop.getProperty(strg);
	}

	public static Connection GetMysqlDBConnection(){

		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"		
		String dbUrl = readProperty("dbUrl");					

		//Database Username		
		String username = readProperty("dbUserName");	

		//Database Password		
		String password = readProperty("dbPassword");				


		//Load mysql jdbc driver		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}			

		//Create Connection to DB		
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl,username,password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return con;
	}

	public static User GetUserDetailsFromDb(){

		User objUser = new User();

		Connection con  = GetMysqlDBConnection();

		//Query to Execute		
		String query = "select *  from users;";	


		//Create Statement Object		
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					

		// Execute the SQL Query. Store results in ResultSet		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}							

		// While Loop to iterate through all data and print results		
		try {
			while (rs.next()){
				String UserName = rs.getString(2);								        
				String Password = rs.getString(3);					                               
				System. out.println(UserName+"  "+Password);		
				objUser.setPassword(Password);
				objUser.setUserName(UserName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		// closing DB Connection		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return objUser;
	}

	public static void launchUrl(WebDriver driver,String url){

		String currentPageUrl = "";
		currentPageUrl = readProperty(url);
		System.out.println(currentPageUrl);
		driver.get(currentPageUrl);
		//	driver.get("http://demo.guru99.com/V4/");  

	}
	
	public static String TakeScreenshot(String Testcase) {
		String img_path = "" ;
		try {

			//Convert web driver object to TakeScreenshot

			TakesScreenshot scrShot =((TakesScreenshot)getDriver());

			//Call getScreenshotAs method to create image file

			File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
			
			img_path = System.getProperty("user.dir")+"\\target\\screenshots\\" + Testcase + SCREENSHOT_NUMBER + ".jpg";
			
			System.out.println("screenshot image path : "+img_path);     

			//Move image file to new destination

			File DestFile=new File(img_path);

			//Copy file at destination

			FileUtils.copyFile(srcFile, DestFile);

					
			SCREENSHOT_NUMBER++;

		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
		return img_path;
	}

	
	public static void ScreenshotToPDf(String Testcase) {
		
        Document document = new Document();
       
        String path = System.getProperty("user.dir")+"\\target\\screenshots\\" ;
        
               int count = FilesCount(path);
               
        System.out.println("count" + count);
        
        if( count > 0 ) {
	        try {
	        	
	            PdfWriter.getInstance(document, new FileOutputStream(path + Testcase + ".pdf"));
	        
	        } catch (FileNotFoundException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        } catch (DocumentException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	        
	        document.open();
	        
	        Font font1 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
	        
	        for (int i = 1; i <= count; i++) {
	        	
	            String namefile = path + Testcase  + i + ".jpg";
	            
	            System.out.println("for pdf namefile : " + namefile);
	            
	            try {
	                if ((i % 2 == 1) && (i > 1)) {
	                	
	                    document.newPage();
	                }
	                
	                document.add(new Paragraph(Testcase + i, font1));
	   
	                document.add(Chunk.NEWLINE);
	               
	                Image image1 = Image.getInstance(namefile);
	                
	                image1.scaleAbsolute(500f, 800f);
	                
	                document.add(image1);
	                
	                document.add(Chunk.NEWLINE);
	                
	                new File(namefile).delete();
	                
	            } catch (Exception e) {
	            	
	                e.printStackTrace();
	            }
	        }
	
	        document.close();
        }
    }
	
	 public static int FilesCount(String folderPath) {
	      
		
	        System.out.println("folderPath=" + folderPath);
	        String[] fileNames = new File(folderPath).list();
	        System.out.println("fileNames " + fileNames);
	        System.out.println("fileNames.length = " + fileNames.length);
	        int total = 0;
	        if( fileNames != null && fileNames.length > 0 ) {
	        	System.out.println("fileNames != null && fileNames.length > 0");
	        	for (int i = 0; i < fileNames.length; i++) {
		            String suffix = ".jpg";
		            if (fileNames[i].toLowerCase().endsWith(suffix)) {
		            	System.out.println(".jpg file found");
		                total++;
		            }
	        	}
	        } else
	        	System.out.println("No snapshot images found for current execution, so no PDF is generated");
	        return total;
	    }
}