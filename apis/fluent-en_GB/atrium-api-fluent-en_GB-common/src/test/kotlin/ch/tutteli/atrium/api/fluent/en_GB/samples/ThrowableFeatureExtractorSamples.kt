package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ThrowableFeatureExtractorSamples {

    @Test
    fun messageToContain() {
        expect(RuntimeException("abc")).messageToContain("b")

        fails {
            expect(RuntimeException("abc")).messageToContain("d")
        }
    }

    @Test
    fun messageFeature() {
        expect(RuntimeException("abc"))
            .message         // subject is now of type String
            .toContain("a")

        fails {
            expect(RuntimeException("abc"))
                .message
                .toContain("d")   // fails
                .toStartWith("z") // not evaluated/reported because `toContain` already fails
            //                       use `.message { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun message() {
        expect(RuntimeException("abc")).message { // subject inside this block is of type String (actually "abc")
            toContain("a")
        } // subject here is back to type RuntimeException

        fails {
            expect(RuntimeException("abc")).message { // subject inside this block is of type String (actually "abc")
                toContain("d")   // fails
                toStartWith("z") // still evaluated even though `toContain` already fails
                //                  use `.message.` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun causeFeature() {
        expect(IllegalStateException(IndexOutOfBoundsException("abc")))
            .cause<IndexOutOfBoundsException>() // subject is now of type IndexOutOfBoundsException
            .messageToContain("b")

        fails {
            expect(IllegalStateException(IndexOutOfBoundsException("abc")))
                .cause<IllegalStateException>() // fails
                .messageToContain("d")          // not evaluated/reported because `cause` already fails
            //                                     use `.cause<...> { ... }` if you want that all assertions are evaluated

        }
    }

    @Test
    fun cause() {
        expect(IllegalStateException(IndexOutOfBoundsException("abc"))).cause<IndexOutOfBoundsException> { // subject is now of type IndexOutOfBoundsException
            messageToContain("b")
        }

        fails { // because wrong type expected (IllegalStateException instead of IndexOutOfBoundsException), but since we use a block...
            expect(IllegalStateException(IndexOutOfBoundsException("abc"))).cause<IllegalStateException> {
                messageToContain("b") // ... reporting mentions that subject's message was expected `to contain: "b"`
            }
        }

        fails {
            // because you forgot to define an expectation in the expectation group block
            // use `.cause<...>()` if this is all you expect
            expect(IllegalStateException(IndexOutOfBoundsException("abc"))).cause<IllegalStateException> { }
        }
    }
}
