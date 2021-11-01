package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ArraySubjectChangerSamples {

    @Test
    fun asListFeature() {
        expect(arrayOf("A", "B"))
            .asList()  // subject is now of type List<String>
            .toEqual(listOf("A", "B"))

        fails {
            expect(arrayOf("A", "B"))
                .asList()  // subject is now of type List<String>
                .toContain("C")  // fails
                .toContain("D")  // not reported because above `toContain` already fails
            //                      use `.asList { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun asList() {
        expect(arrayOf("A", "B"))
            .asList { // subject within this block is of type List<String>
                toEqual(listOf("A", "B"))
            } // subject here is back to type Array<String>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(arrayOf("A", "B"))
                .asList {
                    toContain("C")  // fails
                    toContain("D")  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun asListEOut() {
        expect<Array<out String>>(arrayOf("A", "B"))
            .asList { // subject within this block is of type List<String>
                toEqual(listOf("A", "B"))
            } // subject here is back to type Array<out String>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect<Array<out String>>(arrayOf("A", "B"))
                .asList {
                    toContain("C")  // fails
                    toContain("D")  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun byteArrAsListFeature() {
        expect(byteArrayOf(1, 2, 3))
            .asList() // subject is now of type List<Byte>
            .toEqual(listOf<Byte>(1, 2, 3))
    }

    @Test
    fun byteArrAsList() {
        expect(byteArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Byte>
                toEqual(listOf<Byte>(1, 2, 3))
            } // subject here is back to type Array<Byte>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(byteArrayOf(1, 2, 3))
                .asList {
                    toContain(98)  // fails
                    toContain(99)  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun charArrAsListFeature() {
        expect(charArrayOf('A', 'B', 'C'))
            .asList() // subject is now of type List<Char>
            .toEqual(listOf('A', 'B', 'C'))
    }

    @Test
    fun charArrAsList() {
        expect(charArrayOf('A', 'B', 'C'))
            .asList { // subject within this block is of type List<Char>
                toEqual(listOf('A', 'B', 'C'))
            } // subject here is back to type Array<Char>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(charArrayOf('A', 'B', 'C'))
                .asList {
                    toContain('X')  // fails
                    toContain('Y')  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun shortArrAsListFeature() {
        expect(shortArrayOf(1, 2, 3))
            .asList() // subject is now of type List<Short>
            .toEqual(listOf<Short>(1, 2, 3))
    }

    @Test
    fun shortArrAsList() {
        expect(shortArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Short>
                toEqual(listOf<Short>(1, 2, 3))
            } // subject here is back to type Array<Short>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(shortArrayOf(1, 2, 3))
                .asList {
                    toContain(98)  // fails
                    toContain(99)  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun intArrAsListFeature() {
        expect(intArrayOf(1, 2, 3))
            .asList() // subject is now of type List<Int>
            .toEqual(listOf(1, 2, 3))
    }

    @Test
    fun intArrAsList() {
        expect(intArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Int>
                toEqual(listOf(1, 2, 3))
            } // subject here is back to type Array<Int>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(intArrayOf(1, 2, 3))
                .asList {
                    toContain(98)  // fails
                    toContain(99)  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun longArrAsListFeature() {
        expect(longArrayOf(1L, 2L, 3L)) // subject is now of type List<Long>
            .asList()
            .toEqual(listOf(1L, 2L, 3L))
    }

    @Test
    fun longArrAsList() {
        expect(longArrayOf(1L, 2L, 3L))
            .asList { // subject within this block is of type List<Long>
                toEqual(listOf(1L, 2L, 3L))
            } // subject here is back to type Array<Long>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(longArrayOf(1L, 2L, 3L))
                .asList {
                    toContain(98L)  // fails
                    toContain(99L)  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun floatArrAsListFeature() {
        expect(floatArrayOf(1f, 2f, 3f))
            .asList() // subject is now of type List<Float>
            .toEqual(listOf(1f, 2f, 3f))
    }

    @Test
    fun floatArrAsList() {
        expect(floatArrayOf(1f, 2f, 3f))
            .asList { // subject within this block is of type List<Float>
                toEqual(listOf(1f, 2f, 3f))
            } // subject here is back to type Array<Float>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(floatArrayOf(1f, 2f, 3f))
                .asList {
                    toContain(98f)  // fails
                    toContain(99f)  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun doubleArrAsListFeature() {
        expect(doubleArrayOf(1.1, 2.2, 3.3))
            .asList() // subject is now of type List<Double>
            .toEqual(listOf(1.1, 2.2, 3.3))
    }

    @Test
    fun doubleArrAsList() {
        expect(doubleArrayOf(1.1, 2.2, 3.3))
            .asList { // subject within this block is of type List<Double>
                toEqual(listOf(1.1, 2.2, 3.3))
            } // subject here is back to type Array<Double>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(doubleArrayOf(1.1, 2.2, 3.3))
                .asList {
                    toContain(98.1)  // fails
                    toContain(99.2)  // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun booleanArrAsListFeature() {
        expect(booleanArrayOf(true, false))
            .asList() // subject is now of type List<Boolean>
            .toEqual(listOf(true, false))
    }

    @Test
    fun booleanArrAsList() {
        expect(booleanArrayOf(true, false))
            .asList { // subject within this block is of type List<Boolean>
                toEqual(listOf(true, false))
            } // subject here is back to type Array<Boolean>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(booleanArrayOf(true, true))
                .asList {
                    // fails
                    toContain(false)

                    // still evaluated even though above `toContain` already fails
                    //                 use `.asList().` if you want a fail fast behaviour
                    toContain.inAnyOrder.atLeast(3).value(true)
                }
        }
    }
}
