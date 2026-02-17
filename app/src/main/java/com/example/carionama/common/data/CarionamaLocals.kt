package com.example.carionama.common.data

import com.example.carionama.R

enum class CarionamaLocals(
    val code: String, val displayNameResId: Int
) {
    Fa("fa", R.string.farsi), En("en", R.string.english);

    companion object {
        fun fromCode(code: String): CarionamaLocals = entries.find { it.code == code } ?: En
    }
}
