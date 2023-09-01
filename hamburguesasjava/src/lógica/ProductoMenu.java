package lógica;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase;
	//MEtodos
	public ProductoMenu(String nombre,int precioBase){
		this.nombre=nombre;
		this.precioBase=precioBase;
	}
	public int getPrecio() {
			return precioBase;
		}

	public String getNombre() {
			return nombre;
		
	}
	@Override
	public String generarTextoFactura() {
		String factura = nombre +"  "+precioBase;
		return factura;
	}

}
