package me.doublenico.BeeredNotes.note;

public record NoteModel(Long id, String title, String content) {
    public NoteModel {
        if (title.isBlank()) throw new IllegalArgumentException("Title cannot be blank");

        if (content.isBlank()) throw new IllegalArgumentException("Content cannot be blank");
    }

    @Override
    public String toString() {
        return "Note{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
