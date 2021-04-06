package ch.tutteli.atrium.core.polyfills

actual val Throwable.stackBacktrace: List<String> get() = this.stackTrace.map { it.toString() }
