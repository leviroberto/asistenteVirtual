package com.velasquez.asistentevirtualucv.Interactors;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.velasquez.asistentevirtualucv.Models.Clases.Curso;
import com.velasquez.asistentevirtualucv.Models.Clases.Tarea;
import com.velasquez.asistentevirtualucv.Models.Interfaces.IMain;

import java.util.ArrayList;
import java.util.List;


public class Main_Interactor implements IMain.IMain_Interactor {
    private IMain.IMain_Presentor iMain_presentor;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private CollectionReference cr;

    public Main_Interactor(IMain.IMain_Presentor iMain_presentor) {
        this.iMain_presentor = iMain_presentor;
        mAuth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();
        this.cr = db.collection("Cursos");
    }

    @Override
    public void obtenerCursos() {
        final String id = mAuth.getCurrentUser().getUid();
        db.enableNetwork();

        cr.whereEqualTo("docente_Id", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                List<Curso> listCurso = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Curso curso = (Curso) document.toObject(Curso.class);
                                    curso.setId(document.getId());
                                    listCurso.add(curso);

                                }
                                iMain_presentor.obtenerCursosCorrecto(listCurso);
                            } else {
                                iMain_presentor.operacionIncorrecta("No tiene cursos registrados");
                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iMain_presentor.operacionIncorrecta("No tiene cursos registrados");

            }
        });

    }

    @Override
    public void crearTarea(Tarea tarea) {

        tarea.setDocente_id(mAuth.getCurrentUser().getUid());
        tarea.setEstado("Pendiente");
        db.enableNetwork();
        db.collection("Tareas")
                .add(tarea).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                // obtenerTokenAdministrador(prestamo);
                iMain_presentor.operacionCorrecta("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iMain_presentor.operacionIncorrecta("Se ha producido un error. Vuelve a intentarlo mas tarde");
            }
        });
    }

    @Override
    public void buscarTareaPorFecha(String date) {
        final String id = mAuth.getCurrentUser().getUid();
        db.enableNetwork();
        db.collection("Tareas").whereEqualTo("docente_id", id).whereEqualTo("fecha",date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                List<Tarea> listTarea = new ArrayList<>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Tarea tarea = (Tarea) document.toObject(Tarea.class);
                                    tarea.setId(document.getId());
                                    listTarea.add(tarea);

                                }

                                iMain_presentor.buscarTareaPorFechaCorrecto(listTarea);
                            } else {
                                iMain_presentor.operacionIncorrecta("No hay tareas por la fecha buscada");
                            }

                        }else
                        {
                            iMain_presentor.operacionIncorrecta("No hay tareas por la fecha buscada");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iMain_presentor.operacionIncorrecta("No hay tareas por la fecha buscada ");

            }
        });
    }
}
