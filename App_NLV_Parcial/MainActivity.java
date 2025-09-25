package com.example.convertidor_de_monedas;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

  private EditText etAmount;
  private Spinner spinnerFrom;
  private Spinner spinnerTo;
  private Button btnConvert;
  private Button btnReset;
  private TextView tvConvertedAmount;
  private LinearLayout inputSection;
  private LinearLayout resultSection;
  private ProgressBar progressBar;

  private OkHttpClient client;
  private Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etAmount = findViewById(R.id.et_amount);
    spinnerFrom = findViewById(R.id.spinner_from);
    spinnerTo = findViewById(R.id.spinner_to);
    btnConvert = findViewById(R.id.btn_convert);
    btnReset = findViewById(R.id.btn_reset);
    tvConvertedAmount = findViewById(R.id.tv_converted_amount);
    inputSection = findViewById(R.id.input_section);
    resultSection = findViewById(R.id.result_section);
    progressBar = findViewById(R.id.progress_bar);

    // Inicializar cliente HTTP y handler
    client = new OkHttpClient(); //Envía y recibe información de la API
    handler = new Handler(Looper.getMainLooper());

    // Configurar Spinners con divisas
    String[] currencies = {"USD", "EUR", "MXN", "JPY", "GBP", "KRW"};
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerFrom.setAdapter(adapter); //Convierte las divisas a un formato que el spinner entienda
    spinnerTo.setAdapter(adapter);

    // Establecer la selección predeterminada
    spinnerFrom.setSelection(adapter.getPosition("MXN"));
    spinnerTo.setSelection(adapter.getPosition("USD"));

    btnConvert.setOnClickListener(v -> convertCurrency());
    btnReset.setOnClickListener(v -> resetApp());
  }

  private void convertCurrency() {
    String amountStr = etAmount.getText().toString();
    if (amountStr.isEmpty()) { //si el input esta vacio
      Toast.makeText(this, "Por favor, ingresa un monto", Toast.LENGTH_SHORT).show();
      return;
    }

    double amount = Double.parseDouble(amountStr); //El string se convierte a double para la conversion
    String fromCurrency = spinnerFrom.getSelectedItem().toString(); //conversion
    String toCurrency = spinnerTo.getSelectedItem().toString();

    if (fromCurrency.equals(toCurrency)) { //Comprueba si la divisa de origen es la misma que la de destino
      tvConvertedAmount.setText(String.format("%.2f %s", amount, fromCurrency)); //La conversion con 2 decimales
      inputSection.setVisibility(View.GONE);
      resultSection.setVisibility(View.VISIBLE); //Oculta el input  y muestra el output
      return;
    }

    // Mostrar barra de progreso
    progressBar.setVisibility(View.VISIBLE);
    inputSection.setVisibility(View.GONE);
    btnConvert.setVisibility(View.GONE);

    // Construir la URL de la API
    String url = String.format("https://api.frankfurter.app/latest?from=%s&to=%s", fromCurrency, toCurrency);

    Request request = new Request.Builder().url(url).build(); //Crea el objeto de la API que contiene los detalles de solicitud que se enviará

    client.newCall(request).enqueue(new Callback() { //Se llama a la API sin cerrar la app
      @Override
      public void onFailure(Call call, IOException e) { //No se ejecuta si falla la llamada a la API
        e.printStackTrace(); //Imprime el error en la consola de Android
        handler.post(() -> {
          progressBar.setVisibility(View.GONE);
          inputSection.setVisibility(View.VISIBLE);
          btnConvert.setVisibility(View.VISIBLE);
          Toast.makeText(MainActivity.this, "Error de red. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
        });
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) { //Si la respuesta de la API fue exitosa se realiza la conversión
          String jsonResponse = response.body().string(); //Del formato JSON de la API a String
          try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONObject rates = jsonObject.getJSONObject("rates"); //Se extrae el objeto rates del JSON que contiene las tasas de cambio

            // La API devuelve un objeto con la tasa de conversión si se encuentra dentro de las opciones
            if (rates.has(toCurrency)) {
              double rate = rates.getDouble(toCurrency);
              double convertedAmount = amount * rate; //Cálculo de la conversión

              handler.post(() -> { //Se muestra el resultado de la conversión
                tvConvertedAmount.setText(String.format("%.2f %s", convertedAmount, toCurrency));
                resultSection.setVisibility(View.VISIBLE);
              });
            }

          } catch (JSONException e) { //Captura de errores en el análisis JSON
            e.printStackTrace();
            handler.post(() -> Toast.makeText(MainActivity.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show());
          }
        } else {
          handler.post(() -> Toast.makeText(MainActivity.this, "Respuesta no exitosa de la API", Toast.LENGTH_SHORT).show());
        }

        handler.post(() -> {
          progressBar.setVisibility(View.GONE);
          inputSection.setVisibility(View.VISIBLE);
          btnConvert.setVisibility(View.VISIBLE);
        });
      }
    });
  }

  private void resetApp() {
    etAmount.setText("");
    resultSection.setVisibility(View.GONE);
    inputSection.setVisibility(View.VISIBLE);
    spinnerFrom.setSelection(0);
    spinnerTo.setSelection(1);
  }
}
