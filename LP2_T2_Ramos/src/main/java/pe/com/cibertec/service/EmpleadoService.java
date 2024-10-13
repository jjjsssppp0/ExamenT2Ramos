package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.EmpleadoEntity;

public interface EmpleadoService {
	
	List<EmpleadoEntity>buscarTodosEmpleados();
	EmpleadoEntity buscarEmpleadoPorId(String id);
	void crearEmpleado(EmpleadoEntity empleadoEntity);
	void actualizarEmpleado(String id, EmpleadoEntity empleadoEntity);
	void eliminarEmpleado(String id);
	EmpleadoEntity buscarPorCorreo(String correo);
}
