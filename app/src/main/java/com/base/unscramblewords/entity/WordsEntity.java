package com.base.unscramblewords.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "WORDS")
public class WordsEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "userId")
    private int userId;
    @ColumnInfo(name = "words")
    private String word;

    public WordsEntity(int userId, String word) {
        this.userId = userId;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordsEntity that = (WordsEntity) o;
        return id == that.id && userId == that.userId && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, word);
    }

    @Override
    public String toString() {
        return "WordsEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", word='" + word + '\'' +
                '}';
    }
}
