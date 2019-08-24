package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import org.spekframework.spek2.meta.Ignore
import java.nio.file.Path

//TODO #108 remove @Ignore
@Ignore
class PathAssertionsSpec : ch.tutteli.atrium.specs.integration.PathAssertionsSpec(
    fun0(Expect<Path>::exists)
)
