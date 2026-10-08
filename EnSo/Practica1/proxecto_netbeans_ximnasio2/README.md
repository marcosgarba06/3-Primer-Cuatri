# Proxecto BD II Ximnasio Grupo 2

## 👥 Miembros del grupo de implementación
*Marcos García Blanco: marcosgarba06*

*Elisa García García: ElisaGX2*

*Lucas García Vilariño: pLucas1234*


## 📂 Estructura del proyecto

El proyecto está dividido en los scripts de la base de datos y el código fuente de la aplicación, el cual se encuentra dentro del directorio de implementación.

```text
📁 Proyecto
├── 📁 implementacion/
│   └── 📁 src/
│       ├── 📁 aplicacion/   → Lógica (GestionXxx.java) + Entidades + FachadaAplicacion.java     
│       ├── 📁 baseDatos/    → DAOs (DAOXxx.java) + FachadaBaseDatos.java + AbstractDAO.java
│       └── 📁 gui/          → Ventanas Swing (VXxx.java) + FachadaGui.java
│
└── 📁 scriptsSQL/
    ├── 📄 creacionXimnasio.sql    → Script de creación de la base de datos con todas sus tablas
    ├── 📄 datosinsercion.sql      → Script para insertar datos en las tablas
    └── 📄 eliminacionXimnasio.sql → Script para eliminar las tablas de la base de datos
``` 
