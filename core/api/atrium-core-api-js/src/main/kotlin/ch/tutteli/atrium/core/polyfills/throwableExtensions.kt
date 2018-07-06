package ch.tutteli.atrium.core.polyfills

actual val Throwable.stack: List<String> get() = TODO("this.asDynamic().stack as List<String>")
