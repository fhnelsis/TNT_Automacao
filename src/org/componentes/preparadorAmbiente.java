package org.componentes;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.thoughtworks.selenium.SeleniumException;

import org.testng.*;
import static org.testng.AssertJUnit.*;

public class preparadorAmbiente {

	/* Variáveis Gerais */
	public String username = "joelson";
	public String password = "joelson";
	public String sgFilialLogado = "FLN";
	public String url = "http://homolog.mercurio.local/lms-ag3/";

	/* Constantes Gerais */
	public static final String campoUsuario = "username";
	public static final String campoSenha = "password";
	public static final String botaoLogin = "loginButton";
	public static final String botaoVoltar = "back_button";
	public static final String mensagemSucesso = "message.div";
	public static final String logo = "logo_img";
	public static final String browser = "*internetexplorer";

	/* Constantes manterMeuPerfil */
	public String urlMeuPerfil = "http://homolog.mercurio.local/lms-ag3/seguranca/manterMeuPerfil.do?cmd=main";
	public static final String campoSGFilialLogado = "filialLogado.sgFilial";
	public static final String botaoCarregar = "carregar";

	/* Constantes cadastrarPedidoColeta */
	public String urlCadastrarPedidoColeta = "http://homolog.mercurio.local/lms-ag3/coleta/cadastrarPedidoColeta.do?cmd=main";
	static final String campoCliente = "cliente.pessoa.nrIdentificacao";
	static final String campoHorarioLimite = "hrLimiteColeta";
	static final String campoModoColeta = "tpModoPedidoColeta";
	static final String campoHorarioLimiteColeta = "hrLimiteColeta";
	static final String campoContato = "hrLimiteColeta";
	static final String campoNomePessoa = "cliente.pessoa.nmPessoa";
	public String nroIdentificacaoCliente = "02406081000150";
	public String horarioLimiteColeta = "20:00";
	public InternetExplorerDriver driver = new InternetExplorerDriver();

	@BeforeClass
	public static void setupTest() {
		try {
			WindowsUtils.killByName("IEDriverServer.exe");
			WindowsUtils.killByName("iexplorer.exe");
		} catch (Exception e) {
			// Gamba da zueira
			/*
			 * throw new SeleniumException(
			 * "Erro: Algum erro ocorreu ao tentar finalizar os processos anteriores do IEDriver."
			 * );
			 */
		}
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// File file = new File("C:/dev/IEDriverServer.exe");
		// System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		System.setProperty("webdriver.ie.driver", "C:/dev/IEDriverServer.exe");
	}

	@AfterClass
	public static void tearDownTest() {
		try {
			WindowsUtils.killByName("IEDriverServer.exe");
			WindowsUtils.killByName("iexplorer.exe");
		} catch (Exception e) {
			// Gamba da zueira
			/*
			 * throw new SeleniumException(
			 * 
			 * "Erro: Algum erro ocorreu ao tentar finalizar os processos anteriores do IEDriver."
			 * );
			 */
		}
	}

	@Test
	public void setAmbiente() {
	}
}