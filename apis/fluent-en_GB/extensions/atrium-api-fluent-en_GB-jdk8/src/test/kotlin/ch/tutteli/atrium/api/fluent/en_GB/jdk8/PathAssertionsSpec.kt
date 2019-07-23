package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.fun0
import org.spekframework.spek2.meta.Ignore
import java.nio.file.Path

@Ignore
class PathAssertionsSpec : ch.tutteli.atrium.specs.integration.PathAssertionsSpec(
    AssertionVerbFactory,
    fun0(Expect<Path>::exists)
)
