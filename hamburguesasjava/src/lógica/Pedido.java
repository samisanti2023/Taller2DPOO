package lógica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pedido {
	// atributos
		public static int numeroPedidos;
		private int idPedido;
		private String nombreCliente;
		private String direccionCliente;
		private ArrayList<Producto> elementos;
		
		// metodos
		
		public Pedido(String nombreCliente, String direccionCliente, int idPedido) {
			numeroPedidos++;
			this.nombreCliente= nombreCliente;
			this.direccionCliente=direccionCliente;
			this.idPedido=idPedido;
			this.elementos = new ArrayList<>();
					
			
		}
		public static int getNumeroPedidos(){
			return numeroPedidos;
		}
		public int getidPedido() {
			return idPedido;
		}
		public String getNombreCliente() {
			return nombreCliente;
		}
		public String getDireccion() {
			return direccionCliente;
		}
		public void agregarProducto(Producto nuevoItem) {
			elementos.add(nuevoItem);
			
		}
		private int getPrecioNetoPedido() {
			int sumaTotal = 0;
			for (int i=0;i<elementos.size();i++) {
				Producto producto= elementos.get(i);
				int precioP=producto.getPrecio();
				
				sumaTotal=sumaTotal +precioP;
				
			}
			
			return sumaTotal;
		}
		private int getPrecioTotalPedido(){
			int precioTotal = (int) (getPrecioNetoPedido()+getIVAPedido());
			return precioTotal;
			
		}
		private double getIVAPedido() {
			double precioNeto = (double)(getPrecioNetoPedido());
			double iva = precioNeto*0.19;
			return iva;
		}
		private String generarFactura() {
			String factura = "Hamburguesas el corral\n"+"\n"+"\n";
			factura = factura + "ID Pedido: "+getidPedido() +"\n";
			factura = factura + "Nombre: "+getNombreCliente() +"\n";
			factura = factura + "Dirección: "+getDireccion() +"\n";
			for(int i=0;i<elementos.size();i=i+1) {
				Producto elemento = elementos.get(i);
				
				factura=factura+elemento.generarTextoFactura()+"\n";
			}
			factura = factura +"SUBTOTAL: "+getPrecioNetoPedido() +"\n";
			factura = factura +"IVA: "+getIVAPedido() +"\n";
			factura = factura +"Total: "+getPrecioTotalPedido() +"\n";
			return factura;
		}
		public void guardarFactura(File archivo) throws IOException {
			FileWriter fichero = new FileWriter(archivo);
			fichero.write(generarFactura());
			fichero.close();
		}
		
}
