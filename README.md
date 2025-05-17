# Web App Stellar Odissey
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white) ![CSS](https://img.shields.io/badge/CSS-563d7c?&style=flat&logo=css3&logoColor=white) ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white) ![Bootstrap](https://img.shields.io/badge/Bootstrap-7952B3?style=flat&logo=bootstrap&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)  

Aplicación web con CRUD completo para gestionar diferentes elementos de diversas páginas. Está desarrollada con 
**Java11**, **TomCat9** y **MariaDB** como base de datos. El frontend utiliza estilos propios de **Bootstrap** y varios 
estilos personalizados en un fichero **CSS** con estilos personalizados. 

---

## Características
- Crear, leer, actualizar y eliminar Astronautas, Planetas y Misiones
- Visualizar Astronautas fácilmente
- Interfaz web amigable
- Datos almacenados en base de datos MariaDB

## Tecnologías utilizadas 

- Java 11
- Servlet 4.0 & JSP
- MariaDB
- Apache Tomcat9
- Bootstrap

## Estructura del proyecto 
```bash
Directory structure:
└── specialteam01-javaspacialteam/
    ├── README.md
    ├── Consulta.sql
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   ├── dao/
            │   ├── model/
            │   ├── servlet/
            │   └── utils/
            └── webapp/
                ├── css/
                ├── images/
                ├── includes/
                ├── jsp/
                └── WEB-INF/
```

## Requisitos previos

### Java 
Tener instalada la versión 11 de Java

### Tomcat9
Debes modificar los siguientes ficheros. 

**Conf/server.xml**
```bash
  <Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```

**conf/tomcat-users.xml**
```bash
  <role rolename="manager-gui"/>
  <user username="tomcat" password="tomcat" roles="manager-gui, manager-script"/>
```
### Apache Maven 3.9.9
Debes tener instalada la version 3.9.9 de Apache Maven para que el proyecto funcione correctamente. 


## Instalación
### 1. Clonar el repositorio
```bash
git clone https://github.com/SpecialTeam01/JavaSpacialTeam.git
cd javaspacialteam
```
> [!TIP]
> Antes de ejecuar el proyecto, asegurate de tener configurada la base de datos, los datos de conexion están configurados
> en el backend. 

### 2. Desplieuge de la aplicación
Para ejecutar el proyecto, puedes ejecutar estos comando desde una consola CMD o GitBash. 
```bash
mvn tomcat7:deploy      # Desplegar la aplicación 
mvn tomcat7:undeploy    # Eliminar la aplicación
```
> [!CAUTION]
> Si tienes que modificar el fichero pom.xml, debes sincronizarlo de forma manual
> 
> ![image](https://github.com/user-attachments/assets/c0247366-5785-4be3-b33f-0682b386499b)

  
