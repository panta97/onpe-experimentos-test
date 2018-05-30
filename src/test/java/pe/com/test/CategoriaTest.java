package pe.com.test;

import org.testng.annotations.Test;

import pe.com.core.business.CategoriaBusiness;
import pe.com.core.entity.Categoria;
import pe.com.test.bean.CategoriaBean;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CategoriaTest {
	
	public final static CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
	public static Categoria categoria =null;
	
	@BeforeMethod
	public void antesDeCadaPruebaUnitaria() {
		System.out.println("Antes de cada P.U.");
	}

	@AfterMethod
	public void despuesDeCadaPruebaUnitaria() {
		System.out.println("Despues de cada P.U.");
	}
	

	@BeforeClass
	public void beforeClass() {
		System.out.println("Al inicio de la clase");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Al final de la clase");
	}
	
	@DataProvider(name = "datosDeCategorias")
	public static Object[][] datosLlenarCategorias(){
		return new Object[][]
				{
					{new CategoriaBean("Menestras")},
					{new CategoriaBean("Madera")},
					{new CategoriaBean("Lacteos")}
				};
	}
	
	
	@Test
	public void insertar() {
		try {
			System.out.println("Met. Insertar");
			//Datos de entrada
			categoria = new Categoria();
			categoria.setNombre("Chocolates");
			//Ejecutar prueba
			categoriaBusiness.insertar(categoria);
			//Validar datos de salida
			Assert.assertTrue(categoria.getIdCategoria()>0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Mensaje: " + e.getMessage());
		}
	}
	
	
	@Test
	@Parameters({"VarNombreInsertar"})
	public void insertarConParametros(String nombre) {
		try {
			System.out.println("Met. Insertar");
			//Datos de entrada
			categoria = new Categoria();
			categoria.setNombre(nombre);
			//Ejecutar prueba
			categoriaBusiness.insertar(categoria);
			//Validar datos de salida
			Assert.assertTrue(categoria.getIdCategoria()>0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Mensaje: " + e.getMessage());
		}
	}
	
	
	@Test(dataProvider = "datosDeCategorias")
	public void insertarDataProvider(CategoriaBean bean) {
		try {
			System.out.println("Met. Insertar");
			//Datos de entrada
			categoria = new Categoria();
			categoria.setNombre(bean.getNombre());
			//Ejecutar prueba
			categoriaBusiness.insertar(categoria);
			//Validar datos de salida
			Assert.assertTrue(categoria.getIdCategoria()>0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Mensaje: " + e.getMessage());
		}
	}
	
	
	@Test(dependsOnMethods = {"insertar"})
	public void eliminar() {
		try {
			System.out.println("Met. Eliminar");
			categoriaBusiness.eliminar(categoria);
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Mensaje: " + e.getMessage());
		}
	}

}
