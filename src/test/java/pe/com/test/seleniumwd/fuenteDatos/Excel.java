package pe.com.test.seleniumwd.fuenteDatos;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Excel {

	
	public static String[][] leerExcel(String rutaArchivo) {
		String[][] lista = null;
		int i = 0;
		try {
			FileInputStream archivo = new FileInputStream(new File(rutaArchivo));
			HSSFWorkbook archivoExcel = new HSSFWorkbook(archivo);
			HSSFSheet hojaExcel = archivoExcel.getSheetAt(0);
			Iterator<Row> filas = hojaExcel.iterator();
			filas.next();
			lista = new String[hojaExcel.getLastRowNum()][];
			while (filas.hasNext()) {
				Row filaActual = filas.next();
				Iterator<Cell> celdas = filaActual.cellIterator();
				lista[i] = new String[filaActual.getLastCellNum()];
				int j = 0;
				while (celdas.hasNext()) {
					Cell celda = celdas.next();
					lista[i][j] = celda.getStringCellValue();
					j++;
				}
				i++;
			}
			archivoExcel.close();
			archivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lista;
	}
}
