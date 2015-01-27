package org.componentes;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.SeleniumException;

import javax.json.*;

public class manterMeuPerfil extends preparadorAmbiente {

	@Test
	public void manterMeuPerfil() throws Exception {

		driver.navigate().to(url);
		driver.manage().window().maximize();

		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By
						.id(campoUsuario))).sendKeys(username);
		new WebDriverWait(driver, 10)
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.id(campoSenha))).sendKeys(password);

		String h1 = driver.getWindowHandle();

		// Operação que gera nova janela
		driver.findElement(By.id(botaoLogin)).click();
		Thread.sleep(3000);

		driver.switchTo().window(h1).navigate().to(urlMeuPerfil);
		driver.switchTo().frame(
				driver.findElement(By.name("manterMeuPerfil_iframe")));

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
			} catch (Exception e) {
				throw new SeleniumException("Erro: Filial não carregada!");
			}
		} else {
			throw new SeleniumException("Erro: A filial carregada é incorreta.");
		}
	}
}