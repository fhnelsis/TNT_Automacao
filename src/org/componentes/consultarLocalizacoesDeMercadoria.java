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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import com.thoughtworks.selenium.SeleniumException;

public class consultarLocalizacoesDeMercadoria {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	final String cte = "POA 99364278-2";
	String vlrFrete = "R$ 610,56";
	int notaFiscal = 955262132;
	String pesoCalculo = "200,000 kg";

	@Before
	public void setUp() throws Exception {

		baseUrl = "http://homolog.mercurio.local/";


		/* INTERNET EXPLORER */
		// DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		// ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// File file = new File("C:/dev/workspace/TNT_Automacao/IEDriverServer.exe");
		// System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		// driver = new InternetExplorerDriver();

//		/* FIREFOX */
//		//driver = new FirefoxDriver();
//		File pathToBinary = new File("C:/Users/francisconh/AppData/Local/Mozilla Firefox/firefox.exe");
//		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//		FirefoxProfile firefoxProfile = new FirefoxProfile();
//		driver = new FirefoxDriver(ffBinary,firefoxProfile);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
		/*CHROME*/
		File file = new File("C:/dev/workspace/TNT_Automacao/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	@Test
	public void testConsultarlocalizacaodemercadoras() throws Exception {
		driver.get(baseUrl + "/lms-hm/view/login");
		driver.manage().window().maximize();
		
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='formLogin']/div[2]/input")));
				
		driver.findElement(By.id("usuario")).sendKeys("melissaa");
		driver.findElement(By.id("senha")).sendKeys("melissaa");
		driver.findElement(By.xpath("//*[@id='formLogin']/div[2]/input")).click();
		
		//Click do Menu
		driver.findElement(By.xpath("//*[@id='body-web']/div/div[1]/div/div/button")).click();
		
		//Click do Menu SIM
		driver.findElement(By.xpath("//*[@id='body-web']/div/div[2]/div/div[1]/div/div/ul[1]/li[9]/a")).click();
			
		//Click do Menu Consultas e Relatórios
		driver.findElement(By.xpath("//*[@id='body-web']/div/div[2]/div/div[1]/div/div/ul[1]/li[2]/a")).click();
		
		//Click do Menu Consultar Localizações de Mercadorias
		driver.findElement(By.xpath("//*[@id='body-web']/div/div[2]/div/div[1]/div/div/ul[1]/li[2]/a")).click();
		
		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='periodoInicial']")));
		
		//Escolha de datas
		driver.findElement(By.xpath("//*[@id='periodoInicial']")).clear();
		driver.findElement(By.xpath("//*[@id='periodoInicial']")).sendKeys("08/03/2015");
		//driver.wait(1000);
		driver.findElement(By.xpath("//*[@id='periodoFinal']")).clear();
		driver.findElement(By.xpath("//*[@id='periodoFinal']")).sendKeys("10/03/2015");
		//driver.wait(1000);
		
		//Click do Menu accordion Remetente
		driver.findElement(By.xpath("//*[@id='body-web']/div[1]/div[2]/div/div[2]/div/div/div/div/div[2]/form/accordion/div/div[2]/div[1]/h4/a/span[2]")).click();
		
		//Input do CNPJ do Destinatário
		driver.findElement(By.xpath("//*[@id='remetente']")).sendKeys("04363790000120");

		//Click do Menu accordion Destinatário
		driver.findElement(By.xpath("//*[@id='body-web']/div[1]/div[2]/div/div[2]/div/div/div/div/div[2]/form/accordion/div/div[3]/div[1]/h4/a/span[2]")).click();
		
		//Input do CNPJ do Destinatário
		driver.findElement(By.xpath("//*[@id='destinatario']")).sendKeys("15102288008248");
		
		//Input do CTE
		driver.findElement(By.xpath("//*[@id='doctoServico']")).sendKeys(cte);
		
		//Click do botão Consultar
		driver.findElement(By.xpath("//*[@id='consultar']")).click();
		
		//driver.switchTo().frame();
		
		//Click no CTE
		
		driver.findElement(By.xpath("//*[@id='body-web']/div[1]/div[2]/div/div[2]/div/div/div/div/div[2]/div[4]/table/tbody/tr[1]/td[1]")).click();
		
						
			driver.findElement(By.xpath("//*[@id='vlTotalParcelas']")).getText().equalsIgnoreCase(vlrFrete);
			driver.findElement(By.xpath("//*[@id='principal.nrNotaFiscal']")).getText().equals(notaFiscal);
			driver.findElement(By.xpath("//*[@id='principal.psReferenciaCalculo']")).getText().equalsIgnoreCase(pesoCalculo);
		
	
	}

//	@After
//	public void tearDown() throws Exception {
//		driver.quit();
//		String verificationErrorString = verificationErrors.toString();
//		if (!"".equals(verificationErrorString)) {
//			fail(verificationErrorString);
//		}
//	}
//
//	private boolean isElementPresent(By by) {
//		try {
//			driver.findElement(by);
//			return true;
//		} catch (NoSuchElementException e) {
//			return false;
//		}
//	}
//
//	private boolean isAlertPresent() {
//		try {
//			driver.switchTo().alert();
//			return true;
//		} catch (NoAlertPresentException e) {
//			return false;
//		}
//	}
//
//	private String closeAlertAndGetItsText() {
//		try {
//			Alert alert = driver.switchTo().alert();
//			String alertText = alert.getText();
//			if (acceptNextAlert) {
//				alert.accept();
//			} else {
//				alert.dismiss();
//			}
//			return alertText;
//		} finally {
//			acceptNextAlert = true;
//		}
//	}

}
