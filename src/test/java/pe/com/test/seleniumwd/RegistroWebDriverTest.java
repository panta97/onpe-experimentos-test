package pe.com.test.seleniumwd;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.seleniumwd.fuenteDatos.Excel;
import pe.com.test.seleniumwd.page.RegistroPage;
import pe.com.test.seleniumwd.util.Utilitario;

public class RegistroWebDriverTest {
	
	private String urlInicial = "http://localhost:8080/OnpeWeb";
	private RegistroPage registroPage;
	private String rutaCarpetaError = "C:\\CapturasPantallas\\Onpe\\Registro";
	
	@BeforeTest
	@Parameters({ "navegador", "remoto" })
	public void inicioClase(String navegador, int remoto) throws Exception {
		this.registroPage = new RegistroPage(navegador, this.urlInicial, remoto == 1);
	}
	
	@DataProvider(name = "datosInsertarEntrada")
	public static Object[][] datosInsertarPoblados(ITestContext context) {
		Object[][] datos = null;
		
		String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaInsertarArchivo");
		datos = Excel.leerExcel(rutaArchivo);
		return datos;
	}
	
	@Test(dataProvider = "datosInsertarEntrada")
	public void insertarPartido(String usuario, String clave, String confClave, String valorEsperado) throws Exception {
		try {
			String valorObtenido = registroPage.registrarse(usuario.trim(), clave.trim(), confClave.trim());
			Assert.assertEquals(valorObtenido, valorEsperado);
		}catch(AssertionError e){
			Utilitario.caputarPantallarError(rutaCarpetaError, e.getMessage(), registroPage.getWebDriver());
			Assert.fail(e.getMessage());}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	

	
}
