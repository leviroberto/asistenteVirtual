package com.velasquez.asistentevirtualucv.Interactors;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Interactor;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Presenter;
import com.velasquez.asistentevirtualucv.Models.Docente;

public class Docente_Interactor implements IDocente_Interactor {


    private IDocente_Presenter iDocente_presenter;
    private FirebaseFirestore db;
    private CollectionReference cr;

    public Docente_Interactor(IDocente_Presenter iDocente_presenter) {
        this.iDocente_presenter = iDocente_presenter;
        this.db = FirebaseFirestore.getInstance();
        this.cr = db.collection("Docentes");
    }

    @Override
    public void agregar(Docente docente) {

        cr.add(docente).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                iDocente_presenter.operacionCorrecta("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iDocente_presenter.operacionIncorrecta("");
            }
        });
    }

    @Override
    public void moficiar(Docente docente) {

    }

    @Override
    public void eliminar(Docente docente) {

    }
}
