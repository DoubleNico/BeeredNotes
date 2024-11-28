package me.doublenico.BeeredNotes.user;

import me.doublenico.BeeredNotes.note.NoteModel;

import java.util.List;

public record UserModel(Long id, String username, String email, String password, List<NoteModel> notes) {
    public UserModel {
        if (username.isBlank())
            throw new IllegalArgumentException("Username cannot be blank");

        if (password.isBlank())
            throw new IllegalArgumentException("Password cannot be blank");

        if (email.isBlank())
            throw new IllegalArgumentException("Email cannot be blank");
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", notes=" + notes +
            '}';
    }
}
