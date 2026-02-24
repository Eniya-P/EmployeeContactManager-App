package com.example.employeecontactmanagerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private Context context;
    private List<Employee> employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        super(context, 0, employees);
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        }

        Employee employee = employees.get(position);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvDepartment = convertView.findViewById(R.id.tvDepartment);
        Button btnCall = convertView.findViewById(R.id.btnCall);
        Button btnSms = convertView.findViewById(R.id.btnSms);
        Button btnEmail = convertView.findViewById(R.id.btnEmail);

        tvName.setText(employee.getName());
        tvId.setText("ID: " + employee.getId());
        tvDepartment.setText("Department: " + employee.getDepartment());

        btnCall.setOnClickListener(v -> showConfirmationDialog("Call", employee.getName(), () -> {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + employee.getPhone()));
            context.startActivity(intent);
        }));

        btnSms.setOnClickListener(v -> showConfirmationDialog("Send SMS", employee.getName(), () -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + employee.getPhone()));
            context.startActivity(intent);
        }));

        btnEmail.setOnClickListener(v -> showConfirmationDialog("Send Email", employee.getName(), () -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + employee.getEmail()));
            context.startActivity(intent);
        }));

        return convertView;
    }

    private void showConfirmationDialog(String action, String employeeName, Runnable onConfirm) {
        new AlertDialog.Builder(context)
                .setTitle("Confirm " + action)
                .setMessage("Do you want to " + action.toLowerCase() + " " + employeeName + "?")
                .setPositiveButton(action, (dialog, which) -> onConfirm.run())
                .setNegativeButton("Cancel", null)
                .show();
    }
}
