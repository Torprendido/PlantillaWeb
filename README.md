# PlantillaWeb

Es un una plantilla básica para implementar una aplicación java web utilizando los frameworks de Spring y hibernate.

### Herramientas y frameworks utilizados:

* java 1.7
* mysql
* Eclipse Neon
* Spring Tool Suit
* Hibernate Tools
* Plujin de Integración de Maven para Eclipse (m2e)
* Spring 4
* Hibernate 4
* Apache Tomcat 7
* Otras librerías (ver pom.xml)

### Implementaciones.

* Habilita el modelo de programación de Spring MVC @Controller
* Se implementa el uso de la anotacion @Transactional, muy útil para consentrarse sólo en el proceso de negocio.
* Se implementa seguridad con el listener HttpSessionEventPublisher para manejar los eventos de session, y también se hace uso del filtro DelegatingFilterProxy para gestionar el acceso y los permisos de usuario.
* Los objetos POJO fueron generados con la herramienta Hibernate Tools.

### Recursos.

Script para crear los usuarios y roles de la aplicacion. Se puede encontrar en la raíz del proyecto.

# Importar proyecto Eclipce.

Se puede importar este proyecto en Eclipse si te diriges a la pestaña "File > Import" del IDE y después buscar la ruta donde hayas descomprimido o clonado el proyecto.