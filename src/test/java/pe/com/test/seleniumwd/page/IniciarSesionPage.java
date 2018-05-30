package pe.com.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.driver.OnpeDriver;

public class IniciarSesionPage {

	private By cajaUsuario = By.id("user");
	private By cajaClave = By.id("password");
	private By botonIniciarSesion = By.id("btnLogin");
	private String urlInicial;
	private WebDriver webDriver = null;
	
	public IniciarSesionPage(String navegador, String urlInicial, boolean remoto){
		this.webDriver = OnpeDriver.inicializarDriver(navegador, remoto);
		this.urlInicial = urlInicial;
	}
	
	public void iniciarSesion(String usuario, String clave) throws Exception{		
		webDriver.get(urlInicial);
		webDriver.findElement(botonIniciarSesion).click();
		Thread.sleep(2000);
		webDriver.findElement(cajaUsuario).clear();
		webDriver.findElement(cajaUsuario).sendKeys(usuario);
		Thread.sleep(2000);
		webDriver.findElement(cajaClave).clear();
		webDriver.findElement(cajaClave).sendKeys(clave);
		Thread.sleep(2000);
		webDriver.findElement(botonIniciarSesion).click();	
		Thread.sleep(2000);
	}
	
	public void cerrarPagina(){
		OnpeDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
	
}
