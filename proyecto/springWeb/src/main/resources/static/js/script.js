
function parseJwt(token) {
    try {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
        return JSON.parse(jsonPayload);
    } catch (e) {
        console.error("Error parsing JWT:", e);
        return null;
    }
}

function setupNavbar(token) {
    const navLinksMenu = document.getElementById('nav-links-menu');
    if (!navLinksMenu) {
        console.warn("Elemento #nav-links-menu no encontrado");
        return;
    }

    const userData = token ? parseJwt(token) : null;
    const navItemClass = 'nav-item nav-link';

    let menuHTML = '';

    if (userData) {
        const userRole = userData.role;
        const currentPath = window.location.pathname;

        menuHTML += `<a href="/" class="${navItemClass} ${currentPath === '/' ? 'active' : ''}">Inicio</a>`;

        if (userRole === 'VETERINARIO' || userRole === 'ASISTENTE') {
            menuHTML += `<a href="/gestion-citas" class="${navItemClass} ${currentPath.includes('gestion-citas') ? 'active' : ''}">Gestión Citas</a>`;
            menuHTML += `<a href="/agenda" class="${navItemClass} ${currentPath.includes('agenda') ? 'active' : ''}">Mi Agenda</a>`;
            menuHTML += `<a href="/gestion-servicios" class="${navItemClass} ${currentPath.includes('gestion-servicios') ? 'active' : ''}">Gestión de Servicios</a>`;
        } else {
            menuHTML += `<a href="/mascotas" class="${navItemClass} ${currentPath.includes('mascotas') ? 'active' : ''}">Mis Mascotas</a>`;
            menuHTML += `<a href="/pagos" class="${navItemClass} ${currentPath.includes('pagos') ? 'active' : ''}">Mis Pagos</a>`;
        }

        menuHTML += `<a href="/contact" class="${navItemClass} ${currentPath.includes('contact') ? 'active' : ''}">Contacto de emergencia</a>`;
        menuHTML += `<a href="#" id="logout-btn-menu" class="${navItemClass}">Cerrar Sesión</a>`;
    } else {
        const currentPath = window.location.pathname;
        menuHTML += `<a href="/" class="${navItemClass} ${currentPath === '/' ? 'active' : ''}">Inicio</a>`;
        menuHTML += `<a href="/contact" class="${navItemClass} ${currentPath.includes('contact') ? 'active' : ''}">Contacto de emergencia</a>`;

        if (!currentPath.includes('login') && !currentPath.includes('register')) {
            menuHTML += `<a href="/login" class="${navItemClass} ${currentPath.includes('login') ? 'active' : ''}">Iniciar Sesión</a>`;
        }
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

    if (tituloPrincipal) tituloPrincipal.style.display = 'none';
    bienvenidaSeccion.classList.remove('d-none');

    if (mensajeHeader) {
        mensajeHeader.textContent = `¡Bienvenido de nuevo, ${userName}!`;
    }

    if (mensajeParrafo) {
        mensajeParrafo.textContent = (userRole === 'VETERINARIO' || userRole === 'ASISTENTE')
            ? 'Accede a la gestión de citas y tu agenda desde el menú superior.'
            : 'Aquí puedes revisar el estado de tus mascotas y realizar pagos pendientes.';
    }
}

// =============================================
// FUNCIÓN PRINCIPAL (se ejecuta en TODAS las páginas)
// =============================================
function initializePage() {
    const token = localStorage.getItem('jwt_token');

    // 1. Configurar navbar
    setupNavbar(token);

    // 2. Configurar bienvenida personalizada (solo en inicio)
    if (window.location.pathname === '/') {
        setupBienvenidaPersonalizada(token);
    }

    // 3. Manejar logout
    document.addEventListener('click', (e) => {
        if (e.target && e.target.id === 'logout-btn-menu') {
            e.preventDefault();
            localStorage.removeItem('jwt_token');
            window.location.href = '/';
        }
    });

    // 4. Ocultar spinner
    const spinner = document.getElementById('spinner');
    if (spinner) {
        setTimeout(() => spinner.classList.remove('show'), 500);
    }

    // 5. Inicializar WOW.js si existe
    if (typeof WOW !== 'undefined') {
        try {
            new WOW().init();
        } catch (e) {
            console.warn("Error inicializando WOW.js:", e);
        }
    }
}

// =============================================
// EJECUCIÓN SEGURA
// =============================================
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initializePage);
} else {
    initializePage();
}
