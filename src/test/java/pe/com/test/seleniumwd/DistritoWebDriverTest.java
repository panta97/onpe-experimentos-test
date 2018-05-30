package pe.com.test.seleniumwd;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.seleniumwd.fuenteDatos.Excel;
import pe.com.test.seleniumwd.page.DistritoPage;
import pe.com.test.seleniumwd.page.IniciarSesionPage;
import pe.com.test.seleniumwd.util.Utilitario;

public class DistritoWebDriverTest {

	private String urlInicial = "http://localhost:8080/OnpeWeb";
	private DistritoPage distritoPage;
	private IniciarSesionPage iniciarSesionPage;
	private String rutaCarpetaError = "C:\\CapturasPantallas\\Categorias";

	@BeforeTest
	@Parameters({ "navegador", "remoto" })
	public void inicioClase(String navegador, int remoto) throws Exception {
		this.iniciarSesionPage = new IniciarSesionPage(navegador, this.urlInicial, remoto == 1);
		this.distritoPage = new DistritoPage(this.iniciarSesionPage.getWebDriver());
	}

	@DataProvider(name = "datosInsertarEntrada")
	public static Object[][] datosInsertarPoblados(ITestContext context) {
		Object[][] datos = null;
		
		String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaInsertarArchivo");
		datos = Excel.leerExcel(rutaArchivo);
		return datos;
	}
	@DataProvider(name = "datosEditarEntrada")
	public static Object[][] datosEditarPoblados(ITestContext context) {
		Object[][] datos = null;
		
		String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaEditarArchivo");
		datos = Excel.leerExcel(rutaArchivo);
		return datos;
	}
	@DataProvider(name = "datosEliminarEntrada")
	public static Object[][] datosEliminarPoblados(ITestContext context) {
		Object[][] datos = null;
		
		String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaEliminarrArchivo");
		datos = Excel.leerExcel(rutaArchivo);
		return datos;
	}
	@Test(dataProvider = "datosInsertarEntrada")
	public void insertarDistrito(String usuario, String clave, String nombre, String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = distritoPage.insertarDistrito(nombre.trim());
			Assert.assertEquals(valorObtenido, valorEsperado);
		}catch(AssertionError e){
			Utilitario.caputarPantallarError(rutaCarpetaError, e.getMessage(), distritoPage.getWebDriver());
			Assert.fail(e.getMessage());}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@Test(dataProvider = "datosEditarEntrada", dependsOnMethods= {"insertarDistrito"})
	public void editarDistrito(String usuario, String clave, String nuevo_nombre, String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = distritoPage.editarDistrito(nuevo_nombre.trim());
			Assert.assertEquals(valorObtenido, valorEsperado);
		} catch(AssertionError e){
			Utilitario.caputarPantallarError(rutaCarpetaError, e.getMessage(), distritoPage.getWebDriver());
			Assert.fail(e.getMessage());}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@Test(dataProvider = "datosEliminarEntrada", dependsOnMethods= {"editarDistrito"})
	public void eliminarDistrito(String usuario, String clave, String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = distritoPage.eliminarDistrito();
			Assert.assertEquals(valorObtenido, valorEsperado);
		} catch(AssertionError e){
			Utilitario.caputarPantallarError(rutaCarpetaError, e.getMessage(), distritoPage.getWebDriver());
			Assert.fail(e.getMessage());}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@AfterTest
	public void tearDown() throws Exception {
		distritoPage.cerrarPagina();
	}

}
