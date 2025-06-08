package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import kotlin.reflect.KFunction2
import kotlin.test.Test

abstract class CharSequenceToContainSpecBase {
    private val toContainProp: KFunction2<Expect<String>, o, CharSequenceContains.EntryPointStep<String, NoOpSearchBehaviour>> =
        Expect<String>::toContain
    protected val toContain = toContainProp.name
    private val notToContainProp: KFunction2<Expect<String>, o, NotCheckerStep<String, NotSearchBehaviour>> =
        Expect<String>::notToContain
    protected val notToContain = notToContainProp.name

    private val notToContainFun: KFunction2<Expect<String>, Any, Expect<String>> = Expect<String>::notToContain
    protected val notToContainValues = "${notToContainFun.name} values"
    protected val toContainRegex = fun1<String, String>(Expect<String>::toContainRegex).name
    protected val atLeast = CharSequenceContains.EntryPointStep<CharSequence, *>::atLeast.name
    protected val butAtMost = AtLeastCheckerStep<CharSequence, *>::butAtMost.name
    protected val atMost = CharSequenceContains.EntryPointStep<CharSequence, *>::atMost.name
    protected val exactly = CharSequenceContains.EntryPointStep<CharSequence, *>::exactly.name
    protected val notOrAtMost = CharSequenceContains.EntryPointStep<CharSequence, *>::notOrAtMost.name
    private val regexKFun: KFunction2<
        CharSequenceContains.CheckerStep<CharSequence, NoOpSearchBehaviour>,
        String,
        Expect<*>
        > = CharSequenceContains.CheckerStep<CharSequence, NoOpSearchBehaviour>::regex
    protected val regex = regexKFun.name
    protected val ignoringCase =
        "${CharSequenceContains.EntryPointStep<CharSequence, NoOpSearchBehaviour>::ignoring.name} ${case::class.simpleName}"
    protected val value = CharSequenceContains.CheckerStep<CharSequence, NoOpSearchBehaviour>::value.name
    protected val values = "the values"
    protected val elementsOf = CharSequenceContains.EntryPointStep<String, IgnoringCaseSearchBehaviour>::elementsOf.name

    @Test
    fun ambiguityTest() {
        val a1: Expect<CharSequence> = expect("Hello my name is Robert")

        a1 toContain o atLeast 1 value 'R'
        a1 toContain o atMost 2 the values('l', 'm')
        a1 toContain o notOrAtMost 2 regex "H|R"
        a1 toContain o exactly 2 the regexPatterns("H|R", "l.")
        a1 toContain o atLeast 2 matchFor Regex("\\w")
        a1 toContain o atLeast 2 matchFor all(Regex("\\w"), Regex("\\s"))
        a1 toContain o atLeast 2 elementsOf listOf('l', 'm')

        a1 notToContain o value 'E'
        a1 notToContain o the values('L', 'M')
        a1 notToContain o regex "h|E"
        a1 notToContain o the regexPatterns("h|E", "O{2}")
        a1 notToContain o matchFor Regex("\\d")
        a1 notToContain o matchFor all(Regex("\\d"), Regex("\\s{2}"))
        a1 notToContain o elementsOf listOf('L', 'M')

        a1 toContain o ignoring case atLeast 1 value 'E'
        a1 toContain o ignoring case atLeast 1 the values('L', 'M')
        a1 toContain o ignoring case atLeast 1 regex "h|M"
        a1 toContain o ignoring case atLeast 1 the regexPatterns("h|M", "\\s")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1 toContain o ignoring case atLeast 1 matchFor Regex("a")
        //a1 toContain o ignoring case atLeast 1 matchFor all(Regex("a"), Regex("bl"))
        a1 toContain o ignoring case atLeast 1 elementsOf listOf('L', 'M')

        a1 notToContain o ignoring case value 'c'
        a1 notToContain o ignoring case the values('c', 'D')
        a1 notToContain o ignoring case regex "l\\s"
        a1 notToContain o ignoring case the regexPatterns("l\\s", "l{3}")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1 notToContain o ignoring case matchFor Regex("a")
        //a1 notToContain o ignoring case matchFor all(Regex("a"), Regex("bl"))
        a1 notToContain o ignoring case elementsOf listOf('c', 'D')

        // skip atLeast
        a1 toContain o ignoring case value 'E'
        a1 toContain o ignoring case the values('L', 'M')
        a1 toContain o ignoring case regex "h|M"
        a1 toContain o ignoring case the regexPatterns("h|M", "\\s")
        // not supported on purpose as one can specify an ignore case flag for Regex
        // and hence these would be a second way to do the same thing
        //a1 toContain o ignoring case matchFor Regex("a")
        //a1 toContain o ignoring case matchFor all(Regex("a"), Regex("bl"))
        a1 toContain o ignoring case elementsOf listOf('L', 'M')

        a1 and { it toContain o atLeast 1 value 'R' }
        a1 and { it toContain o atMost 2 the values('l', 'm') }
        a1 and { it toContain o notOrAtMost 2 regex "H|R" }
        a1 and { it toContain o exactly 2 the regexPatterns("H|R", "l.") }
        a1 and { it toContain o atLeast 2 matchFor Regex("\\w") }
        a1 and { it toContain o atLeast 2 matchFor all(Regex("\\w"), Regex("\\s")) }
        a1 and { it toContain o atLeast 2 elementsOf listOf('l', 'm') }
    }
}
