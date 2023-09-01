package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import lógica.Combo;
import lógica.Ingrediente;
import lógica.Pedido;
import lógica.ProductoMenu;
import lógica.Restaurante;



public class Aplicacion {
	
	private Restaurante elRestaurante;
	public void ejecutarAplicacion() throws IOException
	{
		System.out.println("Hamburguesas El Corral");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					cargarDatosYmostrarMenu();
				else if (opcion_seleccionada == 2 && elRestaurante != null)
					ejecutarNuevoPedido();
				else if (opcion_seleccionada == 3 && elRestaurante != null)
					ejecutarAgregarProductoAPedido();
				else if (opcion_seleccionada == 4 && elRestaurante != null)
					ejecutarCerrarPedidoyGuardarFactura();
				else if (opcion_seleccionada == 5 && elRestaurante != null)
					ejecutarConsultarPedidodadoID();

				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (elRestaurante == null)
				{
					System.out.println("Para poder ejecutar esta opción primero debe cargar un archivo de atletas.");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}


	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar menú");
		System.out.println("2. Iniciar nuevo pedido");
		System.out.println("3. Agregar producto a pedido");
		System.out.println("4. Cerrar pedido y guardar factura");
		System.out.println("5. Consultar pedido dado su id");
		System.out.println("6. Salir");

	}
	public void MenuProductoOCombo()
	{
		System.out.println("Que desea agregar?");
		System.out.println("1. Producto del menú");
		System.out.println("2. Combo");
		System.out.println("3. Producto modificado");
	}
	
	private void cargarDatosYmostrarMenu() {
		System.out.println("Cargando menú: ");


		try
		{
			elRestaurante = new Restaurante();
			elRestaurante.cargarInformacionRestaurante();
			System.out.println("Se cargó el archivo menu");
			
			System.out.println("Los productos del menú son ");
			
			ArrayList<ProductoMenu> menu =elRestaurante.getListaMenu();
			ArrayList<Ingrediente> ingredientes =elRestaurante.getListaIngredientes();
			ArrayList<Combo> combos =elRestaurante.getListaCombos();
			
			int lenMen =menu.size();
			int lenIn =ingredientes.size();
			int lenCom =combos.size();
			
			for(int i=0;i<lenMen;i =i+1) {
				
			ProductoMenu Prod = menu.get(i);
			System.out.println(i + ". "+Prod.getNombre()+"    "+Prod.getPrecio());	
			}
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("INGREDIENTES ");
			for(int i=0;i<lenIn;i =i+1) {
				
			Ingrediente Prod = ingredientes.get(i);
			System.out.println(i + ". "+Prod.getNombre()+"   "+Prod.getCostoAdiconal());	
			}
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("COMBOS ");
			for(int i=0;i<lenCom;i =i+1) {
				
			Combo Prod = combos.get(i);
			System.out.println(i + ". "+Prod.getNombre()+"   "
					+Prod.getPapas().getNombre()+", "+Prod.getHamburguesa().getNombre()+
					", "+Prod.getBebida().getNombre()+ "   "+Prod.getPrecio());	
			}
			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	private void ejecutarNuevoPedido() {
		String nombre =input("Ingrese su nombre ");
		String dir = input("Ingrese su dirección ");
		elRestaurante.iniciarPedido(nombre, dir);
		
		
	}
	private void ejecutarAgregarProductoAPedido()
	{
		
	   MenuProductoOCombo();
				
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarAgregarDeMenu();
				else if (opcion_seleccionada == 2 && elRestaurante != null)
					ejecutarAgregarCombo();
				else if (opcion_seleccionada == 3 && elRestaurante != null)
					ejecutarAgregarProductoAjustado();

				
	
		
		}
	
	private void ejecutarAgregarDeMenu() {
		String numP =input("Ingrese el número correspondiente al pedido del"
				+ "menu que desea realizar");
		elRestaurante.agregarProductoMenu(numP);
	}
	private void ejecutarAgregarCombo() {
		String numP =input("Ingrese el número correspondiente al pedido del"
				+ "combo que desea realizar");
		elRestaurante.agregarCombo(numP);
	}
	private void ejecutarAgregarProductoAjustado() {
		String numBase =input("Ingrese el número correspondiente al producto"
				+ " base que desea");
		String agregar =input("Ingrese los números separados con comas de los elementos que quiere agregar. Ingrese n para no ");
		String eliminar =input("Ingrese los números separados con comas de los elementos que quiere agregar. Ingrese n para no");
		elRestaurante.agregarProductoAjustado(numBase, agregar, eliminar);
	}
	
	
	
	
	
	private void ejecutarCerrarPedidoyGuardarFactura() throws IOException {
		elRestaurante.cerrarYGuardarPedido();
		
		
	}
	private void ejecutarConsultarPedidodadoID() {
		String id =input("Ingrese id: ");
		Pedido pedido = elRestaurante.getPedidoDadiID(id);
		System.out.println("ID: "+id+"\n");
		System.out.println("Nombre: "+pedido.getNombreCliente()+"\n");
		System.out.println("Dirección: "+pedido.getDireccion()+"\n");

		
		
	}
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws IOException {
		
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}

}
