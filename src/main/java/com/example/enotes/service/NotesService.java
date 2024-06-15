package com.example.enotes.service;

import org.springframework.data.domain.Page;

import com.example.enotes.entity.Notes;
import com.example.enotes.entity.User;

public interface NotesService {
    public Notes saveNotes(Notes notes);
    public Notes getNotesById(int id);

    //public List<Notes> getNotesByUser(User user,int pageno);
    public Page<Notes> getNotesByUser(User user,int pageno);
    
    public Notes updateNotes(Notes notes);
    public boolean deleteNotes(int id);

}
