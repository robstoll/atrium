@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.OptionalAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isEmpty
import java.util.*

class OptionalAssertionsImpl : OptionalAssertions {

    override fun <T : Optional<*>> isEmpty(expect: Expect<T>) = _isEmpty(expect)
}
