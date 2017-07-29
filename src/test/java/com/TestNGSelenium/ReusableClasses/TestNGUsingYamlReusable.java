package com.TestNGSelenium.ReusableClasses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.TestNGSelenium.PageFactory.ToolSQAPracticeFormPageFactory;
import com.TestNGSelenium.helpers.GenericHelper;
import com.TestNGSelenium.helpers.Log;



public class TestNGUsingYamlReusable {

	Map<Object,Object> mapper = new HashMap<Object, Object>();

	ToolSQAPracticeFormPageFactory objToolsqaPF;

	public TestNGUsingYamlReusable(){

		mapper = GenericHelper.init("src\\test\\resources\\practiceForm.yml");

		System.out.println(mapper);
	}



	public void fillPracticeFormReusable() throws InterruptedException{

		//Create Toolsqa practice form Page object

		objToolsqaPF = new ToolSQAPracticeFormPageFactory();

		// Step 3: Select 'Continents' Drop down ( Use Id to identify the element )
		// Find Select element of "Single selection" using ID locator.
		Select oSelect = new Select(objToolsqaPF.continent_selectlist);

		Log.info("continent_selectlist element found");
		// Step 4:) Select option 'Europe' (Use selectByIndex)


		String mappingHeader = "Continents";

		String key1 = "option1";

		GenericHelper.getData(mapper, mappingHeader,key1);
		//oSelect.selectByVisibleText("Europe");

		// Using sleep command so that changes can be noticed
		Thread.sleep(2000);

		// Step 5: Select option 'Africa' now (Use selectByVisibleText)
		String key2 = "option2";

		GenericHelper.getData(mapper, mappingHeader,key2);

		//oSelect.selectByIndex(2);

		Thread.sleep(2000);

		// Step 6: Print all the options for the selected drop down and select one option of your choice
		// Get the size of the Select element
		List<WebElement> oSize = oSelect.getOptions();

		int iListSize = oSize.size();

		// Setting up the loop to print all the options
		for(int i =0; i < iListSize ; i++){
			// Storing the value of the option	
			String sValue = oSelect.getOptions().get(i).getText();
			// Printing the stored value
			System.out.println(sValue);

		}	   


	}

}
