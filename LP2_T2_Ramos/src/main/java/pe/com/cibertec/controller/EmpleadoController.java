package pe.com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.com.cibertec.model.EmpleadoEntity;
import pe.com.cibertec.service.EmpleadoService;

@Controller
public class EmpleadoController {
	
	@Autowired	
	private EmpleadoService empleadoService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<EmpleadoEntity> listaEmpleado = empleadoService.buscarTodosEmpleados();
		model.addAttribute("lst_empleado", listaEmpleado);
		return "Listar_empleado";
	}
	
    @GetMapping("/detalle_empleado/{id}")
    public String detalleEmpleado(@PathVariable String id, Model model) {
        EmpleadoEntity empleado = empleadoService.buscarEmpleadoPorId(id);
        model.addAttribute("empleado", empleado);
        return "detalle_empleado";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteEmpleado(@PathVariable("id") String id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/";
    }
    
    @GetMapping("/registrar_empleado")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("empleado", new EmpleadoEntity());
        return "registrar_empleado";
    }
    
    @PostMapping("/registrar_empleado")
    public String registrarEmpleado(@ModelAttribute("empleado") EmpleadoEntity empleado, Model model) {
    	if (empleadoService.buscarPorCorreo(empleado.getCorreo()) != null) {
    		model.addAttribute("errorMessage", "El correo ya existe, usa otro");
    		model.addAttribute("empleado", empleado);
    		return "registrar_empleado";
    	}
    	
    	empleadoService.crearEmpleado(empleado);
    	return "redirect:/";
    }
    
    @GetMapping("/editar_empleado/{id}")
    public String mostrarEditarEmpleado(@PathVariable("id") String id, Model model) {
    	EmpleadoEntity empleadoEncontrado = empleadoService.buscarEmpleadoPorId(id);
    	model.addAttribute("empleado", empleadoEncontrado);
    	return "editar_empleado";
    }
}
