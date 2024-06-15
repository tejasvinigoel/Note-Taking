package com.example.enotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.enotes.entity.Notes;
import com.example.enotes.entity.User;
import com.example.enotes.repository.NotesRepository;


@Service
public class NotesServiceImpl implements NotesService{

    @Autowired
    private NotesRepository notesRepository;

    @Override
    public boolean deleteNotes(int id) {
        Notes notes=notesRepository.findById(id).get();
        if(notes!=null){
            notesRepository.delete(notes);
            return true;
        }
        return false;
    }

    @Override
    public Notes getNotesById(int id) {
        return notesRepository.findById(id).get();
    }

    @Override
    public Page<Notes> getNotesByUser(User user, int pageno) {
        Pageable pageable =PageRequest.of(pageno,5);
        return notesRepository.findByUser(user,pageable);
    }

    @Override
    public Notes saveNotes(Notes notes) {
        
        return notesRepository.save(notes);
    }

    @Override
    public Notes updateNotes(Notes notes) {
        
        return notesRepository.save(notes);
    }
    
}
