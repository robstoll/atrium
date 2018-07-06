package ch.tutteli.atrium.core.polyfills

actual val Throwable.stack: List<String> get() = this.stackTrace.map { it.toString() }
