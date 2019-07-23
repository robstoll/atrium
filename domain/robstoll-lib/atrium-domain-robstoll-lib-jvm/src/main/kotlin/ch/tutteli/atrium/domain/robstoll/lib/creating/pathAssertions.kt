package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import java.nio.file.Path

//TODO #104 remove annotation
@Suppress("UNUSED_PARAMETER")
fun <T : Path> _exists(assertionContainer: Expect<T>): Assertion =
    TODO(
        """
        #104 create a feature assertion for Path::exists and assert it is true.
        Notice, there is a bug in the new-type-inference https://youtrack.jetbrains.com/issue/KT-32851 
        the bug is only within IDEA, check gradle if it works
        """
    )
