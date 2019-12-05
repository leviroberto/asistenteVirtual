package com.velasquez.asistentevirtualucv.Interactors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.IInicioSesion;

public class IniciarSesion_Interactor extends AppCompatActivity implements IInicioSesion.IIniciarSesion_Interactor {

    private IInicioSesion.IIniciarSesion_Presentor iIniciarSesion_presentor;
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    CollectionReference cr;

    public IniciarSesion_Interactor(IInicioSesion.IIniciarSesion_Presentor iIniciarSesion_presentor) {
        this.iIniciarSesion_presentor = iIniciarSesion_presentor;
        mAuth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();
        this.cr = db.collection("Docentes");

    }

    @Override
    public void iniciarSesion(final Docente iniciarSesion) {

        mAuth.signInWithEmailAndPassword(iniciarSesion.getCorreoElectronico(), iniciarSesion.getContraseña())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            iIniciarSesion_presentor.operacionCorrecta("Inicio sesion correcta");
                        } else {
                            mAuth.signOut();
                            iIniciarSesion_presentor.operacionIncorrecta("Error al iniciar sesion");

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mAuth.signOut();
                        iIniciarSesion_presentor.operacionIncorrecta(e.getMessage().toString());
                    }
                });
    }
}
