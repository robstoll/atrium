package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import kotlin.reflect.KFunction2

abstract class CharSequenceContainsSpecBase : WithAsciiReporter() {
    private val containsProp: KFunction2<Expect<String>, o, CharSequenceContains.Builder<String, NoOpSearchBehaviour>> =
        Expect<String>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KFunction2<Expect<String>, o, NotCheckerOption<String, NotSearchBehaviour>> =
        Expect<String>::containsNot
    protected val containsNot = containsNotProp.name

    private val containsNotFun: KFunction2<Expect<String>, Any, Expect<String>> = Expect<String>::containsNot
    protected val containsNotValues = "${containsNotFun.name} ${Values::class.simpleName}"
    protected val containsRegex = fun1<String, String>(Expect<String>::containsRegex).name
    protected val atLeast = CharSequenceContains.Builder<*, *>::atLeast.name
    protected val butAtMost = AtLeastCheckerOption<*, *>::butAtMost.name
    protected val exactly = CharSequenceContains.Builder<*, *>::exactly.name
    protected val atMost = CharSequenceContains.Builder<*, *>::atMost.name
    protected val notOrAtMost = CharSequenceContains.Builder<*, *>::notOrAtMost.name
    private val regexKFun: KFunction2<
        CharSequenceContains.CheckerOption<*, NoOpSearchBehaviour>,
        String,
        Expect<*>
        > = CharSequenceContains.CheckerOption<*, NoOpSearchBehaviour>::regex
    protected val regex = regexKFun.name
    protected val ignoringCase =
        "${CharSequenceContains.Builder<*, NoOpSearchBehaviour>::ignoring.name} ${case::class.simpleName}"

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1 contains o atLeast 1 value 1
        a1 contains o atMost 2 the Values("a", 1)
        a1 contains o notOrAtMost 2 regex "h|b"
        a1 contains o exactly 2 the RegexPatterns("h|b", "b")
        a1 contains o atLeast 2 matchFor Regex("bla")
        a1 contains o atLeast 2 matchFor All(Regex("bla"), Regex("b"))
        a1 contains o atLeast 2 elementsOf listOf(1, 2)
        a1 containsNot o

        a1 contains o ignoring case atLeast 1 value "a"
        a1 contains o ignoring case atLeast 1 the Values("a", 'b')
        a1 contains o ignoring case atLeast 1 regex "a"
        a1 contains o ignoring case atLeast 1 the RegexPatterns("a", "bl")
        a1 contains o ignoring case atLeast 1 elementsOf listOf(1, 2)

        // skip atLeast
        a1 contains o ignoring case value "a"
        a1 contains o ignoring case the Values("a", 'b')
        a1 contains o ignoring case regex "a"
        a1 contains o ignoring case the RegexPatterns("a", "bl")
        //TODO add to infix as well as fluent
        //a1 contains o ignoring case elementsOf listOf(1, 2)



        a1 and { o contains O atLeast 1 value 1 }
        a1 and { o contains O atMost 2 the Values("a", 1) }
        a1 and { o contains O notOrAtMost 2 regex "h|b" }
        a1 and { o contains O exactly 2 the RegexPatterns("h|b", "b") }
        a1 and { o contains O atLeast 2 matchFor Regex("bla") }
        a1 and { o contains O atLeast 2 matchFor All(Regex("bla"), Regex("b")) }
        a1 and { o contains O atLeast 2 elementsOf listOf(1, 2) }
    }
}
