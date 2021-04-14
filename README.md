# Ponydesk
Sistema de control de inventarios para una veterinaria rural
# Modulos del sistema
### Pricipales
- Veterinarios
- Proveedores
- Inventario General
- Registro de salidas
- Realizar pedido
- Registro de entradas o compras
### **Historial y consultas**
- Historial de salidas
- Historial de entradas o compras
- Pedidos
### Extras y configuraciones
- Información de la empresa
- Crear una empresa
- Splash Screen
# Cómo usar
Para utilizar la aplicación necesitaremos de MySQL Server o MariaDB Server, y el IDE Apache Netbeans (También puedes usar Netbeans). 
## Instalar base de datos
Primero debes crear la base de datos en MySQL. Para ello, dentro de la carpeta `Base de datos/`, está el archivo basededatos.sql con el cual vamos a instalar la base de datos. Una vez tengamos nuestro archivo .sql, abrimos una terminal o cmd y ejecutamos el siguiente comando:
```shell
mysql -u root < basededatos.sql
```
## Cambiar los parámetros de la base de datos 
Este paso solo es necesario si tu usuario root tiene una contraseña o si utilizas un servidor de MySQL externo.  
Una vez tengamos creada nuestra base de datos, tenemos que especificar los parámetros de nuestra base de datos en el código de la aplicación. Para ello vamos a modificar 2 archivos:
1. El archivo persistence.xml que está en el directorio `src/main/resources/META-INF/`
2. El archivo ConexionDB.java que está en el directorio `src/main/java/ponyvet/modelo/dao/`
## Ejecutar aplicación en Apache Netbeans
Simplemente exportamos nuestra aplicación desde Apache Netbeans y ejecutamos el archivo `src/main/java/ponyvet/gui/principal/SplashScreen.java`, el cual contiene la clase principal.
# Capturas de pantalla
![](https://i.imgur.com/DpSiCyT.png)
![](https://i.imgur.com/QAtFRdi.png)
![](https://i.imgur.com/1GpTfeH.png)

