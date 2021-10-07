package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class Fun0ExpectationSamples {

    @Test
    fun toThrowFeature() {
        expect { throw IllegalStateException("abc") }
            .toThrow<IllegalStateException>() // subject is now of type IllegalStateException
            .messageToContain("abc")

        fails {
            expect { throw IllegalStateException("abc") }
                .toThrow<IndexOutOfBoundsException>()
                .messageToContain("abc") // not shown in reporting as `toThrow<IndexOutOfBoundsException>()` already fails
        }
    }

    @Test
    fun toThrow() {
        expect { throw IllegalStateException("abc") }
            .toThrow<IllegalStateException> { // subject inside this block is of type IllegalStateException
                messageToContain("abc")
            }

        fails { // because wrong type expected (IndexOutOfBoundsException instead of IllegalStateException), but since we use a block...
            expect { throw IllegalStateException("abc") }
                .toThrow<IndexOutOfBoundsException> {
                    messageToContain("abc") // ... reporting mentions that subject's message was expected `to contain: "abc"`
                }
        }
    }

    @Test
    fun notToThrowFeature() {
        expect { "abc" }
            .notToThrow() // subject is now of type String
            .toContain("abc")

        fails {
            expect { throw IllegalStateException("abc") }
                .notToThrow()
        }
    }

    @Test
    fun notToThrow() {
        expect { "abc" }
            .notToThrow { // subject is now of type String
                toEqual("abc")
            }

        fails {
            expect { throw IllegalStateException("abc") }
                .notToThrow {}
        }
    }
}
