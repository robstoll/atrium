package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import kotlin.reflect.KFunction3
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = Expect<String>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Expect<String>::containsNot
    protected val containsNot = containsNotProp.name
    private val containsRegexKFun: KFunction3<Expect<String>, String, Array<out String>, Expect<*>> =
        Expect<String>::containsRegex
    protected val containsRegex = containsRegexKFun.name
    protected val atLeast = CharSequenceContains.Builder<*, *>::atLeast.name
    protected val butAtMost = AtLeastCheckerOption<*, *>::butAtMost.name
    protected val exactly = CharSequenceContains.Builder<*, *>::exactly.name
    protected val atMost = CharSequenceContains.Builder<*, *>::atMost.name
    protected val notOrAtMost = CharSequenceContains.Builder<*, *>::notOrAtMost.name
    private val regexKFun: KFunction3<
        CharSequenceContains.CheckerOption<*, NoOpSearchBehaviour>,
        String,
        Array<out String>,
        Expect<*>
        > = CharSequenceContains.CheckerOption<*, NoOpSearchBehaviour>::regex
    protected val regex = regexKFun.name
    protected val ignoringCase = CharSequenceContains.Builder<*, NoOpSearchBehaviour>::ignoringCase.name
}
