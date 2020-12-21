package com.example.ppliapp.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ppliapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InicioSesion extends AppCompatActivity {

    private EditText Email;
    private EditText Contrasena;
    private EditText RepetirContrasena;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        Email = findViewById(R.id.Email);
        Contrasena = findViewById(R.id.Contrasena);
        RepetirContrasena = findViewById(R.id.RepetirContrasena);


        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Log.i("User", "" + currentUser);
    }

    public void createUserWhitEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("EXITO", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ERROR", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InicioSesion.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // ...
                    }
                });
    }
    public void pulsaBoton(View view){
        String email = Email.getText().toString();
        String contrasena = Contrasena.getText().toString();
        String repetircontrasena = RepetirContrasena.getText().toString();

        if (!email.isEmpty()&&contrasena.isEmpty()&&repetircontrasena.isEmpty()){
            if (contrasena.equals(repetircontrasena)){
                if(contrasena.length()>8){
                    createUserWhitEmailAndPassword(email,contrasena);
                }else
                    Toast.makeText(this, "La contraseña debe ser mayor a 8 dígitos", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Por favor rellene todos los campos", Toast.LENGTH_SHORT).show();
    }
}