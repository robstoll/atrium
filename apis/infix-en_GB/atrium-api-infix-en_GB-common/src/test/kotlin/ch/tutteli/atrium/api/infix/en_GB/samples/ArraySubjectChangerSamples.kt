package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ArraySubjectChangerSamples {

    @Test
    fun asListFeature() {
        expect(arrayOf("A", "B")) asList o toEqual listOf("A", "B")
        //                           | subject is now of type List<String>
    }

    @Test
    fun asList() {
        expect(arrayOf("A", "B"))
            .asList { // subject within this expectation-group is of type List<String>
                it toEqual listOf("A", "B")
            } // subject here is back to type Array<String>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(arrayOf("A", "B"))
                .asList {
                    it toContain "C"  // fails
                    it toContain "D"  // still evaluated even though above `toContain` already fails
                    //                   use `asList o` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun asListEOut() {
        expect<Array<out String>>(arrayOf("A", "B"))
            .asList { // subject within this expectation-group is of type List<String>
                it toEqual listOf("A", "B")
            } // subject here is back to type Array<out String>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect<Array<out String>>(arrayOf("A", "B"))
                .asList {
                    it toContain "C" // fails
                    it toContain "D" // still evaluated even though above `toContain` already fails
                    //                  use `asList o` if you want a fail fast behaviour
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
            .asList { // subject within this expectation-group is of type List<Byte>
                it toEqual listOf<Byte>(1, 2, 3)
            } // subject here is back to type Array<Byte>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(byteArrayOf(1, 2, 3))
                .asList {
                    it toContain 98  // fails
                    it toContain 99  // still evaluated even though above `toContain` already fails
                    //                   use `asList o` if you want a fail fast behaviour
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
            .asList { // subject within this expectation-group is of type List<Char>
                it toEqual listOf('A', 'B', 'C')
            } // subject here is back to type Array<Char>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(charArrayOf('A', 'B', 'C'))
                .asList {
                    it toContain 'X'  // fails
                    it toContain 'Y'  // still evaluated even though above `toContain` already fails
                    //                   use `asList o` if you want a fail fast behaviour
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
            .asList { // subject within this expectation-group is of type List<Short>
                it toEqual listOf<Short>(1, 2, 3)
            } // subject here is back to type Array<Short>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(shortArrayOf(1, 2, 3))
                .asList {
                    it toContain 98  // fails
                    it toContain 99  // still evaluated even though above `toContain` already fails
                    //                  use `asList o` if you want a fail fast behaviour
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
            .asList { // subject within this expectation-group is of type List<Int>
                it toEqual listOf(1, 2, 3)
            } // subject here is back to type Array<Int>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(intArrayOf(1, 2, 3))
                .asList {
                    it toContain 98  // fails
                    it toContain 99  // still evaluated even though above `toContain` already fails
                    //                  use `asList o` if you want a fail fast behaviour
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
            .asList { // subject within this expectation-group is of type List<Long>
                it toEqual listOf(1L, 2L, 3L)
            } // subject here is back to type Array<Long>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(longArrayOf(1L, 2L, 3L))
                .asList {
                    it toContain 98L  // fails
                    it toContain 99L  // still evaluated even though above `toContain` already fails
                    //                   use `asList o` if you want a fail fast behaviour
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
            .asList { // subject within this expectation-group is of type List<Float>
                it toEqual listOf(1f, 2f, 3f)
            } // subject here is back to type Array<Float>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(floatArrayOf(1f, 2f, 3f))
                .asList {
                    it toContain 98f  // fails
                    it toContain 99f  // still evaluated even though above `toContain` already fails
                    //                   use `asList o` if you want a fail fast behaviour
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
            .asList { // subject within this expectation-group is of type List<Double>
                it toEqual listOf(1.1, 2.2, 3.3)
            } // subject here is back to type Array<Double>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(doubleArrayOf(1.1, 2.2, 3.3))
                .asList {
                    it toContain 98.1  // fails
                    it toContain 99.2  // still evaluated even though above `toContain` already fails
                    //                    use `asList o` if you want a fail fast behaviour
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
            .asList { // subject within this expectation-group is of type List<Boolean>
                it toEqual listOf(true, false)
            } // subject here is back to type Array<Boolean>

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group
            expect(booleanArrayOf(true, true))
                .asList {
                    it toContain false                               // fails
                    it toContain o inAny order atLeast 3 value true  // still evaluated even though above `toContain` already fails
                    //                                                  use `asList o` if you want a fail fast behaviour
                }
        }
    }
}
