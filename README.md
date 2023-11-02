# Fragments Simple Example
  
  
Repositorio de código que muestra simples interacciones (navegación, safeArgs, sharedPreferences, room, entre otras) con fragmentos.
  
Este es un proyecto en construcción, basado inicialmente del siguiente archivo de <a href="https://www.figma.com/proto/aLbXUI5R5g2DwjHh4eLjRo/Navegaci%C3%B3n?type=design&node-id=1-2&t=ymAzHLfHUGJL1jEQ-0&scaling=scale-down&page-id=0%3A1&starting-point-node-id=1%3A2" target="_blank">diseño en Figma</a>, construido como actividad introductoria para entender el principio de navegación en Android.

Actualmente presenta las ramas:

- **main:** rama principal con el proyecto actualizado completamente.
- **init:** rama inicial
  - Navegación entre fragmentos.
  - Paso de valores con ayuda de safeArgs.
  - Configuración básica de un archivo sharedPreferences.
  - Material Design Components para la UI (MaterialToolbar, BottomNavigationView, TextView, Buttons, TextInputLayout - TextInputEditText, entre otros)
- **feature_room:** característica de arquitectura Room añadida para interactuar con bases de datos locales.
  - Mejora en la experiencia de usuario controlando el ciclo de vida de los fragmentos.
  - Creación de Entidades, interfaces de conexion con las entidades (DAO) y bases de datos.
  - Manejo de CRUD básico con corrutinas.
- **feature_list_of_users:** característica agregada para mostrar una lista de usuarios haciendo uso de un RecyclerView y el patrón Adapter.
  - Se añade una lista en el fragmento Principal con los usuarios registrados en la base de datos local.
  - Se mejoran algunos detalles en la documentación interna de cada archivo.
  - Se añaden (o remplazan) mensajes básicos de confirmación por medio de simples Snackbars.
  
Saludos.
  
Continua codificando  :sunglasses:
