package pe.com.test.seleniumwd;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.seleniumwd.fuenteDatos.Excel;
import pe.com.test.seleniumwd.page.PartidoPage;
import pe.com.test.seleniumwd.page.IniciarSesionPage;
import pe.com.test.seleniumwd.util.Utilitario;

public class PartidoWebDriverTest {

	private String urlInicial = "http://localhost:8080/OnpeWeb";
	private PartidoPage partidoPage;
	private IniciarSesionPage iniciarSesionPage;
	private String rutaCarpetaError = "C:\\CapturasPantallas\\Categorias";

	@BeforeTest
	@Parameters({ "navegador", "remoto" })
	public void inicioClase(String navegador, int remoto) throws Exception {
		this.iniciarSesionPage = new IniciarSesionPage(navegador, this.urlInicial, remoto == 1);
		this.partidoPage = new PartidoPage(this.iniciarSesionPage.getWebDriver());
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
	public void insertarPartido(String usuario, String clave, String nombre, String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = partidoPage.insertarPartido(nombre.trim());
			Assert.assertEquals(valorObtenido, valorEsperado);
		}catch(AssertionError e){
			Utilitario.caputarPantallarError(rutaCarpetaError, e.getMessage(), partidoPage.getWebDriver());
			Assert.fail(e.getMessage());}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@Test(dataProvider = "datosEditarEntrada", dependsOnMethods= {"insertarPartido"})
	public void editarPartido(String usuario, String clave, String nuevo_nombre, String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = partidoPage.editarPartido(nuevo_nombre.trim());
			Assert.assertEquals(valorObtenido, valorEsperado);
		} catch(AssertionError e){
			Utilitario.caputarPantallarError(rutaCarpetaError, e.getMessage(), partidoPage.getWebDriver());
			Assert.fail(e.getMessage());}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@Test(dataProvider = "datosEliminarEntrada", dependsOnMethods= {"editarPartido"})
	public void eliminarPartido(String usuario, String clave, String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = partidoPage.eliminarPartido();
			Assert.assertEquals(valorObtenido, valorEsperado);
		} catch(AssertionError e){
			Utilitario.caputarPantallarError(rutaCarpetaError, e.getMessage(), partidoPage.getWebDriver());
			Assert.fail(e.getMessage());}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@AfterTest
	public void tearDown() throws Exception {
		partidoPage.cerrarPagina();
	}

}
