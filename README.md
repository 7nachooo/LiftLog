
# 🏋️ LiftLog

**Aplicación móvil multiplataforma para la gestión integral del bienestar físico y mental.**  
Proyecto Fin de Grado — Desarrollo de Aplicaciones Multiplataforma  
Desarrollado por Ignacio Arcalá · CESUR Madrid · Curso 2024–2025

---

## 📱 ¿Qué es LiftLog?

LiftLog es una app móvil que permite registrar, analizar y visualizar el progreso del usuario en cuatro áreas clave:

- 🏃 Entrenamientos personalizados
- 🍽️ Nutrición y recetas saludables
- 💤 Sueño y descanso
- 👥 Comunidad para compartir logros y motivarse

Todo desde una única plataforma accesible y fácil de usar.

---

## 🛠 Tecnologías utilizadas

| Categoría            | Tecnologías                             |
|----------------------|------------------------------------------|
| Lenguaje principal   | Kotlin                                   |
| Interfaz de usuario  | Jetpack Compose                          |
| Autenticación        | Firebase Authentication (email/Google)   |
| Base de datos        | Firestore (Firebase)                     |
| Estado y navegación  | ViewModel + Navigation Compose           |
| Librerías extra      | Vico Compose · Coil · ThreeTenABP        |
| IDE                  | Android Studio Flamingo                  |

---

## 🚀 Instrucciones para ejecutar el proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/7nachooo/LiftLog.git
   ```

2. Abre el proyecto en Android Studio.

3. Configura Firebase:
   - Accede a [Firebase Console](https://console.firebase.google.com/)
   - Crea un proyecto nuevo o conecta uno existente.
   - Descarga el archivo `google-services.json` y colócalo en la carpeta `/app`.

4. Sincroniza Gradle:
   - `File > Sync Project with Gradle Files`

5. Ejecuta la app:
   - Desde emulador o dispositivo físico con depuración USB activada.

---

## 🔐 Usuario de prueba

Puedes iniciar sesión con el siguiente usuario de test:

```
Email: nacho3@liftlog.com  
Contraseña: 170398
```

---

## 📸 Capturas de pantalla

Puedes ver ejemplos visuales del diseño y flujo de la aplicación en el apartado de **Anexos** del documento entregado.

---

## 📁 Estructura del proyecto

```
LiftLog/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/com/ignacio/liftlog/
│       │   │   ├── ui/screens/
│       │   │   ├── ui/components/
│       │   │   ├── models/
│       │   │   └── utils/
│       │   └── res/
├── README.md
└── google-services.json (no incluido en repo por seguridad)
```

---

## 📌 Nota final

Este proyecto ha sido desarrollado como parte del **Trabajo de Fin de Grado** del ciclo **Desarrollo de Aplicaciones Multiplataforma** en **CESUR Madrid**.  
Se agradece cualquier feedback o sugerencia a través de GitHub.

---

© 2025 Ignacio Arcalá · Todos los derechos reservados
