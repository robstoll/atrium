package readme.examples

//@formatter:off
//snippet-import-AssertionContainer-start
import ch.tutteli.atrium.creating.AssertionContainer
//snippet-import-AssertionContainer-end
//snippet-import-logic-start
import ch.tutteli.atrium.logic.*
//snippet-import-logic-end
//@formatter:on
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic

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

object I18nSpec : Spek({

    test("code-i18n-1") {
        fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> =
            createAndAddAssertion(DescriptionIntAssertion.IS_MULTIPLE_OF, base) { it % base == 0 }

        //snippet-DescriptionIntAssertion-insert
    }

    test("code-i18n-2") {
        fun Expect<Int>.isEven(): Expect<Int> =
            createAndAddAssertion(DescriptionBasic.IS, DescriptionIntAssertions.EVEN) { it % 2 == 0 }

        //snippet-DescriptionIntAssertions-insert
    }

    //snippet-i18n-3a-start
    fun AssertionContainer<Int>.isMultipleOf(base: Int): Assertion =
        createDescriptiveAssertion(DescriptionIntAssertion.IS_MULTIPLE_OF, base) { it % base == 0 }
    //snippet-i18n-3a-end

    test("code-i18n-3a") {
        //snippet-import-AssertionContainer-insert

        //snippet-i18n-3a-insert
    }
    test("code-i18n-3b") {
        //snippet-import-logic-insert

        fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> =
            _logicAppend { isMultipleOf(base) }
    }
    test("code-i18n-3c") {
        //snippet-import-logic-insert

        fun Expect<Int>.istVielfachesVon(base: Int): Expect<Int> =
            _logicAppend { isMultipleOf(base) }
    }
})

//snippet-DescriptionIntAssertion-start
enum class DescriptionIntAssertion(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
//snippet-DescriptionIntAssertion-end


//snippet-DescriptionIntAssertions-start
enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    EVEN("an even number")
}
//snippet-DescriptionIntAssertions-end
