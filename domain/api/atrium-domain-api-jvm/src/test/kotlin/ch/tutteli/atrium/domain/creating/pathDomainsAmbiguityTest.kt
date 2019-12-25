package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import java.nio.file.Path
import java.nio.file.Paths

@Suppress("unused")
private fun ambiguityTest() {
    val fluent: Expect<Path> = notImplemented()

    //Path
    fluent._domain.startsWith(Paths.get("a"))

    //Comparable
    fluent._domain.isLessThan(Paths.get("a"))

    //Iterable<E: Comparable>
    fluent._domain.min()
}
