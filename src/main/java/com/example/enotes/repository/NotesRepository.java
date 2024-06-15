package com.example.enotes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enotes.entity.Notes;
import com.example.enotes.entity.User;


public interface NotesRepository extends JpaRepository<Notes,Integer>{
    
    public Page<Notes> findByUser(User user, Pageable pageable);
}
