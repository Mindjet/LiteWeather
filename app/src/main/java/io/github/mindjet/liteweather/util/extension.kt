package io.github.mindjet.liteweather.util

infix fun String?.append(content: String): String {
    return this + content
}