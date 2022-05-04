package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class Fun0ExpectationSamples {

    @Test
    fun toThrowFeature() {
        expect { throw IllegalStateException("abc") }
            .toThrow<IllegalStateException>() messageToContain "abc"
        //      | subject is now of type IllegalStateException

        fails {
            expect { throw IllegalStateException("abc") }
                .toThrow<IndexOutOfBoundsException>() messageToContain "abc"
            //                                        | not shown in reporting as `toThrow<IndexOutOfBoundsException>()` already fails
        }
    }

    @Test
    fun toThrow() {
        expect { throw IllegalStateException("abc") }
            .toThrow<IllegalStateException> { // subject inside this expectation-group is of type IllegalStateException
                its messageToContain "abc"
            } // subject keeps type IllegalStateException also after the block

        fails { // because wrong type expected (IndexOutOfBoundsException instead of IllegalStateException), but since we use an expectation-group...
            expect { throw IllegalStateException("abc") }
                .toThrow<IndexOutOfBoundsException> {
                    its messageToContain "abc" // ... reporting mentions that subject's message was expected `to contain: "abc"`
                }
        }

        fails {
            // because you forgot to define an expectation in the expectation-group block
            // use `.toThrow()` if this is all you expect
            expect { throw IllegalStateException("abc") }
                .toThrow<IndexOutOfBoundsException> {}
        }
    }

    @Test
    fun notToThrowFeature() {
        expect { "abc" }
            .notToThrow() toContain "abc"
            //  | subject is now of type String

        fails {
            expect { throw IllegalStateException("abc") }
                .notToThrow()
        }
    }

    @Test
    fun notToThrow() {
        expect { "abc" }
            .notToThrow { // subject is now of type String
                it toEqual "abc"
            } // subject keeps type String also after the block

        fails { // because an exception was thrown, but since we use an expectation-group...
            expect<() -> String> { throw IllegalStateException("abc") }
                .notToThrow {
                    it toStartWith "abc" // ... reporting mentions that subject's message was expected `to start with: "abc"`
                }
        }

        fails { // because an exception was thrown, but since we use an expectation-group...
            expect<() -> String> { throw IllegalStateException("abc") }
                .notToThrow {
                    it toStartWith "abc" // ... reporting mentions that subject's message was expected `to start with: "abc"`
                }
        }

        fails {
            // because you forgot to define an expectation in the expectation-group block
            // use `.notToThrow()` if this is all you expect
            expect { "abc" }.notToThrow {}
        }
    }
}
