package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ThrowableExpectationSamples {

    @Test
    fun messageToContain() {
        expect(RuntimeException("abc")).messageToContain("b")

        fails {
            expect(RuntimeException("abc")).messageToContain("d")
        }
    }

    @Test
    fun messageFeature() {
        expect(RuntimeException("abc")).message.toContain("a")

        fails {
            expect(RuntimeException("abc")).message.toContain("d")
        }
    }

    @Test
    fun message() {
        expect(RuntimeException("abc")).message {
            toContain("a")
        }

        fails {
            expect(RuntimeException("abc")).message {
                toContain("d")
            }
        }
    }

    @Test
    fun causeFeature() {
        expect(IllegalStateException(IndexOutOfBoundsException())).cause<Throwable>()
            .toBeAnInstanceOf<IndexOutOfBoundsException>()

        fails {
            expect(IllegalStateException(IndexOutOfBoundsException())).cause<Throwable>()
                .toBeAnInstanceOf<IllegalStateException>()
        }
    }

    @Test
    fun cause() {
        expect(IllegalStateException(IndexOutOfBoundsException())).cause<Throwable> {
            toBeAnInstanceOf<IndexOutOfBoundsException>()
        }

        fails {
            expect(IllegalStateException(IndexOutOfBoundsException())).cause<Throwable> {
                toBeAnInstanceOf<IllegalStateException>()
            }
        }
    }
}
