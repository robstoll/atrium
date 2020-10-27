package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ArrayAssertionSamples {

    @Test
    fun asListFeature() {
        expect(arrayOf("A", "B"))
            .asList()  // subject is now of type List<String>
            .toBe(listOf("A", "B"))
    }

    @Test
    fun asList() {
        expect(arrayOf("A", "B"))
            .asList { // subject within this block is of type List<String>
                toBe(listOf("A", "B"))
            } // subject here is back to type Array<String>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(arrayOf("A", "B"))
                .asList {
                    contains("C")  // fails
                    contains("D")  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun asListEOut() {
        expect<Array<out String>>(arrayOf("A", "B"))
            .asList { // subject within this block is of type List<String>
                toBe(listOf("A", "B"))
            } // subject here is back to type Array<out String>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect<Array<out String>>(arrayOf("A", "B"))
                .asList {
                    contains("C")  // fails
                    contains("D")  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun byteArrAsListFeature() {
        expect(byteArrayOf(1, 2, 3))
            .asList() // subject is now of type List<Byte>
            .toBe(listOf<Byte>(1, 2, 3))
    }

    @Test
    fun byteArrAsList() {
        expect(byteArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Byte>
                toBe(listOf<Byte>(1, 2, 3))
            } // subject here is back to type Array<Byte>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(byteArrayOf(1, 2, 3))
                .asList {
                    contains(98)  // fails
                    contains(99)  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun charArrAsListFeature() {
        expect(charArrayOf('A', 'B', 'C'))
            .asList() // subject is now of type List<Char>
            .toBe(listOf('A', 'B', 'C'))
    }

    @Test
    fun charArrAsList() {
        expect(charArrayOf('A', 'B', 'C'))
            .asList { // subject within this block is of type List<Char>
                toBe(listOf('A', 'B', 'C'))
            } // subject here is back to type Array<Char>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(charArrayOf('A', 'B', 'C'))
                .asList {
                    contains('X')  // fails
                    contains('Y')  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun shortArrAsListFeature() {
        expect(shortArrayOf(1, 2, 3))
            .asList() // subject is now of type List<Short>
            .toBe(listOf<Short>(1, 2, 3))
    }

    @Test
    fun shortArrAsList() {
        expect(shortArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Short>
                toBe(listOf<Short>(1, 2, 3))
            } // subject here is back to type Array<Short>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(shortArrayOf(1, 2, 3))
                .asList {
                    contains(98)  // fails
                    contains(99)  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun intArrAsListFeature() {
        expect(intArrayOf(1, 2, 3))
            .asList() // subject is now of type List<Int>
            .toBe(listOf(1, 2, 3))
    }

    @Test
    fun intArrAsList() {
        expect(intArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Int>
                toBe(listOf(1, 2, 3))
            } // subject here is back to type Array<Int>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(intArrayOf(1, 2, 3))
                .asList {
                    contains(98)  // fails
                    contains(99)  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun longArrAsListFeature() {
        expect(longArrayOf(1L, 2L, 3L)) // subject is now of type List<Long>
            .asList()
            .toBe(listOf(1L, 2L, 3L))
    }

    @Test
    fun longArrAsList() {
        expect(longArrayOf(1L, 2L, 3L))
            .asList { // subject within this block is of type List<Long>
                toBe(listOf(1L, 2L, 3L))
            } // subject here is back to type Array<Long>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(longArrayOf(1L, 2L, 3L))
                .asList {
                    contains(98L)  // fails
                    contains(99L)  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun floatArrAsListFeature() {
        expect(floatArrayOf(1f, 2f, 3f))
            .asList() // subject is now of type List<Float>
            .toBe(listOf(1f, 2f, 3f))
    }

    @Test
    fun floatArrAsList() {
        expect(floatArrayOf(1f, 2f, 3f))
            .asList { // subject within this block is of type List<Float>
                toBe(listOf(1f, 2f, 3f))
            } // subject here is back to type Array<Float>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(floatArrayOf(1f, 2f, 3f))
                .asList {
                    contains(98f)  // fails
                    contains(99f)  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun doubleArrAsListFeature() {
        expect(doubleArrayOf(1.1, 2.2, 3.3))
            .asList() // subject is now of type List<Double>
            .toBe(listOf(1.1, 2.2, 3.3))
    }

    @Test
    fun doubleArrAsList() {
        expect(doubleArrayOf(1.1, 2.2, 3.3))
            .asList { // subject within this block is of type List<Double>
                toBe(listOf(1.1, 2.2, 3.3))
            } // subject here is back to type Array<Double>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(doubleArrayOf(1.1, 2.2, 3.3))
                .asList {
                    contains(98.1)  // fails
                    contains(99.2)  // still evaluated, use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun booleanArrAsListFeature() {
        expect(booleanArrayOf(true, false))
            .asList() // subject is now of type List<Boolean>
            .toBe(listOf(true, false))
    }

    @Test
    fun booleanArrAsList() {
        expect(booleanArrayOf(true, false))
            .asList { // subject within this block is of type List<Boolean>
                toBe(listOf(true, false))
            } // subject here is back to type Array<Boolean>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(booleanArrayOf(true, true))
                .asList {
                    // fails
                    contains(false)

                    // still evaluated, use `.asList().` if you want a fail fast behaviour
                    contains.inAnyOrder.atLeast(3).value(true)
                }
        }
    }
}
