package com.example.contactdb.UI.ContactForm;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.contactdb.AppDatabase.AppDatabase;
import com.example.contactdb.Models.Person;
import com.example.contactdb.databinding.FragmentContactFormBinding;

public class ContactFormFragment extends Fragment {

    public static ContactFormFragment newInstance() {
        return new ContactFormFragment();
    }

    private ContactFormViewModel mViewModel;
    private FragmentContactFormBinding binding;
    private AppDatabase appDatabase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContactFormViewModel.class);
        binding = FragmentContactFormBinding.inflate(inflater,container,false);
        Button saveDetailsButton = binding.saveDetailsButton;
        registerLiveDataListenner();
        appDatabase = Room.databaseBuilder(this.getActivity().getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();
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



        String name = binding.nameText.getText().toString();
        String dob = binding.dobText.getText().toString();
        String email = binding.emailText.getText().toString();

        // Calls the insertDetails method we created
        Person person = new Person();
        person.name = name;
        person.dob = dob;
        person.email = email;

        long personId = appDatabase.personDao().insertPerson(person);

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