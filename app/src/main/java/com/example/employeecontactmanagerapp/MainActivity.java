package com.example.employeecontactmanagerapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etId, etPhone, etEmail;
    private Spinner spDepartment;
    private Button btnSave;
    private GridView gridView;
    private List<Employee> employeeList;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        spDepartment = findViewById(R.id.spDepartment);
        btnSave = findViewById(R.id.btnSave);
        gridView = findViewById(R.id.gridView);

        String[] departments = {"HR", "IT", "Sales", "Marketing", "Finance"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDepartment.setAdapter(spinnerAdapter);

        employeeList = new ArrayList<>();
        adapter = new EmployeeAdapter(this, employeeList);
        gridView.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String id = etId.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String department = spDepartment.getSelectedItem().toString();

            if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Employee newEmployee = new Employee(name, id, phone, email, department);
                employeeList.add(newEmployee);
                adapter.notifyDataSetChanged();
                clearFields();
            }
        });
    }

    private void clearFields() {
        etName.setText("");
        etId.setText("");
        etPhone.setText("");
        etEmail.setText("");
        spDepartment.setSelection(0);
    }
}
