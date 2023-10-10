package com.example.contactdb.ContactDetails;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.contactdb.DatabaseHelper;
import com.example.contactdb.R;
import com.example.contactdb.databinding.FragmentContactDetailsBinding;

public class ContactDetails extends Fragment {

    private ContactDetailsViewModel mViewModel;
    FragmentContactDetailsBinding binding;

    public static ContactDetails newInstance() {
        return new ContactDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContactDetailsViewModel.class);
        binding = FragmentContactDetailsBinding.inflate(inflater,container,false);
        // Also declares an object of our helper class
        DatabaseHelper db = new DatabaseHelper(this.getContext());

        // Call the getDetails method we created
        String details = db.getDetails();

        TextView detailsTxt = binding.detailsText;

        // The text returned is just displayed
        detailsTxt.setText(details);
        return binding.getRoot();
    }

}