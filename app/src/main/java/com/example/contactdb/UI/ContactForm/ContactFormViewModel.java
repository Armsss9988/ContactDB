package com.example.contactdb.UI.ContactForm;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactFormViewModel extends ViewModel {
    private MutableLiveData<String> textName;
    private MutableLiveData<String> textDob;
    private MutableLiveData<String> textEmail;
    public  ContactFormViewModel(){
        textName = new MutableLiveData<>();
        textDob = new MutableLiveData<>();
        textEmail = new MutableLiveData<>();
    }
    public MutableLiveData<String> getTextName(){
    return textName;
    }
    public MutableLiveData<String> getTextDob(){
        return textDob;
    }
    public MutableLiveData<String> getTextEmail(){
        return textEmail;
    }

}