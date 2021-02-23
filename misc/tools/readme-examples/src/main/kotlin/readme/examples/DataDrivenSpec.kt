package readme.examples

//@formatter:off
//snippet-expectLambda-start
import ch.tutteli.atrium.logic.utils.expectLambda
//snippet-expectLambda-end
//@formatter:on

import readme.examples.utils.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.spekframework.spek2.Spek



/**
 * The tests and error message are written here and automatically placed into the README via generation.
 * The generation is done during the project built. To trigger it manually, you have to run:
 * ```
 * ./gradlew :readme-examples:build
 * ```
 *
 * There are currently three kind of tags supported:
 * - <ex-xy> => places code and output into the tag
 * - <exs-xy> => places code into the tag, output will be put into a tag named <exs-xy-output>
 * - <code> => is not supposed to fail and only the code is put into the code
 *
 * Moreover, all tags can reuse snippets defined in this file with corresponding markers
 */
class DataDrivenSpec : Spek({

    //snippet-data-driven-1-start
    fun myFun(i: Int) = (i + 97).toChar()
    //snippet-data-driven-1-end

    test("ex-data-driven-1") {
        //snippet-data-driven-1-insert

        expect("calling myFun with...") {
            mapOf(
                1 to 'a',
                2 to 'c',
                3 to 'e'
            ).forEach { (arg, result) ->
                feature { f(::myFun, arg) }.toBe(result)
            }
        }
    }

    test("ex-data-driven-2") {
        //snippet-expectLambda-insert

        expect("calling myFun with ...") {
            mapOf(
                1 to expectLambda<Char> { isLessThan('f') },
                2 to expectLambda { toBe('c') },
                3 to expectLambda { isGreaterThan('e') }
            ).forEach { (arg, assertionCreator) ->
                feature({ f(::myFun, arg) }, assertionCreator)
            }
        }
    }

    //snippet-data-driven-3-start
    fun myNullableFun(i: Int) = if (i > 0) i.toString() else null
    //snippet-data-driven-3-end

    test("ex-data-driven-3") {
        //snippet-data-driven-3-insert

        expect("calling myNullableFun with ...") {
            mapOf(
                Int.MIN_VALUE to expectLambda<String> { contains("min") },
                -1 to null,
                0 to null,
                1 to expectLambda { toBe("1") },
                2 to expectLambda { endsWith("2") },
                Int.MAX_VALUE to expectLambda { toBe("max") }
            ).forEach { (arg, assertionCreatorOrNull) ->
                feature { f(::myNullableFun, arg) }.toBeNullIfNullGivenElse(assertionCreatorOrNull)
            }
        }
    }
})
