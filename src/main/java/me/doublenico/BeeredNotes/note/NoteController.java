package me.doublenico.BeeredNotes.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/")
    public String getAllNotes(Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "index";
    }

    @GetMapping("/notes/{id}")
    public String getNoteById(@PathVariable Long id, Model model) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            model.addAttribute("note", note.get());
            return "note";
        }
        return "redirect:/";
    }

    @GetMapping("/notes/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            model.addAttribute("note", note.get());
            return "edit-note";
        }
        return "redirect:/";
    }

    @PostMapping("/notes/edit/{id}")
    public String updateNote(@PathVariable Long id, @Valid Note note, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-note";
        }
        noteRepository.save(note);
        return "redirect:/";
    }

    @PostMapping("/")
    public String createNote(@Valid Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Note> notes = noteRepository.findAll();
            model.addAttribute("notes", notes);
            return "index";
        }
        noteRepository.save(note);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            noteRepository.deleteById(id);
        }
        return "redirect:/";
    }
}