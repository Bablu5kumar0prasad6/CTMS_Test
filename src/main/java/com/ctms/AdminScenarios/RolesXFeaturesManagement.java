package com.ctms.AdminScenarios;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.GlobalMethod.GlobalWait;

import jxl.Sheet;
import jxl.Workbook;

public class RolesXFeaturesManagement {

	public RolesXFeaturesManagement() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	public void RoleXFeturMngmnt() throws Exception {
		GlobalMethods.Admin_Login();

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement AdminTaskNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		AdminTaskNavig.click();

		WebElement RoleXFeaturmngmt = GWait.Wait_GetElementByXpath("//nav/ul/li[1]/div/ul/li[3]/a");
		RoleXFeaturmngmt.click();
		
		Thread.sleep(1500);

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("RoleXFeatureMNGMNT");

		int rowval = r1.getRows();
		System.out.println("No of rows: " + rowval);
		for (int i = 1; i <= rowval-1; i++) {

			String RoleName_Data = r1.getCell(0, i).getContents();
			Thread.sleep(2000);
			WebElement SelectRole = GWait.Wait_GetElementByXpath("//app-roles-features-edit/div/div/div/select");
			Select se = new Select(SelectRole);
			se.selectByVisibleText(RoleName_Data);
			int colval = r1.getRow(i).length;
			 
			System.out.println("No of Colms: " +colval);
			for (int j = 1; j < colval; j++) {
				
				String Feature_Data = r1.getCell(j, i).getContents();
				Thread.sleep(2000);
				WebElement table_element = GWait.Wait_GetElementByCSS("div:nth-child(1) > div > div > div > table > tbody");
				ArrayList<WebElement> rows = (ArrayList<WebElement>) table_element.findElements(By.tagName("tr"));
				for (WebElement row : rows) {
					ArrayList<WebElement> cells = (ArrayList<WebElement>) row.findElements(By.cssSelector("td > div > label"));
					for (WebElement cell : cells) {
						System.out.println(cell.getText());
						System.out.println(cell.getText().equalsIgnoreCase(Feature_Data));
						if (cell.getText().equalsIgnoreCase(Feature_Data)) {
							cell.click();
							break;
						}
						
					}
				}
			}
			
			/*WebElement Cancel_BTN = GWait.Wait_GetElementByXpath("//button[@type='button']");
			Cancel_BTN.click();*/
			Thread.sleep(2000);
			WebElement Assign_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
			Assign_BTN.click();
			
		}
		Thread.sleep(8000);
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		Logout_BTN.click();
		
		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();

	}

}
