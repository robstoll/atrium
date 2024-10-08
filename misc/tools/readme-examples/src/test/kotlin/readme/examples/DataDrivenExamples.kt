package readme.examples

import readme.examples.utils.expect
import readme.examples.utils.expectGrouped
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.ExpectationCreator
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class DataDrivenExamples : ReadmeTest {

    //snippet-data-driven-1-start
    fun myFun(i: Int) = (i + 97).toChar()
    //snippet-data-driven-1-end

    @Test
    fun `ex-data-driven-1`() {
        //snippet-data-driven-1-insert

        expectGrouped {
            mapOf(
                1 to 'a',
                2 to 'c',
                3 to 'e'
            ).forEach { (arg, result) ->
                group("calling myFun with $arg") {
                    expect(myFun(arg)).toEqual(result)
                }
            }
        }
    }

    @Test
    fun `ex-data-driven-2`() {
        expectGrouped {
            mapOf<Int, ExpectationCreator<Char>>(
                1 to { toBeLessThan('f') },
                2 to { toEqual('c') },
                3 to { toBeGreaterThan('e') }
            ).forEach { (arg, assertionCreator) ->
                group("calling myFun with $arg") {
                    expect(myFun(arg), assertionCreator)
                }
            }
        }
    }

    //snippet-data-driven-3-start
    fun myNullableFun(i: Int) = if (i > 0) i.toString() else null
    //snippet-data-driven-3-end

    @Test
    fun `ex-data-driven-3`() {
        //snippet-data-driven-3-insert

        expectGrouped {
            mapOf<Int, ExpectationCreator<String>?>(
                Int.MIN_VALUE to { toContain("min") },
                -1 to null,
                0 to null,
                1 to { toEqual("1") },
                2 to { toEndWith("2") },
                Int.MAX_VALUE to { toEqual("max") }
            ).forEach { (arg, assertionCreatorOrNull) ->
                group("calling myFun with $arg") {
                    expect(myNullableFun(arg)).toEqualNullIfNullGivenElse(assertionCreatorOrNull)
                }
            }
        }
    }

    @Test
    fun `ex-data-driven-nesting`() {
        val x1 = 1
        val x2 = 3
        val y = 6

        expectGrouped {
            group("first group") {
                expect(x1).toEqual(2)
                group("sub-group") {
                    expect(x2).toBeGreaterThan(5)
                }
            }
            group("second group") {
                expect(y) {
                    group("sub-group 1") {
                        toBeGreaterThan(0)
                        toBeLessThan(5)
                    }
                    group("sub-group 2") {
                        notToEqual(6)
                    }
                }
            }
        }
    }
}


