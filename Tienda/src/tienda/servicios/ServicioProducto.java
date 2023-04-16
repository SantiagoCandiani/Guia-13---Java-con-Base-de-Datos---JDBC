package tienda.servicios;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDao;

/**
 * @author scandiani
 */
public class ServicioProducto {

    private ProductoDao dao;
    private Scanner leer; // Creo el Scanner para utilizar en toda mi clase
    private ServicioFabricante servf;

    public ServicioProducto() {
        this.dao = new ProductoDao();
        this.leer = new Scanner(System.in).useDelimiter("\n"); // Cuando se invoca la clase, habilita el flujo de datos
        this.servf = new ServicioFabricante();
    }

    //------------------------------------------------------//
    public Collection<Producto> listarProductos() throws Exception {
        try {
            Collection<Producto> listaProductos = dao.listarProductos();
            return listaProductos;
        } catch (Exception e) {
            throw e;
        }
    }

    //a) Lista el nombre de todos los productos que hay en la tabla producto:
    public void imprimirNombreProductos() throws Exception {
        System.out.println("- Los nombres de los productos son:");
        try {
            //Listar porductos
            Collection<Producto> listaProductos = listarProductos();
            //Imprimimos los productos, todos los argumentos
            if (listaProductos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto aux : listaProductos) {
                    System.out.println("* " + aux.getNombre());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //b) Lista los nombres y los precios de todos los productos de la tabla producto.
    public void imprimirNombreYPrecioProductos() throws Exception {
        System.out.println("- El nombre y precio de cada producto queda:");
        try {
            //Listar porductos
            Collection<Producto> listaProductos = listarProductos();
            //Imprimimos los productos, todos los argumentos
            if (listaProductos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto aux : listaProductos) {
                    System.out.println("* " + aux.getNombre() + " - $" + aux.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //------------------------------------------------------//
    public Collection<Producto> listarProductosEntrePrecios() throws Exception {
        try {
            Collection<Producto> listaProductos = dao.listarProductosEntrePrecios();
            return listaProductos;
        } catch (Exception e) {
            throw e;
        }
    }

    //c) Listar aquellos productos que su precio esté entre 120 y 202.
    public void imprimirProductosEntrePrecios() throws Exception {
        System.out.println("- Los productos entre $120 y $202 son:");
        try {
            //Listar porductos
            Collection<Producto> listaProductos = listarProductosEntrePrecios();
            //Imprimimos los productos, todos los argumentos
            if (listaProductos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto aux : listaProductos) {
                    System.out.println("* " + aux.getNombre() + " - $" + aux.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //------------------------------------------------------//
    //d) Buscar y listar todos los Portátiles de la tabla producto.
    public void imprimirProductosPortatiles() throws Exception {
        System.out.println("- Los productos portatiles son: ");
        try {
            //Listar porductos
            Collection<Producto> listaProductos = dao.listarProductosPortatiles();
            //Imprimimos los productos, todos los argumentos
            if (listaProductos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto aux : listaProductos) {
                    System.out.println("* " + aux.getNombre() + " - $" + aux.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //------------------------------------------------------//
    //e) Listar el nombre y el precio del producto más barato.
    public void imprimirMasBarato() throws Exception {
        System.out.println("- El producto mas barato disponible es: ");
        try {
            //Listar porductos
            Collection<Producto> listaProductos = dao.listarMasBarato();
            //Imprimimos los productos, todos los argumentos
            if (listaProductos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto aux : listaProductos) {
                    System.out.println("* " + aux.getNombre() + " - $" + aux.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //------------------------------------------------------//
    //f) Ingresar un producto a la base de datos.
    public void crearProducto() throws Exception {

        try {
            Producto p = new Producto();

            System.out.println("Ingrese el nombre del producto:");
            String nombre = leer.next();
            if (nombre.isEmpty()) {
                throw new Exception("El nombre del producto no puede estar vacío");
            }
            p.setNombre(nombre);

            System.out.println("Ingrese el precio del producto:");
            double precio = leer.nextDouble();
            if (precio <= 0) {
                throw new Exception("El precio debe ser mayor a cero");
            }
            p.setPrecio(precio);

            System.out.println("Ingrese el nombre del fabricante:");
            String fab = leer.next();
            if (fab.isEmpty()) {
                throw new Exception("El nombre del fabricante no puede estar vacío");
            }

            Fabricante f = servf.buscarFabricanteNombre(fab);
            if (f == null) {
                servf.crearFabricante(fab);
                f = servf.buscarFabricanteNombre(fab);
            }
            p.setCodigoFabricante(f.getCodigo());
            dao.ingresarProducto(p);

        } catch (InputMismatchException e) {
            throw new Exception("Ingrese un valor numérico válido para el precio");
        } catch (Exception e) {
            throw e;
        }

    }

    //------------------------------------------------------//
    //h) Editar un producto con datos a elección.
      public void imprimirProductos() throws Exception {

        try {
            //Listar porductos
            Collection<Producto> listaPrdocutos = listarProductos();
            //Imprimimos los productos, todos los argumentos
            if (listaPrdocutos.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto aux : listaPrdocutos) {
                    System.out.println(aux);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
      
    public void modificarProducto(Producto p) throws Exception {
        try {
            System.out.println("Ingrese el nuevo nombre del producto:");
            p.setNombre(leer.next());

            System.out.println("Ingrese en nuevo precio del producto: ");
            p.setPrecio(leer.nextDouble());

            System.out.println("Ingrese el nombre del fabricante");
            String fab = leer.next();

            Fabricante f = servf.buscarFabricanteNombre(fab);

            if (f == null) {
                servf.crearFabricante(fab);
                f = servf.buscarFabricanteNombre(fab);
            }
            p.setCodigoFabricante(f.getCodigo());
            //Validamos
            if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }

            if (p.getPrecio() <= 0) {
                throw new Exception("Debe indicar el precio");
            }

            if (p.getCodigoFabricante() < 0) {
                throw new Exception("Debe indicar el id del fabricante");
            }

            dao.modificarProducto(p);
        } catch (Exception e) {
            throw e;
        }

    }

    public void presioneTecla() {
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        String letra = leer.next();
    }

    public void menu() throws Exception {
        try {
            System.out.println("<*************** MENÚ TIENDA ***************>");
            System.out.println("");
            System.out.println("1. Lista el nombre de todos los productos que hay en la tabla producto.");
            System.out.println("2. Lista los nombres y los precios de todos los productos de la tabla producto.");
            System.out.println("3. Listar aquellos productos que su precio esté entre 120 y 202.");
            System.out.println("4. Buscar y listar todos los Portátiles de la tabla producto.");
            System.out.println("5. Listar el nombre y el precio del producto más barato.");
            System.out.println("6. Ingresar un producto a la base de datos.");
            System.out.println("7. Ingresar un fabricante a la base de datos");
            System.out.println("8. Editar un producto con datos a elección.");
            System.out.println("9. Salir");
            System.out.println("");
            System.out.print("Ingrese una opcion: ");
            System.out.println("");
            int op = leer.nextInt();
            switch (op) {
                case 1:
                    imprimirNombreProductos();
                    presioneTecla();
                    menu();
                    break;
                case 2:
                    imprimirNombreYPrecioProductos();
                    presioneTecla();
                    menu();
                    break;
                case 3:
                    imprimirProductosEntrePrecios();
                    presioneTecla();
                    menu();
                    break;
                case 4:
                    imprimirProductosPortatiles();
                    presioneTecla();
                    menu();
                    break;
                case 5:
                    imprimirMasBarato();
                    presioneTecla();
                    menu();
                    break;
                case 6:
                    crearProducto();
                    presioneTecla();
                    menu();
                    break;
                case 7:
                    System.out.println("Ingrese el nombre del fabricante");
                    String nombre = leer.next();
                    servf.crearFabricante(nombre);
                    presioneTecla();
                    menu();
                    break;
                case 8:
                    System.out.println("Elija el producto a modificar de la siguiente lista:");
                    System.out.println("");
                    imprimirProductos();
                    System.out.println("Ingrese el (ID): ");
                    int cod = leer.nextInt();
                    Producto producto = dao.buscarProductoId(cod);

                    if (producto == null) {
                        System.out.println("EL producto no existe. Ingreselo en la opcion 6.");
                    } else {
                        System.out.println("EL producto a modificar es:");
                        System.out.println(producto);
                        modificarProducto(producto);
                        System.out.println("Producto modificado:");
                        System.out.println(producto);
                    }
                    presioneTecla();
                    menu();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Opcion incorrecta!!");
                    presioneTecla();
                    menu();
                    break;
            }

        } catch (Exception e) {
            throw e;
        }
    }

}//class
