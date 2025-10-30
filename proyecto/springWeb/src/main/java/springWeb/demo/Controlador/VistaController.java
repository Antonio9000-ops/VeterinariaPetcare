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

    // --- MÉTODO NUEVO QUE SOLUCIONA EL PROBLEMA ---
    @GetMapping("/gestion-citas")
    public String mostrarPaginaGestionCitas() {
        return "gestion-citas";
    }
}