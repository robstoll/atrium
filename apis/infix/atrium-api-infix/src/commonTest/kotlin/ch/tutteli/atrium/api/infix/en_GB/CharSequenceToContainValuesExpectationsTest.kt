package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun2
import ch.tutteli.atrium.specs.Fun3
import ch.tutteli.atrium.specs.integration.*
import kotlin.test.Test

class CharSequenceToContainValuesExpectationsTest : AbstractCharSequenceToContainValuesExpectationsTest(
    getAtLeastTriple(),
    getAtLeastIgnoringCaseTriple(),
    getShortcutTriple(),
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple()
) {
    @Test
    fun ambiguityTest() {
        val expectText = expect("Hello my name is Robert")
        expectText toContain o atLeast 1 value "Hello"
        expectText toContain o atMost 10 value "name"
    }

    companion object : CharSequenceToContainSpecBase() {
        private fun getAtLeastTriple(): Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>> =
            { what: String, times: String -> "$toContain $what $atLeast $times" } to
                ("$toContain o $atLeast $value" to fun Expect<CharSequence>.(atLeast: Int, a: String, aX: Array<out String>): Expect<CharSequence> {
                    this toContain o atLeast atLeast value a
                    return this
                })

        private fun getAtLeastIgnoringCaseTriple(): Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>> =
            { what: String, times: String -> "$toContain ignoring case $what $atLeast $times" } to
                ("$toContain o $atLeast $ignoringCase $value" to fun Expect<CharSequence>.(atLeast: Int, a: String, aX: Array<out String>): Expect<CharSequence> {
                    return this
                })

        private fun getShortcutTriple(): Pair<(String, String) -> String, Fun2<CharSequence, String, Array<out String>>> =
            { what: String, _: String -> "$toContain $what" } to
                ("$toContain value" to fun Expect<CharSequence>.(a: String, aX: Array<out String>): Expect<CharSequence> {
                    this toContain value a
                    return this
                })

        private fun getAtMostTriple(): Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>> =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain o $atMost $value" to fun Expect<CharSequence>.(atMost: Int, a: String, aX: Array<out String>): Expect<CharSequence> {
                    this toContain o atMost atMost value a
                    return this
                })

        private fun getAtMostIgnoringCaseTriple(): Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>> =
            { what: String, times: String -> "$toContain ignoring case $what $atMost $times" } to
                ("$toContain o $ignoringCase $atMost $value" to fun Expect<CharSequence>.(atMost: Int, a: String, aX: Array<out String>): Expect<CharSequence> {
                    return this
                })
    }
}
