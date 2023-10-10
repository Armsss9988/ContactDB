package com.example.contactdb.ContactForm;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactdb.DatabaseHelper;
import com.example.contactdb.DetailsActivity;
import com.example.contactdb.MainActivity;
import com.example.contactdb.R;
import com.example.contactdb.databinding.FragmentContactFormBinding;

public class ContactFormFragment extends Fragment {

    public static ContactFormFragment newInstance() {
        return new ContactFormFragment();
    }

    private ContactFormViewModel mViewModel;
    private FragmentContactFormBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContactFormViewModel.class);
        binding = FragmentContactFormBinding.inflate(inflater,container,false);
        Button saveDetailsButton = binding.saveDetailsButton;
        registerLiveDataListenner();
        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });
        return binding.getRoot();
    }
    public void registerLiveDataListenner() {
        mViewModel.getTextName().observe(getViewLifecycleOwner(), s -> binding.nameText.setText(s));
        mViewModel.getTextDob().observe(getViewLifecycleOwner(), s -> binding.dobText.setText(s));
        mViewModel.getTextEmail().observe(getViewLifecycleOwner(), s -> binding.emailText.setText(s));
    }
    private void saveDetails() {
        // Creates an object of our helper class
        DatabaseHelper dbHelper = new DatabaseHelper(this.getActivity().getApplicationContext());


        String name = binding.nameText.getText().toString();
        String dob = binding.dobText.getText().toString();
        String email = binding.emailText.getText().toString();

        // Calls the insertDetails method we created
        long personId = dbHelper.insertDetails(name, dob, email);

        // Shows a toast with the automatically generated id
        Toast.makeText(this.getContext(), "Person has been created with id: " + personId,
                Toast.LENGTH_LONG
        ).show();
        // Shows a toast with the automatically generated id
        Toast.makeText(this.getContext(), "Person has been created with id: " + personId,
                Toast.LENGTH_LONG
        ).show();

    }



}