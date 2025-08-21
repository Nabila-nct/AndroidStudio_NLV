package com.example.propinas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  EditText d_monto, d_personas;
  RadioGroup grupoPropinas;
  RadioButton d_prop5, d_prop10, d_prop15;
  Button btnCalcular;
  TextView tvResultado, tvPorPersona;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    d_monto = findViewById(R.id.monto);
    d_personas = findViewById(R.id.personas);
    grupoPropinas = findViewById(R.id.grupo_propinas);
    d_prop5 = findViewById(R.id.prop5);
    d_prop10 = findViewById(R.id.prop10);
    d_prop15 = findViewById(R.id.prop15);
    btnCalcular = findViewById(R.id.btn_calcular);
    tvResultado = findViewById(R.id.tv_resultado);
    tvPorPersona = findViewById(R.id.tv_porPersona);

    btnCalcular.setOnClickListener(v -> {
      try {
        double monto = Double.parseDouble(d_monto.getText().toString());
        int personas = Integer.parseInt(d_personas.getText().toString());

        if (personas <= 0) {
          Toast.makeText(this, "Número de personas inválido", Toast.LENGTH_SHORT).show();
          return;
        }

        double porcentaje = 0;
        int seleccionado = grupoPropinas.getCheckedRadioButtonId();

        if (seleccionado == R.id.prop5) porcentaje = 0.05;
        else if (seleccionado == R.id.prop10) porcentaje = 0.10;
        else if (seleccionado == R.id.prop15) porcentaje = 0.15;
        else {
          Toast.makeText(this, "Selecciona un porcentaje de propina", Toast.LENGTH_SHORT).show();
          return;
        }

        double propina = monto * porcentaje;
        double total = monto + propina;
        double porPersona = total / personas;

        tvResultado.setText("Propina total: $" + String.format("%.2f", propina) + "\nTotal con propina: $" + String.format("%.2f", total));
        tvPorPersona.setText("Cada persona paga: $" + String.format("%.2f", porPersona));

      } catch (Exception e) {
        Toast.makeText(this, "Por favor ingresa valores válidos", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
