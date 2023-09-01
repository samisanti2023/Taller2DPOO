package lógica;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Producto {
// Atributos
	
	private double descuento;
	private String nombreCombo;
	private ProductoMenu papas;
	private ProductoMenu hamburguesa;
	private ProductoMenu bebida;
	
	//metodos
	public Combo(double descuento,String nombreCombo) {
		this.descuento=descuento;
		this.nombreCombo=nombreCombo;
		this.papas= null;
		this.hamburguesa=null;
		this.bebida = null;
	}
	public void agregarPapasACombo(ProductoMenu papas) {
		this.papas=papas;
	}
	public void agregarHamburguesaACombo(ProductoMenu hamburguesa) {
		this.hamburguesa=hamburguesa;
	}
	public void agregarBebidaACombo(ProductoMenu bebida) {
		this.bebida=bebida;
	}
	public int getPrecio() {
		
		int suma = papas.getPrecio()+ hamburguesa.getPrecio()+bebida.getPrecio();
		double d =suma;
		double suma1=d*(100-descuento)/100;
		
		
		
		return (int) suma1 ;
	}

	public String getNombre() {
		return nombreCombo;
	}
	public ProductoMenu getPapas() {
		return papas;
	}
	public ProductoMenu getHamburguesa() {
		return hamburguesa;
	}
	public ProductoMenu getBebida() {
		return bebida;
	}
	@Override
	public String generarTextoFactura() {
		String factura = nombreCombo + "      "+getPrecio();
		return factura;
	}
}
