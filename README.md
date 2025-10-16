--  Sistema de Gestión de Universidad (SGULP). --

📦 Paquete Model
Este paquete contiene las clases de entidad. Su única responsabilidad es representar los datos con los que opera la aplicación, actuando como un molde para la información que se almacena en la base de datos.

Alumno.java: Representa a un estudiante. Sus atributos principales son id_alumno, dni, apellido, nombre, fechaNacimiento y estado.

Materia.java: Representa una asignatura académica. Sus atributos clave incluyen id_materia, nombre, anio en el que se cursa y estado.

Inscripcion.java: Es la clase que conecta a un Alumno con una Materia. Modela la relación entre ellos y contiene atributos como id_inscripto, nota, y los objetos Alumno y Materia asociados.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🗃️ Paquete Persistencia
Esta capa es la encargada de toda la comunicación con la base de datos. Abstrae las operaciones SQL (CRUD: Create, Read, Update, Delete) para que el resto de la aplicación no necesite conocer los detalles de la implementación de la base de datos.

Conexion.java: Gestiona la conexión con la base de datos MariaDB. Centraliza las credenciales y proporciona un método para obtener una conexión activa, asegurando que solo haya una instancia de conexión para toda la aplicación (patrón Singleton).

AlumnoData.java: Contiene todos los métodos para manipular los datos de los alumnos en la base de datos. Incluye funciones para guardar, modificar, buscar (por DNI o ID), dar de baja (baja lógica) y listar todos los alumnos.

MateriaData.java: Similar a AlumnoData, pero enfocada en la entidad Materia. Provee la lógica para guardar, modificar, buscar y gestionar el estado (alta/baja) de las materias.

InscripcionData.java: Maneja la lógica de la tabla intermedia inscripcion. Es la clase más compleja de esta capa, ya que gestiona las relaciones. Sus responsabilidades incluyen inscribir un alumno a una materia, anular inscripciones, actualizar notas y obtener listas de materias cursadas y no cursadas por un alumno específico.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🖥️ Paquete Vistas
Esta capa contiene todos los componentes de la interfaz gráfica de usuario (GUI), construidos con Java Swing. Son las ventanas con las que el usuario interactúa directamente. La comunicación sobre los datos se realiza a través de la capa de persistencia.

MenuPrincipal.java: Es la ventana principal de la aplicación. Contiene un JDesktopPane que actúa como contenedor para las demás ventanas internas (JInternalFrame), permitiendo una experiencia de aplicación de escritorio.

GestorAlumno.java: Formulario para realizar las operaciones CRUD sobre la entidad Alumno. Permite registrar, buscar, modificar y dar de baja o alta a los alumnos.

GestorMaterias.java: Interfaz destinada a la administración de las materias, permitiendo su creación, modificación y gestión de estado.

GestorInscripciones.java: El formulario más dinámico, donde el usuario puede seleccionar un alumno y ver en una tabla las materias en las que está inscripto o las que tiene disponibles para cursar. Permite realizar inscripciones y anularlas.

CargarNotas.java: Una vista especializada para que el personal administrativo pueda seleccionar un alumno, ver sus materias cursadas y modificar la nota de cada una.

ListaAlumnos.java: Muestra un listado completo de todos los alumnos registrados en el sistema en un formato de tabla.
