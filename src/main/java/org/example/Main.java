package org.example;
import org.example.Dao.*;
import org.example.DaoImple.*;
import org.example.Dto.*;
import org.example.GUI.Conexion;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        // Establecer la conexión a PostgreSQL
        Conexion conexion = Conexion.getOrCreate();
        Connection connection = conexion.conectarPostgreSQL();

        if (conexion.estaConectado()) {
            System.out.println("Conexión exitosa a PostgreSQL");

            try {
                // Ejemplo con la tabla "materias"
                MateriaDao materiaDao = new MateriaDaoImple();
                Materia nuevaMateria = new Materia("Matemáticas", "Descripción de Matemáticas");
                int idMateria = materiaDao.insert(nuevaMateria);
                System.out.println("Materia insertada con ID: " + idMateria);

                // Ejemplo con la tabla "cursos"
                CursoDao cursoDao = new CursoDaoImple();
                Curso nuevoCurso = new Curso("Informática");
                int idCurso = cursoDao.insert(nuevoCurso);
                System.out.println("Curso insertado con ID: " + idCurso);

                // Ejemplo con la tabla "tutores"
                TutorDao tutorDao = new TutorDaoImple();
                Tutor nuevoTutor = new Tutor("Juan", "Perez", "123456789", "ABC123");
                int idTutor = tutorDao.insert(nuevoTutor);
                System.out.println("Tutor insertado con ID: " + idTutor);

                // Ejemplo con la tabla "estudiantes"
                EstudianteDao estudianteDao = new EstudianteDaoImple();
                Estudiante nuevoEstudiante = new Estudiante("María", "Gomez", Date.valueOf("2000-01-15"), "ABC987654", idTutor, idCurso);
                int idEstudiante = estudianteDao.insert(nuevoEstudiante);
                System.out.println("Estudiante insertado con ID: " + idEstudiante);

                // Ejemplo con la tabla "maestros"
                MaestroDao maestroDao = new MaestroDaoImple();
                Maestro nuevoMaestro = new Maestro("Carlos", "Ramirez", Date.valueOf("1990-05-20"), "XYZ456789", idMateria);
                int idMaestro = maestroDao.insert(nuevoMaestro);
                System.out.println("Maestro insertado con ID: " + idMaestro);

                // Ejemplo con la tabla "pagos"
                PagoDao pagoDao = new PagoDaoImple();
                Pago nuevoPago = new Pago(idEstudiante, "Matrícula", new BigDecimal("500.00"), Date.valueOf("2023-01-10"));
                int idPago = pagoDao.insert(nuevoPago);
                System.out.println("Pago insertado con ID: " + idPago);

                // Ejemplo con la tabla "notas"
                NotaDao notaDao = new NotaDaoImple();
                Nota nuevaNota = new Nota(idEstudiante, idCurso, 1, new BigDecimal("90.5"));
                int idNota = notaDao.insert(nuevaNota);
                System.out.println("Nota insertada con ID: " + idNota);

                // Ejemplo con la tabla "cursos_materias"
                CursoMateriaDao cursoMateriaDao = new CursoMateriaDaoImple();
                CursoMateria nuevoCursoMateria = new CursoMateria(idCurso, idMateria);
                cursoMateriaDao.insert(nuevoCursoMateria);
                System.out.println("Relación Curso-Materia insertada");

                // Ejemplo con la tabla "detalle_notas"
                DetalleNotaDao detalleNotaDao = new DetalleNotaDaoImple();
                DetalleNota nuevoDetalleNota = new DetalleNota(idNota, new BigDecimal("25.5"), 1, Date.valueOf("2023-03-01"));
                int idDetalleNota = detalleNotaDao.insert(nuevoDetalleNota);
                System.out.println("Detalle de Nota insertado con ID: " + idDetalleNota);

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Desconectar después de usar
            conexion.desconectar();
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }
}
