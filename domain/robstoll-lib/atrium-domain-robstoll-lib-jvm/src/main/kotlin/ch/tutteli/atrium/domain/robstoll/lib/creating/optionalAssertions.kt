package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import java.util.*

//TODO #47 remove annotation
@Suppress("UNUSED_PARAMETER")
fun <E, T: Optional<E>> _isEmpty(assertionContainer: Expect<T>): Assertion =
    TODO(
        """
        #47 create a feature assertion for Optional<T>::isEmpty and assert it to be true
        Notice, there is a bug in the new-type-inference https://youtrack.jetbrains.com/issue/KT-32851
        the bug is only within IDEA, check gradle if it works

        Also note that JDK11 provides Optional.isEmpty but jdk8 does not yet, implement the check in terms of `isPresent`
        However, in reporting we would like to see something like
        expect: Optional[1]
        - is: empty

        and not

        expect: Optional[1]
        - isPresent: true
          - to be: false

        Which means we should provide a custom Description (which is translatable). For this create:
        - DescriptionOptionalAssertion in atrium-translations-en_GB-common (copy DescriptionCollectionAssertion enum and adapt)
        - don't forget to add it also for atrium-translations-de_CH-common  
        """
    )
