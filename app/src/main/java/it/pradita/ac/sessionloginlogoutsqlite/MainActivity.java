package it.pradita.ac.sessionloginlogoutsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import it.pradita.ac.sessionloginlogoutsqlite.helper.DBHelper;


public class MainActivity extends AppCompatActivity {

    TextInputEditText etUsername, etPassword;
    DBHelper helper;
    String username, password;
    MaterialButton login;
    TextView tvBuatAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        tvBuatAkun = findViewById(R.id.bikinAkun);

        helper = new DBHelper(this);

        tvBuatAkun.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        login.setOnClickListener(v -> {
            username = etUsername.getText().toString().trim();
            password = etPassword.getText().toString().trim();

            Boolean res =  helper.checkUser(username, password);

            if (res == true){
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            } else {
                Toast.makeText(MainActivity.this, "Error, Login Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}