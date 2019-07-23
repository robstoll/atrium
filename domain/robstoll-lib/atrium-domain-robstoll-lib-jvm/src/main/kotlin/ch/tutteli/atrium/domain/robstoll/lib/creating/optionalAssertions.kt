package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import java.util.*

//TODO #47 remove annotation
@Suppress("UNUSED_PARAMETER")
fun <T> _isEmpty(assertionContainer: Expect<Optional<T>>): Assertion =
    TODO(
        """
        #47 create a feature assertion for Optional<T>::isEmpty and assert it to be true
        Notice, there is a bug in the new-type-inference https://youtrack.jetbrains.com/issue/KT-32851 
        the bug is only within IDEA, check gradle if it works
        """
    )
