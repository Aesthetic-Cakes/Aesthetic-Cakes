package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    EditText txtCorreo, txtNombre, txtContrasenia, txtConfirmar;
    Button btnContinuar, btnRegresar;

    AwesomeValidation awesomeValidation;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Filtros de correo y contrasenia
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.txtCorreo, Patterns.EMAIL_ADDRESS, R.string.invalid_mail);
        awesomeValidation.addValidation(this,R.id.txtContraseña2, ".{6,}", R.string.invalid_pass);

        txtCorreo = findViewById(R.id.txtCorreo);
        txtNombre = findViewById(R.id.txtNombre);
        txtContrasenia = findViewById(R.id.txtContraseña2);
        txtConfirmar = findViewById(R.id.txtConfirmacion);

        btnContinuar = findViewById(R.id.btnContinuar);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = txtCorreo.getText().toString();
                String pass = txtContrasenia.getText().toString();
                String nombre = txtNombre.getText().toString();
                String confirmar = txtConfirmar.getText().toString();

                if (awesomeValidation.validate()){
                    firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Map<String, Object> map = new HashMap<>();
                                map.put("Name", nombre);
                                map.put("Mail", mail);
                                map.put("Pass", pass);

                                String id = firebaseAuth.getCurrentUser().getUid();
                                mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task2) {
                                        if (task2.isSuccessful()){
                                            Toast.makeText(RegistroActivity.this, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else {
                                            Toast.makeText(RegistroActivity.this, "No se crearon los datos correctamente", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }else{
                                if (nombre.isEmpty() || nombre.length() < 6){
                                    Toast.makeText(RegistroActivity.this, "Usuario no es valido", Toast.LENGTH_SHORT).show();
                                }else if (confirmar.isEmpty() || !confirmar.equals(pass)){
                                    Toast.makeText(RegistroActivity.this, "Contraseña no coincide", Toast.LENGTH_SHORT).show();
                                }
                                String error = ((FirebaseAuthException) task.getException()).getErrorCode();
                                dameToastdeerror(error);
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistroActivity.this, "Llene todos los espacios", Toast.LENGTH_SHORT);
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(RegistroActivity.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(RegistroActivity.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(RegistroActivity.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(RegistroActivity.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                txtCorreo.setError("La dirección de correo electrónico está mal formateada.");
                txtCorreo.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(RegistroActivity.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                txtContrasenia.setError("la contraseña es incorrecta ");
                txtContrasenia.requestFocus();
                txtContrasenia.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(RegistroActivity.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(RegistroActivity.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(RegistroActivity.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(RegistroActivity.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                txtCorreo.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                txtCorreo.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(RegistroActivity.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(RegistroActivity.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(RegistroActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(RegistroActivity.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(RegistroActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(RegistroActivity.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(RegistroActivity.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                txtContrasenia.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                txtContrasenia.requestFocus();
                break;

        }

    }

}