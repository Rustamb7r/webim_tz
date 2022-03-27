package ru.webim.tz.presentation.util

import android.util.Log
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

fun String.toIntSafe() = try {
    this.toInt()
} catch (e: Exception) {
    Log.w("extensions", e)
    0
}

fun Int?.toStringOrEmpty() = this?.toString() ?: ""

@Suppress("UNCHECKED_CAST")
fun <T> Any.getGenericType(): Class<T> {
    fun Type.getParameterizedType(): ParameterizedType? {
        if (this is ParameterizedType) return this
        if (this is Class<*>) return genericSuperclass?.getParameterizedType()
        return null
    }

    javaClass.getParameterizedType()?.actualTypeArguments?.forEach {
        return it as? Class<T> ?: return@forEach
    }
    throw IllegalStateException("No generic found for $this")
}