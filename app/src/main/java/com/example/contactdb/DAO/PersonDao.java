package com.example.contactdb.DAO;

// /dao/PersonDao.java
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.contactdb.Models.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    long insertPerson(Person person);

    @Query("SELECT * FROM persons ORDER BY name")
    List<Person> getAllPersons();
}

