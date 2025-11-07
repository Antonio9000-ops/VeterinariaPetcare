package springWeb.demo.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class VistaController {

    @GetMapping("/")
    public String mostrarPaginaDeInicio() {
        return "inicio";
    }

    @GetMapping({"/login", "/login.html"})
    public String mostrarPaginaLogin() {
        return "login";
    }

    @GetMapping({"/register", "/register.html"})
    public String mostrarPaginaRegister() {
        return "register";
    }

    @GetMapping("/mascotas")
    public String mostrarPaginaMascotas() {
        return "mascotas";
    }

    @GetMapping("/mascota-detalle")
    public String mostrarPaginaMascotaDetalle() {
        return "mascota-detalle";
    }

    @GetMapping("/mascota-formulario")
    public String mostrarPaginaMascotaFormulario() {
        return "mascota-formulario";
    }

    @GetMapping("/cita-formulario")
    public String mostrarPaginaCitaFormulario() {
        return "cita-formulario";
    }

    @GetMapping("/agenda")
    public String mostrarPaginaAgenda() {
        return "agenda";
    }

   @GetMapping("/historia-formulario")
public String mostrarPaginaHistoriaFormulario(@RequestParam("mascotaId") Long mascotaId, Model model) {
    
    model.addAttribute("mascotaId", mascotaId);
    return "historia-formulario";
}

    @GetMapping("/vacuna-formulario")
public String mostrarPaginaVacunaFormulario(@RequestParam("mascotaId") Long mascotaId, Model model) {
    
    model.addAttribute("mascotaId", mascotaId);
    return "vacuna-formulario";
}

   
    @GetMapping("/gestion-citas")
    public String mostrarPaginaGestionCitas() {
        return "gestion-citas";
    }
     @GetMapping("/tratamiento-formulario")
    public String mostrarPaginaTratamientoFormulario(@RequestParam("mascotaId") Long mascotaId, Model model) {
        model.addAttribute("mascotaId", mascotaId);
        return "tratamiento-formulario";
    }

    @GetMapping("/receta-formulario")
    public String mostrarPaginaRecetaFormulario(@RequestParam("mascotaId") Long mascotaId, Model model) {
        model.addAttribute("mascotaId", mascotaId);
        return "receta-formulario";
    }
    @GetMapping("/pagos")
    public String mostrarPaginaPagos() {
    return "pagos"; 
    }
    @GetMapping("/gestion-servicios")
    public String mostrarPaginaGestionServicios() {
        return "gestion-servicios";
    }
}