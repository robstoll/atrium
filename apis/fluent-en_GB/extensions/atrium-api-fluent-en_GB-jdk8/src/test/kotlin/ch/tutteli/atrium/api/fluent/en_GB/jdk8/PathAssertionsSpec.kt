package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import java.nio.file.Path

class PathAssertionsSpec : ch.tutteli.atrium.specs.integration.PathAssertionsSpec(
    fun0(Expect<Path>::exists),
    fun0(Expect<Path>::existsNot),
    fun0(Expect<Path>::isReadable),
    fun0(Expect<Path>::isWritable),
    fun0(Expect<Path>::isRegularFile),
    fun0(Expect<Path>::isDirectory)
)
