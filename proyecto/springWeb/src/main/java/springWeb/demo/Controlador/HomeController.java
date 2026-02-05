package springWeb.demo.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

        @GetMapping("/about")
        public String about() {
            return "about"; // about.html dentro de templates
        }
        @GetMapping("/contact")
        public String contact() {
            // Muestra la página contact.html ya existente
            return "contact";
        }
        @GetMapping("/privacy")
        public String privacy() {
            return "privacy"; // Carga privacy.html
        }
        @GetMapping("/terms")
        public String terms() {
            return "terms"; // Carga privacy.html
        }
}
