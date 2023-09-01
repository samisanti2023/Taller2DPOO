package lógica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class Restaurante {
	// Atributos
	private HashMap<String,ProductoMenu> mapaMenu;
	private HashMap<String,Combo> mapaCombos;
	private HashMap<String,Ingrediente> mapaIngredientes;
	private HashMap<String,Pedido> mapaPedidos;
	private ArrayList<ProductoMenu> listaMenu;
	private ArrayList<Ingrediente> listaIngredientes;
	private ArrayList<Combo> listaCombos;
	private Pedido pedidoActual;




	// Metodos
	public Restaurante() {

		this.mapaCombos= new HashMap<>();
		this.mapaMenu= new HashMap<>();
		this.mapaIngredientes= new HashMap<>();
		this.mapaPedidos = new HashMap<>();
		this.listaMenu = new ArrayList<>();
		this.listaIngredientes = new ArrayList<>();
		this.listaCombos = new ArrayList<>();






	}

	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		pedidoActual= new Pedido(nombreCliente,direccionCliente, Pedido.getNumeroPedidos());



	}
	//// Agregar productos a un pedido


	public void agregarProductoMenu(String base) {
		pedidoActual.agregarProducto(listaMenu.get(Integer.parseInt(base)));
	}
	public void agregarCombo(String base) {
		pedidoActual.agregarProducto(listaCombos.get(Integer.parseInt(base)));
	}
	public void agregarProductoAjustado(String base,String agregar,
			String eliminar) {
		ProductoAjustado prod = new ProductoAjustado(listaMenu.get(Integer.parseInt(base)));
		if (agregar.equals("n")==false) {
			String[] ag = agregar.split(",");


			int lenAg=ag.length;



			for(int i=0;i<lenAg;i=i+1) {
				prod.agregarIngrediente(listaIngredientes.get(Integer.parseInt(ag[i])));
			}}
		if (eliminar.equals("n")==false) {
			String[] el = eliminar.split(",");
			int lenEl=el.length;
			for(int i=0;i<lenEl;i=i+1) {
				prod.quitarIngrediente(listaIngredientes.get(Integer.parseInt(el[i])));
			}}
		pedidoActual.agregarProducto(prod);


	}
	public void cerrarYGuardarPedido() throws IOException {
		String id = Integer.toString(pedidoActual.getidPedido());
		mapaPedidos.put(id,pedidoActual);
		File nuevaFactura = new File("C:/Users/samis/Downloads/factura"+id+".txt");
		pedidoActual.guardarFactura(nuevaFactura);
		pedidoActual = null;

	}

	public Pedido getPedidoEnCurso() {
		return pedidoActual;

	}
	public Pedido getPedidoDadiID(String id) {
		return mapaPedidos.get(id);
	}
	public HashMap<String, ProductoMenu> getMenuBase(){
		return mapaMenu;
	}
	public HashMap<String,Ingrediente> getIngredientes(){
		return mapaIngredientes;
	}
	public HashMap<String,Combo> getCombos(){
		return mapaCombos;
	}
	public ArrayList<Combo> getListaCombos(){
		return listaCombos;
	}
	public ArrayList<Ingrediente> getListaIngredientes(){
		return listaIngredientes;
	}
	public ArrayList<ProductoMenu> getListaMenu(){
		return listaMenu;
	}
	public void cargarInformacionRestaurante() throws IOException {
		cargarMenu();
		cargarIngredientes();
		cargarCombos();
		crearListaMenu();
		crearListaIngredientes();
		crearListaCombos();


	}
	private void cargarMenu() throws IOException  {


		BufferedReader br = new BufferedReader(new FileReader("./data/menu.txt"));

		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");
			String nombreProducto = partes[0];
			int precio = Integer.parseInt(partes[1]);

			ProductoMenu producto = mapaMenu.get(nombreProducto);
			if (producto == null) {
				producto = new ProductoMenu(nombreProducto,precio);
				mapaMenu.put(nombreProducto, producto);

			}
			linea = br.readLine(); // Leer la siguiente línea
		}

		br.close();


	}
	private void cargarIngredientes() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("./data/ingredientes.txt"));
		String linea = br.readLine(); 


		while (linea != null) {
			String[] partes = linea.split(";");
			String nombreProducto = partes[0];
			int precio = Integer.parseInt(partes[1]);

			Ingrediente producto = mapaIngredientes.get(nombreProducto);
			if (producto == null) {
				producto = new Ingrediente(nombreProducto,precio);
				mapaIngredientes.put(nombreProducto, producto);

			}
			linea = br.readLine(); // Leer la siguiente línea
		}
		br.close();
	}
	private void cargarCombos() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("./data/combos.txt"));

		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");
			String nombreProducto = partes[0];
			String descuento =partes[1];
			descuento =descuento.replaceAll("%","");
			double descuento1 = Double.parseDouble(descuento);
			String nombreHamburguesa = partes[2];
			String nombrePapas = partes[3];
			String nombreBebida = partes[4];

			Combo producto = mapaCombos.get(nombreProducto);
			if (producto == null) {
				producto = new Combo(descuento1,nombreProducto);

				ProductoMenu papas = mapaMenu.get(nombrePapas);
				ProductoMenu hamburguesa = mapaMenu.get(nombreHamburguesa);
				ProductoMenu bebida =mapaMenu.get(nombreBebida);

				producto.agregarPapasACombo(papas);
				producto.agregarHamburguesaACombo(hamburguesa);
				producto.agregarBebidaACombo(bebida);

				mapaCombos.put(nombreProducto, producto);

			}
			linea = br.readLine(); // Leer la siguiente línea
		}	br.close();
	}
	private void crearListaCombos()	{
		Collection<Combo> llaves=mapaCombos.values();
		Iterator<Combo> it = llaves.iterator();
		while (it.hasNext()) {
			listaCombos.add((Combo) it.next());
		}}
	private void crearListaIngredientes()	{
		Collection<Ingrediente> llaves=mapaIngredientes.values();
		Iterator<Ingrediente> it = llaves.iterator();
		while (it.hasNext()) {
			listaIngredientes.add((Ingrediente) it.next());
		}}

	private void crearListaMenu()	{
		Collection<ProductoMenu> llaves=mapaMenu.values();
		Iterator<ProductoMenu> it = llaves.iterator();
		while (it.hasNext()) {
			listaMenu.add((ProductoMenu) it.next());
		}}


}
