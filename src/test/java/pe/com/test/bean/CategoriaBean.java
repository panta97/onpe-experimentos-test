package pe.com.test.bean;

public class CategoriaBean {

	private String nombre;
	
	public CategoriaBean(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Nombre: " + this.nombre;
	}
	
	
	
}
