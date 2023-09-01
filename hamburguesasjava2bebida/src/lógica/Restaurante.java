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
	private HashMap<String,ProductoMenu> mapaBebidas;
	private ArrayList<ProductoMenu> listaBebidas;
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
		this.mapaBebidas = new HashMap<>();
		this.listaBebidas = new ArrayList<>();
		this.listaMenu = new ArrayList<>();
		this.listaIngredientes = new ArrayList<>();
		this.listaCombos = new ArrayList<>();

	}


	public void agregarIDElemento(String string) {
		pedidoActual.getlistaIdsElementos().add(string);

	}

	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		pedidoActual= new Pedido(nombreCliente,direccionCliente, Pedido.getNumeroPedidos());



	}
	//// Agregar productos a un pedido
	public void agregarBebida(String base) {
		pedidoActual.agregarProducto(listaBebidas.get(Integer.parseInt(base)));
	}

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
	public boolean cerrarYGuardarPedido() throws IOException {
		String id = Integer.toString(pedidoActual.getidPedido());
		File nuevaFactura = new File("C:/Users/samis/Downloads/factura"+id+".txt");
		pedidoActual.guardarFactura(nuevaFactura);
		boolean bool = determinarIgualdad();
		mapaPedidos.put(id,pedidoActual);
		pedidoActual = null;
		return bool;

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
	public ArrayList<ProductoMenu> getListaBebidas(){
		return listaBebidas;
	}
	public ArrayList<ProductoMenu> getListaMenu(){
		return listaMenu;
	}
	public void cargarInformacionRestaurante() throws IOException {
		cargarMenu();
		cargarIngredientes();
		cargarBebidas();
		cargarCombos();
		crearListaMenu();
		crearListaIngredientes();
		crearListaCombos();
		crearListaBebidas();


	}
	private boolean determinarIgualdad() {
	Set<String> ids=mapaPedidos.keySet();
	Iterator<String> llavesIt = ids.iterator();
	while(llavesIt.hasNext()) {
		ArrayList<String> ArrayElemPed= mapaPedidos.get(llavesIt.next()).getlistaIdsElementos();
	
		boolean bool = ArrayElemPed.equals(pedidoActual.getlistaIdsElementos());
		if(bool==true) {
			return true;
		}
		
	}
	return false;}

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
	private void cargarBebidas() throws IOException  {


		BufferedReader br = new BufferedReader(new FileReader("./data/bebidas.txt"));

		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");
			String nombreProducto = partes[0];
			int precio = Integer.parseInt(partes[1]);

			ProductoMenu producto = mapaBebidas.get(nombreProducto);
			if (producto == null) {
				producto = new ProductoMenu(nombreProducto,precio);
				mapaBebidas.put(nombreProducto, producto);

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
				ProductoMenu bebida =mapaBebidas.get(nombreBebida);

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
	
	private void crearListaBebidas()	{
		Collection<ProductoMenu> llaves=mapaBebidas.values();
		Iterator<ProductoMenu> it = llaves.iterator();
		while (it.hasNext()) {
			listaBebidas.add((ProductoMenu) it.next());
		}}


}
