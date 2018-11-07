package ch.tutteli.atrium.core.robstoll.lib.checking

//TODO maybe we can somehow adjust the stack as well (we could manipulate the stack via reflection I guess)
internal actual fun <T : Throwable> adjustStack(t: T): T  = t
