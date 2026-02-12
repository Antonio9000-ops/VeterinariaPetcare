# Revisión técnica integral del proyecto VeterinariaPetcare

## 1) Hallazgos de revisión (lógica, seguridad y mantenimiento)

### 1.1 Bloqueos de acceso por seguridad (corregido)

**Problema detectado**
- Las rutas `/privacy` y `/terms` sí existen en el controlador web, pero no estaban incluidas en las rutas públicas de `SecurityConfig`.
- Resultado: un usuario no autenticado podía encontrar bloqueo (redirección/401 según flujo) al intentar abrir esas páginas desde el footer.

**Corrección aplicada**
- Se agregaron explícitamente las rutas:
  - `/privacy`, `/privacy.html`
  - `/terms`, `/terms.html`
- Esto permite que esas páginas informativas sean visibles sin iniciar sesión.

### 1.2 Posibles riesgos y puntos a fortalecer

1. **CORS demasiado rígido para despliegues**
   - Actualmente solo permite orígenes de desarrollo (`localhost` y `127.0.0.1`).
   - Recomendación: externalizar a `application.properties` por ambiente (dev/staging/prod).

2. **Uso de excepciones genéricas (`RuntimeException`)**
   - En servicios de negocio hay varios `RuntimeException`/`IllegalStateException` sin tipificación específica.
   - Recomendación: crear excepciones de dominio (`RecursoNoEncontradoException`, `ReglaNegocioException`) y manejarlas con `@ControllerAdvice` para respuestas HTTP consistentes.

3. **Permisos amplios en algunas APIs**
   - `requestMatchers("/api/usuarios/**").permitAll()` puede exponer endpoints más allá de registro/login.
   - Recomendación: separar endpoints públicos de autenticación (`/auth/**`) y proteger CRUD de usuario por rol.

4. **Validación de entrada mejorable**
   - DTOs y endpoints se beneficiarían de validaciones declarativas (`@NotNull`, `@Email`, `@Size`) + `@Valid` en controladores.

### 1.3 Variables no usadas y limpieza

Durante la revisión no se encontraron **atributos `private final` claramente no usados** en clases Java.

Aun así, sí hay oportunidades de limpieza:
- Bloques condicionales vacíos en frontend (por ejemplo, sección de rol `ASISTENTE` en construcción de menú), que no rompe la app pero conviene completar o eliminar para claridad.
- Duplicidad potencial de responsabilidades JS (`main.js` + `script.js`) para comportamiento de UI.

---

## 2) Optimización de estructura propuesta

La estructura actual funciona, pero puede simplificarse y escalar mejor si se organiza por **módulos funcionales** (feature-first) en vez de agrupar todo por tipo técnico en un único paquete `domain`.

## 3) Estructura objetivo recomendada (gráfico)

```text
VeterinariaPetcare/
├─ docs/
│  ├─ arquitectura/
│  │  ├─ adr-0001-modularizacion.md
│  │  └─ diagrama-modulos.md
│  └─ revision_arquitectura_y_mejoras.md
├─ proyecto/
│  └─ springWeb/
│     ├─ src/main/java/springWeb/demo/
│     │  ├─ config/
│     │  │  ├─ SecurityConfig.java
│     │  │  ├─ CorsConfig.java
│     │  │  └─ OpenApiConfig.java
│     │  ├─ shared/
│     │  │  ├─ exception/
│     │  │  ├─ mapper/
│     │  │  └─ util/
│     │  ├─ auth/
│     │  │  ├─ controller/
│     │  │  ├─ service/
│     │  │  ├─ jwt/
│     │  │  └─ dto/
│     │  ├─ mascotas/
│     │  │  ├─ controller/
│     │  │  ├─ service/
│     │  │  ├─ repository/
│     │  │  ├─ model/
│     │  │  ├─ dto/
│     │  │  └─ mapper/
│     │  ├─ citas/
│     │  │  ├─ controller/
│     │  │  ├─ service/
│     │  │  ├─ repository/
│     │  │  ├─ model/
│     │  │  └─ dto/
│     │  ├─ historias/
│     │  ├─ vacunas/
│     │  ├─ tratamientos/
│     │  ├─ recetas/
│     │  ├─ pagos/
│     │  └─ facturacion/
│     ├─ src/main/resources/
│     │  ├─ templates/
│     │  │  ├─ fragments/
│     │  │  ├─ public/
│     │  │  ├─ auth/
│     │  │  ├─ mascotas/
│     │  │  ├─ citas/
│     │  │  └─ facturacion/
│     │  ├─ static/
│     │  │  ├─ css/
│     │  │  ├─ js/
│     │  │  ├─ img/
│     │  │  └─ vendor/
│     │  ├─ application.yml
│     │  ├─ application-dev.yml
│     │  └─ application-prod.yml
│     └─ src/test/
│        ├─ unit/
│        ├─ integration/
│        └─ e2e/
└─ README.md
```

---

## 4) Plan de mejoras priorizado

### Fase 1 (rápida, alto impacto)
1. Ajustar seguridad de rutas públicas/privadas por caso de uso.
2. Incorporar `@ControllerAdvice` + excepciones de dominio.
3. Validaciones en DTOs con Bean Validation.
4. Unificar scripts frontend para evitar solapamientos.

### Fase 2 (escalabilidad)
1. Reorganización por módulos (`mascotas`, `citas`, `facturacion`, etc.).
2. Separar capa web (vistas Thymeleaf) de capa API REST con convenciones claras.
3. Añadir pruebas unitarias de servicios y pruebas de integración de seguridad.

### Fase 3 (operación)
1. Perfilado por ambientes (`application-*.yml`) para CORS, JWT secret, DB, logs.
2. Pipeline CI con:
   - análisis estático,
   - pruebas,
   - cobertura mínima,
   - escaneo de dependencias.

---

## 5) Resultado de esta intervención

- Se eliminó el bloqueo de acceso de páginas informativas públicas (`privacy` y `terms`) mediante configuración de seguridad.
- Se documentó una propuesta integral de optimización estructural para facilitar mantenimiento, escalado y seguridad.

## 6) Cambios implementados en esta rama (`Salvador/b`)

### Seguridad y acceso
- ✅ Se mantuvieron públicas las páginas legales `/privacy` y `/terms`.
- ✅ Se endureció seguridad de `/api/usuarios`:
  - Público solo `POST /api/usuarios/registro` y `POST /api/usuarios/login`.
  - Consulta por id/correo permitida por roles autenticados.
  - Listado limitado a `VETERINARIO` y `ASISTENTE`.
  - Eliminación limitada a `VETERINARIO`.

### Manejo de errores estandarizado
- ✅ Se implementaron excepciones de dominio:
  - `ResourceNotFoundException`
  - `BusinessRuleException`
  - `UnauthorizedException`
- ✅ Se creó `GlobalExceptionHandler` con `@RestControllerAdvice` para mapear errores a respuestas HTTP consistentes.

### Validación de datos de entrada
- ✅ Se agregaron validaciones de Bean Validation en:
  - `UsuarioDTO`
  - `RegisterRequest`
  - `AuthRequest`
  - `FacturaRequestDTO`
- ✅ Se aplicó `@Valid` en endpoints clave (`AuthController`, `UsuarioController`, `FacturacionController`).

### Correcciones lógicas
- ✅ Registro de usuario con contraseña codificada (`PasswordEncoder`) en `UsuarioServiceImpl`.
- ✅ Verificación de login usando `passwordEncoder.matches(...)` en `UsuarioServiceImpl`.
- ✅ Validación de correo duplicado al registrar en `UsuarioServiceImpl` y `AuthService`.
- ✅ Reemplazo de `RuntimeException` genéricas por excepciones de dominio en facturación y autenticación.

### Limpieza de código
- ✅ Eliminado bloque condicional vacío para rol `ASISTENTE` en `static/js/script.js`.

## 7) Siguiente paso recomendado para completar la optimización estructural

Para no romper rutas ni imports en un solo cambio grande, se recomienda migración en 3 PRs:
1. **PR-A (base de arquitectura):** crear nuevos paquetes por módulo y mover excepciones/shared.
2. **PR-B (migración funcional):** mover `mascotas`, `citas`, `facturacion` con pruebas de regresión.
3. **PR-C (recursos y frontend):** reubicar templates/assets por feature y consolidar JS.
