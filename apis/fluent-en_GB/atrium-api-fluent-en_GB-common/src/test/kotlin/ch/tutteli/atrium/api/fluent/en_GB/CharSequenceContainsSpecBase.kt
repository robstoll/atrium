package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.name
import kotlin.reflect.KFunction3
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = Expect<String>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Expect<String>::containsNot
    protected val containsNot = containsNotProp.name
    protected val containsRegex = fun2<String, String, Array<out String>>(Expect<String>::containsRegex).name
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
