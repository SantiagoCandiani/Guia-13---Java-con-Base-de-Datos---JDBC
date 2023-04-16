package tienda.servicios;

import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDao;

/**
 * @author scandiani
 */
public class ServicioFabricante {

    private FabricanteDao dao;
    private Scanner leer;

    public ServicioFabricante() {
        this.leer = new Scanner(System.in).useDelimiter("\n");
        this.dao = new FabricanteDao();
    }
    //g) Ingresar un fabricante a la base de datos
    public void crearFabricante(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre del fabricante no puede estar vacío o nulo");
            }

            Fabricante f = dao.buscarFabricanteNombre(nombre);
            if (f != null) {
                throw new Exception("Ya existe un fabricante con ese nombre en la base de datos");
            }

            f = new Fabricante();
            f.setNombre(nombre);
            dao.ingresarFabricante(f);
        } catch (Exception e) {
            throw e;
        }
    }

    public void mostrarFabricantes() throws Exception {
        try {
            //Listar porductos
            Collection<Fabricante> listaFabricantes = dao.listarFabricantes();
            //Imprimimos los fabricantes, todos los argumentos
            if (listaFabricantes.isEmpty()) {
                throw new Exception("No existen fabricantes para mostrar");
            } else {
                for (Fabricante aux : listaFabricantes) {
                    System.out.println(aux);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricanteNombre(String nombre) throws Exception {
        try {
            Fabricante f = dao.buscarFabricanteNombre(nombre);
            return f;

        } catch (Exception e) {
            throw e;
        }
    }

}//class
