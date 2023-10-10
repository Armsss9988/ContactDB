package com.example.contactdb.UI.ContactDetails;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contactdb.AppDatabase.AppDatabase;
import com.example.contactdb.Models.Person;
import com.example.contactdb.databinding.FragmentContactDetailsBinding;

import java.util.List;

public class ContactDetails extends Fragment {

    private ContactDetailsViewModel mViewModel;
    FragmentContactDetailsBinding binding;
    AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    public static ContactDetails newInstance() {
        return new ContactDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContactDetailsViewModel.class);
        binding = FragmentContactDetailsBinding.inflate(inflater,container,false);
        // Also declares an object of our helper class
        appDatabase = Room.databaseBuilder(this.getActivity().getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        // Call the getDetails method we created
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));

        // The text returned is just displayed
        List<Person> persons = appDatabase.personDao().getAllPersons();

        StringBuilder detailsBuilder = new StringBuilder();
        for (Person person : persons) {
            detailsBuilder.append(person.person_id).append(" ")
                    .append(person.name).append(" ")
                    .append(person.dob).append(" ")
                    .append(person.email).append("\n");
        }
        adapter = new ContactAdapter(persons);
        recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

}