@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)
@file:JvmMultifileClass

package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.creating.RootExpect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

@Suppress(
    "UNCHECKED_CAST",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-36752 is fixed */ "USELESS_CAST"
)
fun <T> expect(subject: suspend CoroutineScope.() -> T): RootExpect<() -> T> =
    expect({ runBlocking(block = subject) } as () -> T)
