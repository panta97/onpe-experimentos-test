package pe.com.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.driver.OnpeDriver;

public class RegistroPage {
	
	private By linkSignUp = By.id("btnSignup");
	private By boxUsuario = By.id("inputUser");
	private By boxPassword = By.id("inputPassword");
	private By boxConfPassword = By.id("inputPasswordConfirm");
	
	private By btnSignUp = By.className("btnSignUp");
	
	private By mensajeRespuesta = By.id("message");

	private String urlInicial;
	private WebDriver webDriver = null;
	
	public RegistroPage(String navegador, String urlInicial, boolean remoto){
		this.webDriver = OnpeDriver.inicializarDriver(navegador, remoto);
		this.urlInicial = urlInicial;
	}
	
	public String registrarse(String usuario, String clave, String confClave) throws Exception {
		webDriver.get(urlInicial);
		webDriver.findElement(linkSignUp).click();
		Thread.sleep(2000);
		
		webDriver.findElement(boxUsuario).clear();
		webDriver.findElement(boxUsuario).sendKeys(usuario);
		webDriver.findElement(boxPassword).clear();
		webDriver.findElement(boxPassword).sendKeys(clave);
		webDriver.findElement(boxConfPassword).clear();
		webDriver.findElement(boxConfPassword).sendKeys(confClave);
		
		webDriver.findElement(btnSignUp).click();
		Thread.sleep(2000);
		
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public void cerrarPagina(){
		OnpeDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
