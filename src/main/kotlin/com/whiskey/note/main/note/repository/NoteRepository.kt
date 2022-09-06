package com.whiskey.note.main.note.repository

import com.whiskey.note.main.note.model.Note
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface NoteRepository: ReactiveCrudRepository<Note, BigInteger> {
}