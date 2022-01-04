package com.sicredi.events.data.util.storage
import java.lang.reflect.Type

interface Cache {
    fun <T> get(key: String, type: Type): T?
    fun set(key: String, value: Any?)
    fun clear()
    class NotFoundException : Exception()
}