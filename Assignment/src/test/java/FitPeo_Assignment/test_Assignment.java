package FitPeo_Assignment;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;


public class test_Assignment {

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		
		//Selenium webdriver and chromedriver setup
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test(description = "Step 1", priority = 1)
	public void fitProHomePgae() throws InterruptedException {
		//lauching fitPeo home page in chrome
		driver.get("https://fitpeo.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@Test(description = "Step 2", priority = 2)
	public void revenueCalculatorPage() throws InterruptedException {
		
		//navigate to Revenue calculater page
		driver.findElement(By.linkText("Revenue Calculator")).click();
		Thread.sleep(2000);
	}

	@Test(description = "Step 3", priority = 3)
	public void scroll_till_silderVisible() throws InterruptedException {

		// scroll the page till the Slider is visible
		WebElement Element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/p"));
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", Element);
		Thread.sleep(3000);
	}

	@Test(description = "Step 4", priority = 4)
	public void textFeild_slider() throws InterruptedException {
		
		// updateing the text field
		
		WebElement textBoxSlider = driver.findElement(By.xpath(
				"//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']"));
		textBoxSlider.sendKeys(Keys.CONTROL, "a");
		textBoxSlider.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		//entered the slider value to set the 560 in textbox
		textBoxSlider.sendKeys(Keys.NUMPAD5, Keys.NUMPAD6, Keys.NUMPAD0);
		Thread.sleep(3000);

	}
	
	@Test(description = "Step 5", priority = 5)
	public void validate_sliderValue() throws InterruptedException {
		
		//validating the slider value and textBox value both are same 
		
		WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
		String finalSliderValue = slider.getAttribute("aria-valuenow");
		WebElement textBoxSlider = driver.findElement(By.xpath(
				"//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']"));
		String finalTextBoxSliderValue = textBoxSlider.getAttribute("value");
		System.out.println("finalSliderValue = " + finalSliderValue);
		System.out.println("finalTextBoxSliderValue = " + finalTextBoxSliderValue);
		Assert.assertEquals(finalSliderValue, finalTextBoxSliderValue);

	}

	@Test(description = "Step 6", priority = 6)
	public void Adjust_slider() throws InterruptedException {
		
		//Adjusting the slider to set the value to 820
		
		WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
		WebElement textBoxBySlider = driver.findElement(By.xpath(
				"//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']"));
		textBoxBySlider.sendKeys(Keys.CONTROL, "a");
		textBoxBySlider.sendKeys(Keys.BACK_SPACE);
		int sliderValue = 820;
		for (int i = 0; i < sliderValue; i++) {
			slider.sendKeys(Keys.ARROW_UP);
		}

		Thread.sleep(3000);
	}


	@Test(description = "Step 7", priority = 7)
	public void cpt_codes() throws InterruptedException {
		
		//check box the Selected CPT codes 
		ArrayList<String> cpt = new ArrayList<String>();
		cpt.add("CPT-99091");
		cpt.add("CPT-99453");
		cpt.add("CPT-99454");
		cpt.add("CPT-99474");
		for (int i = 1; i <= 14; i++) {
			WebElement CPT_text = driver.findElement(
					By.xpath(" //div[@class='MuiBox-root css-1eynrej'][" + i + "]//p[contains(text(),'CPT')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(false);", CPT_text);
			String cptValue = CPT_text.getText();
			Thread.sleep(2000);
			if (cpt.contains(cptValue)) {
				WebElement CPTcheckbox = driver.findElement(By.xpath(
						"//div[@class='MuiBox-root css-1eynrej'][" + i + "]//label//span//input[@type='checkbox']"));
				CPTcheckbox.click();
			}
		}

	}

	@Test(description = "Step 8", priority = 8)
	public void RcurringAmount() throws InterruptedException {
		
		//validate and verify the Total recurring amount value 110700
		String actualAmount = "110700";
		WebElement reccuringAmount = driver.findElement(
				By.xpath("//div[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1lnu3ao']//p[4]//p"));
		String ExpectedRecurringAmount = reccuringAmount.getText().substring(1);
		Assert.assertEquals(ExpectedRecurringAmount, actualAmount);
		System.out.println("validate and verify the Total recurring amount value 110700");
		Thread.sleep(5000);
	}

	@AfterClass
	public void quitpage() {
		
		//exit the fitpeo page
		driver.quit();
	}
	
}
