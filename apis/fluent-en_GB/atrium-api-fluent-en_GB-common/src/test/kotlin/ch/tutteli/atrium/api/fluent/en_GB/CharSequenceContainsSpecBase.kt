package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.impl.StaticName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction3
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = Expect<String>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Expect<String>::containsNot
    protected val containsNot = containsNotProp.name
    protected val containsRegex = fun2<String, String, Array<out String>>(Expect<String>::containsRegex).name
    protected val atLeast = StaticName.atLeast
    protected val butAtMost = StaticName.butAtMost
    protected val exactly = StaticName.exactly
    protected val atMost = StaticName.atMost
    protected val notOrAtMost = StaticName.notOrAtMost
    private val regexKFun: KFunction3<
        CharSequenceContains.CheckerOption<*, NoOpSearchBehaviour>,
        String,
        Array<out String>,
        Expect<*>
        > = CharSequenceContains.CheckerOption<*, NoOpSearchBehaviour>::regex
    protected val regex = regexKFun.name
    protected val ignoringCase = CharSequenceContains.Builder<*, NoOpSearchBehaviour>::ignoringCase.name

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1.contains.atLeast(1).value(1)
        a1.contains.atMost(2).values("a", 1)
        a1.contains.notOrAtMost(2).regex("h|b")
        a1.contains.exactly(2).regex("h|b", "b")
        a1.contains.atLeast(2).regex(Regex("bla"))
        a1.contains.atLeast(2).regex(Regex("bla"), Regex("b"))
        a1.contains.atLeast(2).elementsOf(listOf("a", 2))

        a1.containsNot.value(1)
        a1.containsNot.values("a", 1)
        a1.containsNot.regex("h|b", "b")
        a1.containsNot.regex(Regex("bla"))
        a1.containsNot.regex(Regex("bla"), Regex("b"))
        a1.containsNot.elementsOf(listOf("a", 2))

        a1.contains.ignoringCase.atLeast(1).value("a")
        a1.contains.ignoringCase.atLeast(1).values("a", 'b')
        a1.contains.ignoringCase.atLeast(1).regex("a")
        a1.contains.ignoringCase.atLeast(1).regex("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.contains.ignoringCase.atLeast(1).regex(Regex("a"))
        //a1.contains.ignoringCase.atLeast(1).regex(Regex("a"), Regex("bl"))
        a1.contains.ignoringCase.atLeast(1).elementsOf(listOf(1, 2))

        a1.containsNot.ignoringCase.value("a")
        a1.containsNot.ignoringCase.values("a", 'b')
        a1.containsNot.ignoringCase.regex("a")
        a1.containsNot.ignoringCase.regex("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.containsNot.ignoringCase.regex(Regex("a"))
        //a1.containsNot.ignoringCase.regex(Regex("a"), Regex("bl"))
        a1.containsNot.ignoringCase.elementsOf(listOf(1, 2))

        // skip atLeast
        a1.contains.ignoringCase.value("a")
        a1.contains.ignoringCase.values("a", 'b')
        a1.contains.ignoringCase.regex("a")
        a1.contains.ignoringCase.regex("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.contains.ignoringCase.regex(Regex("a"))
        //a1.contains.ignoringCase.regex(Regex("a"), Regex("bl"))
        a1.contains.ignoringCase.elementsOf(listOf("a", 2))
    }
}
