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
import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.ICurso;

public class Curso_Interactor implements ICurso.ICurso_Interactor {
    private ICurso.ICurso_Presentor iCurso_presentor;
    private FirebaseFirestore db;
    private CollectionReference cr;
    private FirebaseAuth mAuth;

    public Curso_Interactor(ICurso.ICurso_Presentor iCurso_presentor) {
        this.iCurso_presentor = iCurso_presentor;
        this.db = FirebaseFirestore.getInstance();
        this.cr = db.collection("Cursos");
        mAuth = FirebaseAuth.getInstance();
        ;
    }

    @Override
    public void guardar(Curso curso) {
        db.enableNetwork();
        String id = mAuth.getUid();
        curso.setDocente_Id(id);
        cr.add(curso).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                iCurso_presentor.operacionCorrecta("Se creo correctamente el nuevo curso");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iCurso_presentor.operacionIncorrecta("Error al agregar el curso");
            }
        });
    }

    @Override
    public void verificarCurso(final Curso curso) {
        db.enableNetwork();
        cr.whereEqualTo("nombre", curso.getNombre())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Docente usuario = document.toObject(Docente.class);
                                    if (usuario.getNombre().toString().equals(curso.getNombre())) {
                                        iCurso_presentor.existeCursoCorrecto();
                                    }
                                }
                            } else {
                                iCurso_presentor.existeCursoIncorrecto(curso);
                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iCurso_presentor.operacionIncorrecta("Error al veriificar el curso");

            }
        });
    }
}
