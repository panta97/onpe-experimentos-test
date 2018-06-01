package pe.com.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.driver.OnpeDriver;

public class PartidoPage {

	//Insertar
	private By botonPartidos = By.id("btnPartido");
	private By botonNuevo = By.id("btnNuevo");
	private By cajaNombre = By.id("nombre");
	private By botonGuardar = By.id("btnGuardar");
	private By mensajeRespuesta = By.id("message");
	//Editar
	private By botonEditar=By.xpath("//*[@id=\"table-list\"]/tbody/tr[1]/td[4]/a");
	private By botonOrdenar=By.id("sort");
	private By botonActualizar = By.id("btnActualizar");
	//Eliminar
	private By botonEliminar=By.xpath("//*[@id=\"table-list\"]/tbody/tr[1]/td[5]/a");
	private By botonEliminarConfirmar=By.id("btnEliminar");
	private WebDriver webDriver = null;
	
	public PartidoPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public String insertarPartido(String nombre) throws Exception {
		webDriver.findElement(botonPartidos).click();
		Thread.sleep(2000);
		webDriver.findElement(botonNuevo).click();
		Thread.sleep(2000);
		webDriver.findElement(cajaNombre).clear();
		webDriver.findElement(cajaNombre).sendKeys(nombre);
		webDriver.findElement(botonGuardar).click();
		Thread.sleep(2000);
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	public String editarPartido(String nuevo_nombre) throws Exception {
		webDriver.findElement(botonPartidos).click();
		Thread.sleep(2000);
		webDriver.findElement(botonOrdenar).click();
		
		webDriver.findElement(botonEditar).click();
		Thread.sleep(2000);
		webDriver.findElement(cajaNombre).clear();
		webDriver.findElement(cajaNombre).sendKeys(nuevo_nombre);
		webDriver.findElement(botonActualizar).click();
		Thread.sleep(2000);
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	public String eliminarPartido() throws Exception {
		webDriver.findElement(botonPartidos).click();
		Thread.sleep(2000);
		webDriver.findElement(botonOrdenar).click();
		
		webDriver.findElement(botonEliminar).click();
		Thread.sleep(2000);		
		webDriver.findElement(botonEliminarConfirmar).click();
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
