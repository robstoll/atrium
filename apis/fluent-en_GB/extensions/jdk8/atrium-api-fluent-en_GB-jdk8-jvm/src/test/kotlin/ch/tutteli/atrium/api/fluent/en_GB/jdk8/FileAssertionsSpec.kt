package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import java.io.File
import java.nio.file.Path

class FileAssertionsSpec : ch.tutteli.atrium.specs.integration.FileAssertionsSpec(
    Expect<File>::asPath
)
