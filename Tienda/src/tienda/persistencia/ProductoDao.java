package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;

/**
 * @author scandiani
 */
public final class ProductoDao extends Dao {

    //a) Lista el nombre de todos los productos que hay en la tabla producto:
    //b) Lista los nombres y los precios de todos los productos de la tabla producto.
    //Este metodo va a servir para las 2 consultas.
    public Collection<Producto> listarProductos() throws Exception {
        try {

            String sql = "SELECT *\n"
                    + "FROM producto;";
            consultarBase(sql);

            Producto p = null;
            Collection<Producto> listaProductos = new ArrayList();

            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigoFabricante(resultado.getInt(4));

                listaProductos.add(p);
            }
            desconectarBase();
            return listaProductos;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema.");
        }
    }

    //c) Listar aquellos productos que su precio esté entre 120 y 202.
    public Collection<Producto> listarProductosEntrePrecios() throws Exception {
        try {

            String sql = "SELECT *\n"
                    + "FROM producto\n"
                    + "WHERE precio BETWEEN 120 AND 202\n"
                    + "ORDER BY precio;";
            consultarBase(sql);

            Producto p = null;
            Collection<Producto> listaProductos = new ArrayList();

            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigoFabricante(resultado.getInt(4));

                listaProductos.add(p);
            }
            desconectarBase();
            return listaProductos;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema.");
        }
    }

    //d) Buscar y listar todos los Portátiles de la tabla producto.
    public Collection<Producto> listarProductosPortatiles() throws Exception {
        try {

            String sql = "SELECT *\n"
                    + "FROM producto\n"
                    + "WHERE nombre LIKE '%portatil%';";
            consultarBase(sql);

            Producto p = null;
            Collection<Producto> listaProductos = new ArrayList();

            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigoFabricante(resultado.getInt(4));

                listaProductos.add(p);
            }
            desconectarBase();
            return listaProductos;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema.");
        }
    }

    //e) Listar el nombre y el precio del producto más barato.
    public Collection<Producto> listarMasBarato() throws Exception {
        try {

            String sql = "SELECT * FROM producto\n"
                    + "WHERE precio = (SELECT MIN(precio) FROM producto);";
            consultarBase(sql);

            Producto p = null;
            Collection<Producto> listaProductos = new ArrayList();

            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigoFabricante(resultado.getInt(4));

                listaProductos.add(p);
            }
            desconectarBase();
            return listaProductos;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema.");
        }
    }

    //f) Ingresar un producto a la base de datos.
    public void ingresarProducto(Producto p) throws Exception {
        try {
            if (p == null) {
                throw new Exception("Debe indicar un producto");
            }

            //Insertar en la tabla producto; nombre, precio, codigo. Cuyos valores son:
            String sql = "INSERT INTO producto (nombre, precio, codigo_fabricante)"
                    + "VALUES ( '" + p.getNombre() + "' , '" + p.getPrecio()
                    + "' , '" + p.getCodigoFabricante() + "' );";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        }
    }

    //h) Editar un producto con datos a elección.
    public void modificarProducto(Producto p) throws Exception {
        try {
            if (p == null) {
                throw new Exception("Debe indicar el producto que desea modificar.");
            }

            //Insertar en la tabla fabricante; nombre. Cuyos valores son:
            String sql = "UPDATE producto SET\n"
                    + "nombre='" + p.getNombre() + "', precio='" + p.getPrecio()
                    + "', codigo_fabricante='" + p.getCodigoFabricante() + "'"
                    + "WHERE codigo='" + p.getCodigo() + "';";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    //VER SI SE USA O NO
    public Producto buscarProductoId(int id) throws Exception {

        try {
            String sql = "SELECT * FROM producto "
                    + " WHERE codigo='" + id + "';";

            consultarBase(sql);

            Producto p = null;

            while (resultado.next()) {
                p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodigoFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return p;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

}//class
