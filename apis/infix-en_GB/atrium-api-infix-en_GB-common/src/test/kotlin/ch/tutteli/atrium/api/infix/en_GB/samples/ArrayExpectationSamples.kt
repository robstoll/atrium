//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ArrayExpectationSamples {

    @Test
    fun asListFeature() {
        expect(arrayOf("A", "B")) asList o toEqual listOf("A", "B")
        //                           | subject is now of type List<String>
    }

    @Test
    fun asList() {
        expect(arrayOf("A", "B"))
            .asList { // subject within this block is of type List<String>
                it toEqual listOf("A", "B")
            } // subject here is back to type Array<String>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(arrayOf("A", "B"))
                .asList {
                    it contains "C"  // fails
                    it contains "D"  // still evaluated even though `contains "C"` already fails
                    //                  use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun asListEOut() {
        expect<Array<out String>>(arrayOf("A", "B"))
            .asList { // subject within this block is of type List<String>
                it toEqual listOf("A", "B")
            } // subject here is back to type Array<out String>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect<Array<out String>>(arrayOf("A", "B"))
                .asList {
                    it contains "C" // fails
                    it contains "D" // still evaluated even though `contains "C"` already fails
                    //                 use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun byteArrAsListFeature() {
        expect(byteArrayOf(1, 2, 3)) asList o toEqual listOf<Byte>(1, 2, 3)
        //                              | subject is now of type List<Byte>
    }

    @Test
    fun byteArrAsList() {
        expect(byteArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Byte>
                it toEqual listOf<Byte>(1, 2, 3)
            } // subject here is back to type Array<Byte>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(byteArrayOf(1, 2, 3))
                .asList {
                    it contains 98  // fails
                    it contains 99  // still evaluated even though `contains 98` already fails
                    //                 use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun charArrAsListFeature() {
        expect(charArrayOf('A', 'B', 'C')) asList o toEqual listOf('A', 'B', 'C')
        //                                    | subject is now of type List<Char>
    }

    @Test
    fun charArrAsList() {
        expect(charArrayOf('A', 'B', 'C'))
            .asList { // subject within this block is of type List<Char>
                it toEqual listOf('A', 'B', 'C')
            } // subject here is back to type Array<Char>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(charArrayOf('A', 'B', 'C'))
                .asList {
                    it contains 'X'  // fails
                    it contains 'Y'  // still evaluated even though `contains 'X'` already fails
                    //                  use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun shortArrAsListFeature() {
        expect(shortArrayOf(1, 2, 3)) asList o toEqual listOf<Short>(1, 2, 3)
        //                               | subject is now of type List<Short>
    }

    @Test
    fun shortArrAsList() {
        expect(shortArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Short>
                it toEqual listOf<Short>(1, 2, 3)
            } // subject here is back to type Array<Short>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(shortArrayOf(1, 2, 3))
                .asList {
                    it contains 98  // fails
                    it contains 99  // still evaluated even though `contains 98` already fails
                    //                 use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun intArrAsListFeature() {
        expect(intArrayOf(1, 2, 3)) asList o toEqual listOf(1, 2, 3)
        //                             | subject is now of type List<Int>
    }

    @Test
    fun intArrAsList() {
        expect(intArrayOf(1, 2, 3))
            .asList { // subject within this block is of type List<Int>
                it toEqual listOf(1, 2, 3)
            } // subject here is back to type Array<Int>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(intArrayOf(1, 2, 3))
                .asList {
                    it contains 98  // fails
                    it contains 99  // still evaluated even though `contains 98` already fails
                    //                 use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun longArrAsListFeature() {
        expect(longArrayOf(1L, 2L, 3L)) asList o toEqual listOf(1L, 2L, 3L)
        //                                 | subject is now of type List<Long>
    }

    @Test
    fun longArrAsList() {
        expect(longArrayOf(1L, 2L, 3L))
            .asList { // subject within this block is of type List<Long>
                it toEqual listOf(1L, 2L, 3L)
            } // subject here is back to type Array<Long>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(longArrayOf(1L, 2L, 3L))
                .asList {
                    it contains 98L  // fails
                    it contains 99L  // still evaluated even though `contains 98L` already fails
                    //                  use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun floatArrAsListFeature() {
        expect(floatArrayOf(1f, 2f, 3f)) asList o toEqual listOf(1f, 2f, 3f)
        //                                  | subject is now of type List<Float>
    }

    @Test
    fun floatArrAsList() {
        expect(floatArrayOf(1f, 2f, 3f))
            .asList { // subject within this block is of type List<Float>
                it toEqual listOf(1f, 2f, 3f)
            } // subject here is back to type Array<Float>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(floatArrayOf(1f, 2f, 3f))
                .asList {
                    it contains 98f  // fails
                    it contains 99f  // still evaluated even though `contains 98f` already fails
                    //                  use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun doubleArrAsListFeature() {
        expect(doubleArrayOf(1.1, 2.2, 3.3)) asList o toEqual listOf(1.1, 2.2, 3.3)
        //                                      | subject is now of type List<Double>
    }

    @Test
    fun doubleArrAsList() {
        expect(doubleArrayOf(1.1, 2.2, 3.3))
            .asList { // subject within this block is of type List<Double>
                it toEqual listOf(1.1, 2.2, 3.3)
            } // subject here is back to type Array<Double>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(doubleArrayOf(1.1, 2.2, 3.3))
                .asList {
                    it contains 98.1  // fails
                    it contains 99.2  // still evaluated even though `contains 98.1` already fails
                    //                   use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun booleanArrAsListFeature() {
        expect(booleanArrayOf(true, false)) asList o toEqual listOf(true, false)
        //                                     | subject is now of type List<Boolean>
    }

    @Test
    fun booleanArrAsList() {
        expect(booleanArrayOf(true, false))
            .asList { // subject within this block is of type List<Boolean>
                it toEqual listOf(true, false)
            } // subject here is back to type Array<Boolean>

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(booleanArrayOf(true, true))
                .asList {
                    it contains false  //                              fails
                    it contains o inAny order atLeast 3 value true  // still evaluated even though `contains false` already fails
                    //                                                 use `asList o` if you want a fail fast behaviour
                }
        }
    }
}
