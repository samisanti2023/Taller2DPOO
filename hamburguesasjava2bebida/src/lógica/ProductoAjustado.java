package lógica;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado implements Producto{
	
	// Atributos
	private ProductoMenu base;
	private ArrayList<Ingrediente> listaAgregar;
	private ArrayList<Ingrediente> listaEliminar;
	
	
	
	// Metodos
	public ProductoAjustado(ProductoMenu base) {
		this.base = base;
		this.listaAgregar = new ArrayList<>();
		this.listaEliminar= new ArrayList<>();
		
	}
	public void agregarIngrediente(Ingrediente ingrediente) {
		listaAgregar.add(ingrediente);
	}
	public void quitarIngrediente(Ingrediente ingrediente) {
		listaEliminar.add(ingrediente);
	}
	public int getPrecio() {
		int suma = base.getPrecio();
		int len =listaAgregar.size();
		for(int i=0;i<len;i =i+1) {
			suma=suma+listaAgregar.get(i).getCostoAdiconal();
		}
		return suma;
	}

	public String getNombre() {
		return base.getNombre();}
	
	public ArrayList<Ingrediente> getAgregados() {
			return listaAgregar;}
			
	public ArrayList<Ingrediente> getEliminados() {
				return listaEliminar;
	
}
	@Override
	public String generarTextoFactura() {
		String factura = getNombre() +"   "+ base.getPrecio()+"\n";
		if(listaAgregar.size()!=0){
			
			for(int i=0;i<listaAgregar.size();i=i+1) {
			factura= factura +"     con ";
			factura= factura + listaAgregar.get(i).getNombre()+
					"   "+listaAgregar.get(i).getCostoAdiconal()+"\n";
			}
				
			
		}
		if(listaEliminar.size()!=0){
			
			for(int i=0;i<listaEliminar.size();i=i+1) {
			factura= factura +"    sin ";
			factura= factura + listaEliminar.get(i).getNombre()+"\n";
			}
				
			
		}
		return factura;
	}


}
