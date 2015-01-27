package org.componentes;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class cadastrarPedidoColeta extends manterMeuPerfil {

	@BeforeTest
	public void antesCadastrarPedidoColeta() {
	}

	@Test
	public void cadastrarPedidoColeta() throws Exception {

		driver.navigate().to(urlCadastrarPedidoColeta);
		new WebDriverWait(driver, 15).until(
				ExpectedConditions.visibilityOfElementLocated(By
						.id(campoCliente))).sendKeys(nroIdentificacaoCliente);
		driver.findElement(By.id(campoHorarioLimite)).click();

		// Wait carregar dados cliente
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.id(campoNomePessoa)).getText().length() != 0;
			}
		});

		new WebDriverWait(driver, 10).until(
				ExpectedConditions.visibilityOfElementLocated(By
						.id(campoHorarioLimite))).sendKeys(horarioLimiteColeta);
	}
}