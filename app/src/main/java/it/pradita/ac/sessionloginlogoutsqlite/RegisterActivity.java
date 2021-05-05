package it.pradita.ac.sessionloginlogoutsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import it.pradita.ac.sessionloginlogoutsqlite.helper.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText etUsername, etPassword, etKonfirmasiPass;
    MaterialButton btnCreate;
    DBHelper helper;
    TextView tvLogin;
    String username, password, confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        helper = new DBHelper(this);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etKonfirmasiPass = findViewById(R.id.confirm_pass);
        tvLogin = findViewById(R.id.loginAKun);
        btnCreate = findViewById(R.id.create);

        tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        });

        btnCreate.setOnClickListener(v -> {
            username = etUsername.getText().toString().trim();
            password = etPassword.getText().toString().trim();
            confirm_password = etKonfirmasiPass.getText().toString().trim();

            ContentValues values = new ContentValues();

            if (!password.equals(confirm_password)){
                Toast.makeText(RegisterActivity.this, "Password tidak sama!", Toast.LENGTH_SHORT);
            }else if (password.equals("") || username.equals("") || confirm_password.equals("")){
                Toast.makeText(RegisterActivity.this, "Fields tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            } else {
                values.put(DBHelper.USERNAME, username);
                values.put(DBHelper.PASSWORD, password);
                helper.insertData(values);

                Toast.makeText(RegisterActivity.this, "Buat akun sukses!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}