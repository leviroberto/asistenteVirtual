package com.velasquez.asistentevirtualucv.Interactors;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.ICrearCuenta;





public class CrearCuenta_Interactor implements ICrearCuenta.ICrearCuenta_Interactor {


    private ICrearCuenta.ICrearCuenta_Presentor iCrearCuenta_presentor;
    private FirebaseFirestore db;
    private CollectionReference cr;
    private FirebaseAuth mAuth;

    public CrearCuenta_Interactor(ICrearCuenta.ICrearCuenta_Presentor iCrearCuenta_presentor) {
        this.iCrearCuenta_presentor = iCrearCuenta_presentor;
        this.db = FirebaseFirestore.getInstance();
        this.cr = db.collection("Docentes");
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void VerificarEmail(final String correo) {

        db.enableNetwork();
        cr.whereEqualTo("correoElectronico", correo)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Docente usuario = document.toObject(Docente.class);
                                    if (usuario.getCorreoElectronico().toString().equals(correo)) {
                                        iCrearCuenta_presentor.operacionIncorrecta("El correo ingresado ya se esta usando");
                                    }
                                }
                            } else {
                                iCrearCuenta_presentor.operacionCorrecta("");
                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iCrearCuenta_presentor.operacionIncorrecta("Algo esta fallando al verificar el correo electronico");

            }
        });

    }

    @Override
    public void crearCuenta(final Docente docente) {
        try {
            db.enableNetwork();
            mAuth.createUserWithEmailAndPassword(docente.getCorreoElectronico(), docente.getApellidos())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id = task.getResult().getUser().getUid();
                                docente.setId(id);
                                cr.document(id).set(docente);

                                cr.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                        iCrearCuenta_presentor.operacionCorrecta("Se creo correctamente el docente");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        iCrearCuenta_presentor.operacionIncorrecta("");
                                        //mAuth.getCurrentUser().delete();
                                    }
                                });
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mAuth.signOut();
                    iCrearCuenta_presentor.operacionIncorrecta(e.getMessage().toString());

                }
            });
        } catch (Exception e) {
            iCrearCuenta_presentor.operacionIncorrecta("asd");
        }
    }
}
