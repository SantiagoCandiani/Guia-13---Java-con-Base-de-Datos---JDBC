package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;

/**
 * @author scandiani
 */
public final class FabricanteDao extends Dao {

    //g) Ingresar un fabricante a la base de datos
    public void ingresarFabricante(Fabricante f) throws Exception {
        try {
            if (f == null) {
                throw new Exception("Debe indicar un fabricante.");
            }

            //Insertar en la tabla fabricante; nombre. Cuyos valores son:
            String sql = "INSERT INTO fabricante (nombre)"
                    + "VALUES ( '" + f.getNombre() + "' );";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Fabricante> listarFabricantes() throws Exception {
        try {

            String sql = "SELECT *\n"
                    + "FROM fabricante;";
            consultarBase(sql);

            Fabricante f = null;
            Collection<Fabricante> listaFabricantes = new ArrayList();

            while (resultado.next()) {
                f = new Fabricante();
                f.setCodigo(resultado.getInt(1));
                f.setNombre(resultado.getString(2));
                listaFabricantes.add(f);
            }
            desconectarBase();
            return listaFabricantes;

        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema.");
        }
    }

    public Fabricante buscarFabricanteNombre(String nombre) throws Exception {
        try {

            String sql = "SELECT *\n"
                    + "FROM fabricante\n"
                    + "WHERE nombre LIKE '" + nombre + "';";
            consultarBase(sql);

            Fabricante f = null;

            while (resultado.next()) {
                f = new Fabricante();
                f.setCodigo(resultado.getInt(1));
                f.setNombre(resultado.getString(2));
            }
            desconectarBase();
            return f;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

}//class
