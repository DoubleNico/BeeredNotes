package me.doublenico.BeeredNotes.note;

import me.doublenico.BeeredNotes.security.CustomUserDetails;
import me.doublenico.BeeredNotes.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String getAllNotes(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        User user = userDetails.user();
        List<Note> notes = noteRepository.findByUser(user);
        model.addAttribute("notes", notes);
        return "index";
    }

    @GetMapping("/notes/edit/{id}")
    public String showEditForm(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent() && note.get().getUser().getId().equals(userDetails.user().getId())) {
            model.addAttribute("note", note.get());
            return "edit-note";
        }
        return "redirect:/";
    }

    @PostMapping("/notes/edit/{id}")
    public String updateNote(@PathVariable Long id, @Valid Note note, BindingResult result, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        if (result.hasErrors()) {
            return "edit-note";
        }
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent() && existingNote.get().getUser().getId().equals(userDetails.user().getId())) {
            note.setUser(userDetails.user());
            noteRepository.save(note);
        }
        return "redirect:/";
    }

    @PostMapping("/")
    public String createNote(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam String title, @RequestParam String content) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        User user = userDetails.user();
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setUser(user);
        noteRepository.save(note);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent() && note.get().getUser().getId().equals(userDetails.user().getId())) {
            noteRepository.deleteById(id);
        }
        return "redirect:/";
    }

    @GetMapping("/notes/{id}")
    public String viewNote(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent() && note.get().getUser().getId().equals(userDetails.user().getId())) {
            model.addAttribute("note", note.get());
            return "note";
        }
        return "redirect:/";
    }
}