package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.name
import kotlin.reflect.KFunction3
import kotlin.reflect.KProperty
import kotlin.test.Test

abstract class CharSequenceToContainSpecBase {
    private val toContainProp: KProperty<*> = Expect<String>::toContain
    protected val toContain = toContainProp.name
    private val notToContainProp: KProperty<*> = Expect<String>::notToContain
    protected val toContainNot = notToContainProp.name
    protected val toContainRegex = fun2<String, String, Array<out String>>(Expect<String>::toContainRegex).name
    protected val atLeast = CharSequenceContains.EntryPointStep<CharSequence, *>::atLeast.name
    protected val butAtMost = AtLeastCheckerStep<CharSequence, *>::butAtMost.name
    protected val atMost = CharSequenceContains.EntryPointStep<CharSequence, *>::atMost.name
    protected val exactly = CharSequenceContains.EntryPointStep<CharSequence, *>::exactly.name
    protected val notOrAtMost = CharSequenceContains.EntryPointStep<CharSequence, *>::notOrAtMost.name
    private val regexKFun: KFunction3<
        CharSequenceContains.CheckerStep<CharSequence, NoOpSearchBehaviour>,
        String,
        Array<out String>,
        Expect<*>
        > = CharSequenceContains.CheckerStep<CharSequence, NoOpSearchBehaviour>::regex
    protected val regex = regexKFun.name
    protected val ignoringCase = CharSequenceContains.EntryPointStep<CharSequence, NoOpSearchBehaviour>::ignoringCase.name
    protected val value = CharSequenceContains.CheckerStep<CharSequence, NoOpSearchBehaviour>::value.name
    protected val values = CharSequenceContains.CheckerStep<CharSequence, NoOpSearchBehaviour>::values.name
    protected val elementsOf = CharSequenceContains.EntryPointStep<String, IgnoringCaseSearchBehaviour>::elementsOf.name

    @Test
    fun ambiguityTest() {
        val a1: Expect<CharSequence> = expect("Hello my name is Robert")

        a1.toContain.atLeast(1).value('R')
        a1.toContain.atMost(2).values('l', 'm')
        a1.toContain.notOrAtMost(2).regex("H|R")
        a1.toContain.exactly(2).regex("H|R", "l.")
        a1.toContain.atLeast(2).matchFor(Regex("\\w"))
        a1.toContain.atLeast(2).matchFor(Regex("\\w"), Regex("\\s"))
        a1.toContain.atLeast(2).elementsOf(listOf('l', 'm'))

        a1.notToContain.value('E')
        a1.notToContain.values('L', 'M')
        a1.notToContain.regex("h|E", "L.")
        a1.notToContain.matchFor(Regex("\\d"))
        a1.notToContain.matchFor(Regex("\\d"), Regex("\\s{2}"))
        a1.notToContain.elementsOf(listOf('L', 'M'))

        a1.toContain.ignoringCase.atLeast(1).value('E')
        a1.toContain.ignoringCase.atLeast(1).values('L', 'M')
        a1.toContain.ignoringCase.atLeast(1).regex("h|M")
        a1.toContain.ignoringCase.atLeast(1).regex("h|M", "\\s")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.toContain.ignoringCase.atLeast(1).regex(Regex("a"))
        //a1.toContain.ignoringCase.atLeast(1).regex(Regex("a"), Regex("bl"))
        a1.toContain.ignoringCase.atLeast(1).elementsOf(listOf('L', 'M'))

        a1.notToContain.ignoringCase.value('c')
        a1.notToContain.ignoringCase.values('c', 'D')
        a1.notToContain.ignoringCase.regex("l\\s")
        a1.notToContain.ignoringCase.regex("l\\s", "l{3}")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.notToContain.ignoringCase.regex(Regex("a"))
        //a1.notToContain.ignoringCase.regex(Regex("a"), Regex("bl"))
        a1.notToContain.ignoringCase.elementsOf(listOf('c', 'D'))

        // skip atLeast
        a1.toContain.ignoringCase.value('E')
        a1.toContain.ignoringCase.values('L', 'M')
        a1.toContain.ignoringCase.regex("h|M")
        a1.toContain.ignoringCase.regex("h|M", "\\s")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.toContain.ignoringCase.regex(Regex("a"))
        //a1.toContain.ignoringCase.regex(Regex("a"), Regex("bl"))
        a1.toContain.ignoringCase.elementsOf(listOf('L', 'M'))
    }
}
