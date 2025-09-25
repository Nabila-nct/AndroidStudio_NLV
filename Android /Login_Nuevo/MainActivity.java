package com.example.login_nuevo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  EditText etUsername, etPassword;
  Button btnLogin, btnCancel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R .layout.activity_main);

    etUsername = findViewById(R.id.etUsername);
    etPassword = findViewById(R.id.etPassword);
    btnLogin = findViewById(R.id.btnLogin);
    btnCancel = findViewById(R.id.btnCancel);

    btnLogin.setOnClickListener(v -> {
      String user = etUsername.getText().toString();
      String pass = etPassword.getText().toString();

      if(user.equals("admin") && pass.equals("1234")){
        Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
      }
    });

    btnCancel.setOnClickListener(v -> {
      etUsername.setText("");
      etPassword.setText("");
    });
  }
}
