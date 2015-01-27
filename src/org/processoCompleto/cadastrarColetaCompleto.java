package org.processoCompleto;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.thoughtworks.selenium.SeleniumException;

public class cadastrarColetaCompleto {

	String username = "joelson";
	String password = "joelson";
	String sgFilialLogado = "FLN";
	String url = "http://homolog.mercurio.local/lms-ag3/";

	/* Constantes Gerais */
	final String campoUsuario = "username";
	final String campoSenha = "password";
	final String botaoLogin = "loginButton";
	final String botaoVoltar = "back_button";
	final String mensagemSucesso = "message.div";
	final String logo = "logo_img";
	final String browser = "*internetexplorer";

	/* Constantes manterMeuPerfil */
	final String urlMeuPerfil = url + "seguranca/manterMeuPerfil.do?cmd=main";
	final String campoSGFilialLogado = "filialLogado.sgFilial";
	final String botaoCarregar = "carregar";
	final String frameManterMeuPerfil = "manterMeuPerfil_iframe";

	/* Constantes cadastrarPedidoColeta */
	final String urlCadastrarPedidoColeta = url
			+ "coleta/cadastrarPedidoColeta.do?cmd=main";
	final String campoCliente = "cliente.pessoa.nrIdentificacao";
	final String campoHorarioLimite = "hrLimiteColeta";
	final String campoModoColeta = "tpModoPedidoColeta";
	final String campoHorarioLimiteColeta = "hrLimiteColeta";
	final String campoContato = "hrLimiteColeta";
	final String campoNomePessoa = "cliente.pessoa.nmPessoa";
	final String framePedidoColeta = "pedidoColeta_iframe";
	final String urlCadastrarPedidoColetaDetalhe = url
			+ "coleta/cadastrarPedidoColeta.do?cmd=detalheColeta";

	/* Variáveis cadastrarPedidoColeta */
	String nroIdentificacaoCliente = "02406081000150";
	String nroIdentificacaoDestinatario = "02543945000428";
	String horarioLimiteColeta = "20:00";

	@SuppressWarnings("unchecked")
	@Test
	public void cadastrarColetaCompleto() throws Exception {
		try {
			WindowsUtils.killByName("IEDriverServer.exe");
			WindowsUtils.killByName("iexplorer.exe");
		} catch (Exception e) {
			// // Gamba da zueira
			// throw new SeleniumException(
			// "Erro: Algum erro ocorreu ao tentar finalizar os processos anteriores do IEDriver.");
		}
		DesiredCapabilities ieCapabilities = DesiredCapabilities
				.internetExplorer();
		ieCapabilities
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);

		File file = new File("C:/dev/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		InternetExplorerDriver driver = new InternetExplorerDriver();

		/* Manter Perfil */
		driver.navigate().to(url);
		driver.manage().window().maximize();

		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By
						.id(campoUsuario))).sendKeys(username);
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.id(campoSenha))).sendKeys(password);

		String h1 = driver.getWindowHandle();

		driver.findElement(By.id(botaoLogin)).click();
		Thread.sleep(3000);

		driver.switchTo().window(h1).navigate().to(urlMeuPerfil);
		driver.switchTo().frame(
				driver.findElement(By.name(frameManterMeuPerfil)));

		// Wait carregar dados da filial logada
		int size = 0;
		while (size == 0) {
			Thread.sleep(100);
			size = driver.findElement(By.id(campoSGFilialLogado))
					.getAttribute("value").length();
		}

		new WebDriverWait(driver, 20).until(
				ExpectedConditions.visibilityOfElementLocated(By
						.id(campoSGFilialLogado))).clear();
		driver.findElement(By.id(campoSGFilialLogado)).sendKeys(sgFilialLogado);
		driver.findElement(By.name("filialLogado.pessoa.nmFantasia")).click();
		String verificadorFilial = driver.findElement(
				By.id(campoSGFilialLogado)).getAttribute("value");

		if (verificadorFilial.equalsIgnoreCase(sgFilialLogado)) {
			driver.findElement(By.id(botaoCarregar)).click();
			try {
				driver.switchTo().parentFrame()
						.findElement(By.id(mensagemSucesso));
				new WebDriverWait(driver, 15).until(ExpectedConditions
						.visibilityOfElementLocated(By.id(mensagemSucesso)));
				assertTrue(verificadorFilial.equals(sgFilialLogado));

			} catch (Exception e) {
				throw new SeleniumException("Erro: Filial não carregada!");
			}
		} else {
			throw new SeleniumException("Erro: A filial carregada é incorreta.");
		}

		/* Cadastrar Coleta */
		driver.navigate().to(urlCadastrarPedidoColeta);
		
		driver.switchTo().frame("pedidoColeta_iframe");
		Select dropdown = new Select(driver.findElement(By.id("tpModoPedidoColeta")));
		dropdown.selectByVisibleText("Balcão");
		
//		driver.switchTo().frame(driver.findElement(By.name(framePedidoColeta)));
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.id(campoCliente))).sendKeys(nroIdentificacaoCliente);
		driver.findElement(By.id(campoHorarioLimite)).click();

		
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("hrLimiteColeta"))).sendKeys(horarioLimiteColeta);
		driver.findElement(By.id("dsInfColeta")).sendKeys("FHPN");


		// Wait carregar dados cliente
		new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.id("telefoneEndereco.nrTelefone"))
						.getAttribute("value").length() != 0;
			}
		});

		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By
						.id(campoHorarioLimite))).sendKeys(horarioLimiteColeta);
		driver.findElement(By.id("dsInfColeta")).sendKeys("FHPN");

		/* Detalhes da Coleta */
		driver.findElement(By.id("detalheColeta_spanTexto")).click();
		driver.findElement(By.id("cliente.pessoa.nrIdentificacao")).sendKeys(
				nroIdentificacaoDestinatario);
		
		// TODO Teste
			
		driver.findElement(By.id("vlMercadoria")).click();

		// Wait carregar dados destinatário
		new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.id("nmDestinatario"))
						.getAttribute("value").length() != 0;
			}
		});

		driver.findElement(By.id("vlMercadoria")).click();
		driver.findElement(By.id("vlMercadoria")).sendKeys("");
		driver.findElement(By.id("vlMercadoria")).sendKeys("100,00");

		// new WebDriverWait(driver,
		// 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("vlMercadoria"))).sendKeys("100,00");
		// new WebDriverWait(driver,
		// 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("vlMercadoria"))).sendKeys("100,00");

		driver.findElement(By.id("psMercadoria")).click();
		driver.findElement(By.id("psMercadoria")).sendKeys("100,000");

		driver.findElement(By.id("qtVolumes")).sendKeys("1");
		driver.findElement(By.id("nmDestinatario")).click();

		driver.findElement(By.id("storeButton")).click();
	}
}