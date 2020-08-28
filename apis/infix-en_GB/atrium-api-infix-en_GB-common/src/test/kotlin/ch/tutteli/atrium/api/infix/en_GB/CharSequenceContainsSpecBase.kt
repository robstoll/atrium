package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.impl.StaticName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerOption
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
    protected val containsNotValues = "${containsNotFun.name} values"
    protected val containsRegex = fun1<String, String>(Expect<String>::containsRegex).name
    protected val atLeast = StaticName.atLeast
    protected val butAtMost = StaticName.butAtMost
    protected val exactly = StaticName.exactly
    protected val atMost = StaticName.atMost
    protected val notOrAtMost = StaticName.notOrAtMost
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
        a1 contains o atMost 2 the values("a", 1)
        a1 contains o notOrAtMost 2 regex "h|b"
        a1 contains o exactly 2 the regexPatterns("h|b", "b")
        a1 contains o atLeast 2 matchFor Regex("bla")
        a1 contains o atLeast 2 matchFor all(Regex("bla"), Regex("b"))
        a1 contains o atLeast 2 elementsOf listOf(1, 2)

        a1 containsNot o value "a"
        a1 containsNot o the values("a", 'b')
        a1 containsNot o regex "a"
        a1 containsNot o the regexPatterns("a", "bl")
        a1 containsNot o matchFor Regex("a")
        a1 containsNot o matchFor all(Regex("a"), Regex("bl"))
        a1 containsNot o elementsOf listOf(1, 2)

        a1 contains o ignoring case atLeast 1 value "a"
        a1 contains o ignoring case atLeast 1 the values("a", 'b')
        a1 contains o ignoring case atLeast 1 regex "a"
        a1 contains o ignoring case atLeast 1 the regexPatterns("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1 contains o ignoring case atLeast 1 matchFor Regex("a")
        //a1 contains o ignoring case atLeast 1 matchFor all(Regex("a"), Regex("bl"))
        a1 contains o ignoring case atLeast 1 elementsOf listOf(1, 2)

        a1 containsNot o ignoring case value "a"
        a1 containsNot o ignoring case the values("a", 'b')
        a1 containsNot o ignoring case regex "a"
        a1 containsNot o ignoring case the regexPatterns("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1 containsNot o ignoring case matchFor Regex("a")
        //a1 containsNot o ignoring case matchFor all(Regex("a"), Regex("bl"))
        a1 containsNot o ignoring case elementsOf listOf(1, 2)

        // skip atLeast
        a1 contains o ignoring case value "a"
        a1 contains o ignoring case the values("a", 'b')
        a1 contains o ignoring case regex "a"
        a1 contains o ignoring case the regexPatterns("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1 contains o ignoring case matchFor Regex("a")
        //a1 contains o ignoring case matchFor all(Regex("a"), Regex("bl"))
        a1 contains o ignoring case elementsOf listOf("a", 2)

        a1 and { it contains o atLeast 1 value 1 }
        a1 and { it contains o atMost 2 the values("a", 1) }
        a1 and { it contains o notOrAtMost 2 regex "h|b" }
        a1 and { it contains o exactly 2 the regexPatterns("h|b", "b") }
        a1 and { it contains o atLeast 2 matchFor Regex("bla") }
        a1 and { it contains o atLeast 2 matchFor all(Regex("bla"), Regex("b")) }
        a1 and { it contains o atLeast 2 elementsOf listOf(1, 2) }
    }
}
