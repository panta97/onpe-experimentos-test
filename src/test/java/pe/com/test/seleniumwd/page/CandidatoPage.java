package pe.com.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CandidatoPage {
	//Insertar
	private By botonCandidatos = By.id("btnCandidato");
	private By botonNuevo = By.id("btnNuevo");
	private By cajaNombre = By.id("nombre");
	private By cajaApellido = By.id("apellido");
	private By botonGuardar = By.id("btnGuardar");
	private By mensajeRespuesta = By.id("message");
	//Editar
	private By botonEditar=By.xpath("//*[@id=\"table-list\"]/tbody/tr[1]/td[7]/a");
	private By botonOrdenar=By.id("sort");
	private By botonActualizar = By.id("btnActualizar");
	//Eliminar
	private By botonEliminar=By.xpath("//*[@id=\"table-list\"]/tbody/tr[1]/td[8]/a");
	private By botonEliminarConfirmar=By.id("btnEliminar");
	private WebDriver webDriver = null;
	
	public CandidatoPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String insertarCandidato(String nombre, String apellido) throws Exception {
		webDriver.findElement(botonCandidatos).click();
		Thread.sleep(2000);
		webDriver.findElement(botonNuevo).click();
		Thread.sleep(2000);
		webDriver.findElement(cajaNombre).clear();
		webDriver.findElement(cajaNombre).sendKeys(nombre);
		webDriver.findElement(cajaApellido).clear();
		webDriver.findElement(cajaApellido).sendKeys(apellido);
		webDriver.findElement(botonGuardar).click();
		Thread.sleep(2000);
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public String editarCandidato(String nuevoNombre, String nuevoApellido) throws Exception {
		webDriver.findElement(botonCandidatos).click();
		Thread.sleep(2000);
		webDriver.findElement(botonOrdenar).click();
		
		webDriver.findElement(botonEditar).click();
		Thread.sleep(2000);
		webDriver.findElement(cajaNombre).clear();
		webDriver.findElement(cajaNombre).sendKeys(nuevoNombre);
		webDriver.findElement(cajaApellido).clear();
		webDriver.findElement(cajaApellido).sendKeys(nuevoApellido);
		webDriver.findElement(botonActualizar).click();
		Thread.sleep(2000);
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public String eliminarCandidato() throws Exception {
		webDriver.findElement(botonCandidatos).click();
		Thread.sleep(2000);
		webDriver.findElement(botonOrdenar).click();
		
		webDriver.findElement(botonEliminar).click();
		Thread.sleep(2000);		
		webDriver.findElement(botonEliminarConfirmar).click();
		Thread.sleep(2000);	
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public void cerrarPagina() {
		
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
