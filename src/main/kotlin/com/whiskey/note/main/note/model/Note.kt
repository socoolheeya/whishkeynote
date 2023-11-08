package com.whiskey.note.main.note.model

import jakarta.validation.constraints.Min
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table(name = "note")
data class Note(
    @field:Id
    @Column(value = "note_id")
    var noteId: BigInteger,

    @field:NotNull
    @Column(value = "aroma_id")
    var aromaId: BigInteger,

    @field:NotNull
    @Column(value = "palate_id")
    var palateId: BigInteger,

    @field:NotNull
    @Column(value = "color_id")
    var colorId: BigInteger,

    @field:NotNull
    @Column(value = "finish_id")
    var finishId: BigInteger,

    @Column(value = "whiskey_name")
    var whiskeyName: String? = null,

    @field:Min(0)
    @Column(value = "age")
    var age: Int? = 0,

    @Column(value = "country")
    var country: String? = null,

    @Column(value = "volume")
    var volume: Double? = null,

    @Column(value = "region")
    var region: String? = null,

    @Column(value = "type")
    var type: String? = null,

    @Column(value = "distiller")
    var distiller: String? = null,

    @Column(value = "total_score")
    var totalScore: Double? = 0.0,

    @Column(value = "notes")
    var notes: String? = null

)