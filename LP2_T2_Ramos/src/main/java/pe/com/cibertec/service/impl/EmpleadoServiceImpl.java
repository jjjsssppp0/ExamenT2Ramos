package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cibertec.model.EmpleadoEntity;
import pe.com.cibertec.repository.EmpleadoRepository;
import pe.com.cibertec.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Override
    public List<EmpleadoEntity> buscarTodosEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public EmpleadoEntity buscarEmpleadoPorId(String id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void crearEmpleado(EmpleadoEntity empleadoEntity) {
        empleadoRepository.save(empleadoEntity);
    }

    @Override
    public void actualizarEmpleado(String id, EmpleadoEntity empleadoNuevo) {
        EmpleadoEntity empleadoEncontrado = buscarEmpleadoPorId(id);
        
        if (empleadoEncontrado == null) {
            throw new RuntimeException("Empleado no encontrado");
        }
        
        try {
            empleadoEncontrado.setNombreEmpleado(empleadoNuevo.getNombreEmpleado());
            empleadoEncontrado.setApellidoEmpleado(empleadoNuevo.getApellidoEmpleado());
            empleadoEncontrado.setFechaNacimiento(empleadoNuevo.getFechaNacimiento());
            empleadoEncontrado.setDireccion(empleadoNuevo.getDireccion());
            empleadoEncontrado.setCorreo(empleadoNuevo.getCorreo());
            
            empleadoRepository.save(empleadoEncontrado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    @Override
    public void eliminarEmpleado(String id) {
        EmpleadoEntity empleadoEncontrado = buscarEmpleadoPorId(id);
        empleadoRepository.delete(empleadoEncontrado);
    }

    @Override
    public EmpleadoEntity buscarPorCorreo(String correo) {
        return empleadoRepository.findByCorreo(correo);
    }
}
