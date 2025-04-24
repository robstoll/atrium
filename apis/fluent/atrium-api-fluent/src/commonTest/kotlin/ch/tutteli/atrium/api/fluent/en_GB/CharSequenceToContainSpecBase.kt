package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.CheckerStep
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.EntryPointStep
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction3
import kotlin.reflect.KProperty

abstract class CharSequenceToContainSpecBase {
    private val toContainProp: KProperty<*> = Expect<String>::toContain
    protected val toContain = toContainProp.name
    private val notToContainProp: KProperty<*> = Expect<String>::notToContain
    protected val toContainNot = notToContainProp.name
    protected val toContainRegex = fun2<String, String, Array<out String>>(Expect<String>::toContainRegex).name
    protected val atLeast = EntryPointStep<CharSequence, *>::atLeast.name
    protected val butAtMost = AtLeastCheckerStep<CharSequence, *>::butAtMost.name
    protected val atMost = EntryPointStep<CharSequence, *>::atMost.name
    protected val exactly = EntryPointStep<CharSequence, *>::exactly.name
    protected val notOrAtMost = EntryPointStep<CharSequence, *>::notOrAtMost.name
    private val regexKFun: KFunction3<
        CheckerStep<CharSequence, NoOpSearchBehaviour>,
        String,
        Array<out String>,
        Expect<*>
        > = CheckerStep<CharSequence, NoOpSearchBehaviour>::regex
    protected val regex = regexKFun.name
    protected val ignoringCase = EntryPointStep<CharSequence, NoOpSearchBehaviour>::ignoringCase.name
    protected val value = CheckerStep<CharSequence, NoOpSearchBehaviour>::value.name
    protected val values = CheckerStep<CharSequence, NoOpSearchBehaviour>::values.name
    protected val elementsOf = EntryPointStep<String, IgnoringCaseSearchBehaviour>::elementsOf.name

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<String> = notImplemented()

        a1.toContain.atLeast(1).value(1)
        a1.toContain.atMost(2).values("a", 1)
        a1.toContain.notOrAtMost(2).regex("h|b")
        a1.toContain.exactly(2).regex("h|b", "b")
        a1.toContain.atLeast(2).matchFor(Regex("bla"))
        a1.toContain.atLeast(2).matchFor(Regex("bla"), Regex("b"))
        a1.toContain.atLeast(2).elementsOf(listOf("a", 2))

        a1.notToContain.value(1)
        a1.notToContain.values("a", 1)
        a1.notToContain.regex("h|b", "b")
        a1.notToContain.matchFor(Regex("bla"))
        a1.notToContain.matchFor(Regex("bla"), Regex("b"))
        a1.notToContain.elementsOf(listOf("a", 2))

        a1.toContain.ignoringCase.atLeast(1).value("a")
        a1.toContain.ignoringCase.atLeast(1).values("a", 'b')
        a1.toContain.ignoringCase.atLeast(1).regex("a")
        a1.toContain.ignoringCase.atLeast(1).regex("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.toContain.ignoringCase.atLeast(1).regex(Regex("a"))
        //a1.toContain.ignoringCase.atLeast(1).regex(Regex("a"), Regex("bl"))
        a1.toContain.ignoringCase.atLeast(1).elementsOf(listOf(1, 2))

        a1.notToContain.ignoringCase.value("a")
        a1.notToContain.ignoringCase.values("a", 'b')
        a1.notToContain.ignoringCase.regex("a")
        a1.notToContain.ignoringCase.regex("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.notToContain.ignoringCase.regex(Regex("a"))
        //a1.notToContain.ignoringCase.regex(Regex("a"), Regex("bl"))
        a1.notToContain.ignoringCase.elementsOf(listOf(1, 2))

        // skip atLeast
        a1.toContain.ignoringCase.value("a")
        a1.toContain.ignoringCase.values("a", 'b')
        a1.toContain.ignoringCase.regex("a")
        a1.toContain.ignoringCase.regex("a", "bl")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1.toContain.ignoringCase.regex(Regex("a"))
        //a1.toContain.ignoringCase.regex(Regex("a"), Regex("bl"))
        a1.toContain.ignoringCase.elementsOf(listOf("a", 2))
    }
}
