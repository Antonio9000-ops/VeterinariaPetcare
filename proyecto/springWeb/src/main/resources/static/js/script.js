function parseJwt(token) {
    try { return JSON.parse(atob(token.split('.')[1])); } catch (e) { return null; }
}

function setupNavbar(token) {
    const navLinksMenu = document.getElementById('nav-links-menu');
    if (!navLinksMenu) return;

    const userData = token ? parseJwt(token) : null;
    const navItemClass = 'nav-item nav-link';
    
    let menuHTML = `<a href="/" class="${navItemClass}">Inicio</a>`;

    if (userData) {
        const userRole = userData.role;
        if (userRole === 'VETERINARIO') {
            menuHTML += `<a href="/gestion-citas" class="${navItemClass}">Gestión Citas</a>`;
            menuHTML += `<a href="/agenda" class="${navItemClass}">Mi Agenda</a>`;
            menuHTML += `<a href="/gestion-servicios" class="${navItemClass}">Gestion de Servicios</a>`;
        
        } else if (userRole === 'DUEÑO') {
            menuHTML += `<a href="/mascotas" class="${navItemClass}">Mis Mascotas</a>`;
            menuHTML += `<a href="/pagos" class="${navItemClass}">Mis Pagos</a>`;
        
        } else {
            
        }
        menuHTML += `<a href="/contact" class="${navItemClass}">Contacto de emergencia</a>`;
        menuHTML += `<a href="#" id="logout-btn-menu" class="${navItemClass}">Cerrar Sesión</a>`;
    } else {
        menuHTML += `<a href="/contact" class="${navItemClass}">Contacto de emergencia</a>
                     <a href="/login" class="${navItemClass}">Iniciar Sesión</a>`;
    }
    navLinksMenu.innerHTML = menuHTML;
}

function setupBienvenidaPersonalizada(token) {
    const bienvenidaSeccion = document.getElementById('bienvenida-seccion');
    const tituloPrincipal = document.getElementById('titulo-principal');
    
    if (!bienvenidaSeccion || !token) return;

    const userData = parseJwt(token);
    if (!userData) return;

    const userRole = userData.role;
    const userName = userData.nombre || 'Usuario';

    const mensajeHeader = document.getElementById('mensaje-bienvenida-header');
    const mensajeParrafo = bienvenidaSeccion.querySelector('p');

    // 1. Ocultar el título genérico
    if (tituloPrincipal) tituloPrincipal.style.display = 'none';

    // 2. Mostrar la sección quitando la clase d-none de Bootstrap
    bienvenidaSeccion.classList.remove('d-none');

    // 3. Personalizar mensajes
    if (mensajeHeader) {
        mensajeHeader.textContent = `¡Bienvenido de nuevo, ${userName}!`;
    }
    
    if (mensajeParrafo) {
        if (userRole === 'VETERINARIO' || userRole === 'ASISTENTE') {
            mensajeParrafo.textContent = 'Accede a la gestión de citas y tu agenda desde el menú superior.';
        } else {
            mensajeParrafo.textContent = 'Aquí puedes revisar el estado de tus mascotas y realizar pagos pendientes.';
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('jwt_token');

    setupNavbar(token);
    setupBienvenidaPersonalizada(token);

    // Logout
    document.addEventListener('click', (e) => {
        if (e.target && e.target.id === 'logout-btn-menu') {
            localStorage.removeItem('jwt_token');
            window.location.href = '/';
        }
    });

    // Ocultar spinner si existe
    const spinner = document.getElementById('spinner');
    if (spinner) {
        setTimeout(() => spinner.classList.remove('show'), 1);
    }

    if (typeof WOW !== 'undefined') new WOW().init();
});