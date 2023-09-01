package lógica;

public class Ingrediente {
// Atributos
	private String nombre;
	private int costoAdicional;
	//metodos
	public Ingrediente(String nombre,int costoAdicional) {
		this.costoAdicional=costoAdicional;
		this.nombre=nombre;
		
	}
	public String getNombre() {
		return nombre;
	}
	public int getCostoAdiconal() {
		return costoAdicional;
	}
}
