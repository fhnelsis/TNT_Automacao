package org.componentes;

import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class consultarLocalizacoesDeMercadoria {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {

		baseUrl = "http://homolog.mercurio.local/";

		/* INTERNET EXPLORER */
		// DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		// ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// File file = new File("C:/dev/workspace/TNT_Automacao/IEDriverServer.exe");
		// System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		// driver = new InternetExplorerDriver();

		/* FIREFOX */
		// driver = new FirefoxDriver();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// File pathToBinary = new File("C:/Users/francisconh/AppData/Local/Mozilla Firefox/firefox.exe");
		// FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		// FirefoxProfile firefoxProfile = new FirefoxProfile();
		// WebDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);
		
		/*CHROME*/
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		File file = new File("C:/dev/workspace/TNT_Automacao/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//WebDriver driver = new RemoteWebDriver(capabilities);
		//Capabilities actualCapabilities = ((RemoteWebDriver) file).getCapabilities();
	
	}

	@Test
	public void testConsultarlocalizacaodemercadoras() throws Exception {
		driver.get(baseUrl + "/lms-hm/view/login");
		//driver.findElement(By.id("usuario")).clear();
		driver.findElement(By.id("usuario")).sendKeys("melissaa");
		//driver.findElement(By.id("senha")).clear();
		driver.findElement(By.id("senha")).sendKeys("melissaa");
		driver.findElement(By.className("Entrar")).click();
		
		driver.findElement(By.cssSelector("button.navbar-toggle")).click();
		driver.findElement(By.linkText("SIM")).click();
		driver.findElement(By.linkText("Consultas e Relatórios")).click();
		driver.findElement(By.linkText("Consultar Localizações de Mercadoria"))
				.click();
		driver.findElement(By.cssSelector("span.ng-binding.ng-scope")).click();
		driver.findElement(By.cssSelector("span.ng-binding.ng-scope")).click();
		driver.findElement(
				By.xpath("//body[@id='body-web']/div/div[2]/div/div[2]/div/div/div/div/div[2]/form/accordion/div/div[2]/div/h4/a/span[2]"))
				.click();
		driver.findElement(By.id("remetente")).clear();
		driver.findElement(By.id("remetente")).sendKeys("04363790000120");
		driver.findElement(
				By.xpath("//body[@id='body-web']/div/div[2]/div/div[2]/div/div/div/div/div[2]/form/accordion/div/div[3]/div/h4/a/span[2]"))
				.click();
		driver.findElement(By.id("destinatario")).clear();
		driver.findElement(By.id("destinatario")).sendKeys("15102288008248");
		driver.findElement(By.id("openDatePicker")).click();
		driver.findElement(By.linkText("8")).click();
		driver.findElement(By.xpath("(//span[@id='openDatePicker'])[2]"))
				.click();
		driver.findElement(By.linkText("10")).click();
		driver.findElement(By.id("consultar")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}
