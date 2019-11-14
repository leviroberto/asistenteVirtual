package com.velasquez.asistentevirtualucv.Interactors;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velasquez.asistentevirtualucv.Interfaces.ICrearCuenta_Interactor;
import com.velasquez.asistentevirtualucv.Interfaces.ICrearCuenta_Presentor;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Interactor;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Presenter;
import com.velasquez.asistentevirtualucv.Models.Docente;

public class CrearCuenta_Interactor implements ICrearCuenta_Interactor {


    private ICrearCuenta_Presentor iCrearCuenta_presentor;
    private FirebaseFirestore db;
    private CollectionReference cr;
    private FirebaseAuth mAuth;

    public CrearCuenta_Interactor(ICrearCuenta_Presentor iCrearCuenta_presentor) {
        this.iCrearCuenta_presentor = iCrearCuenta_presentor;
        this.db = FirebaseFirestore.getInstance();
        this.cr = db.collection("Docentes");
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void crearCuenta(String email, String password) {
        db.enableNetwork();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            iCrearCuenta_presentor.operacionCorrecta("");
                        } else {
                            mAuth.signOut();
                            iCrearCuenta_presentor.operacionIncorrecta("");
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mAuth.signOut();
                iCrearCuenta_presentor.operacionCorrecta(e.getMessage().toString());

            }
        });

    }
}
