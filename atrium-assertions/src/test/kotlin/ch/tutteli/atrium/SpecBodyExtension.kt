package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import org.jetbrains.spek.api.dsl.SpecBody

fun <T : Any> SpecBody.checkNarrowingAssertion(description: String,
                                               act: (IAssertionPlant<T>.() -> Unit) -> Unit,
                                               immediate: (IAssertionPlant<T>.() -> Unit),
                                               lazy: (IAssertionPlant<T>.() -> Unit),
                                               vararg otherMethods: Pair<String, (IAssertionPlant<T>.() -> Unit)>) {
    checkGenericNarrowingAssertion(description, act, immediate, lazy, *otherMethods)
}

fun <T> SpecBody.checkNarrowingNullableAssertion(description: String,
                                                 act: (IAssertionPlantNullable<T>.() -> Unit) -> Unit,
                                                 immediate: (IAssertionPlantNullable<T>.() -> Unit),
                                                 lazy: (IAssertionPlantNullable<T>.() -> Unit),
                                                 vararg otherMethods: Pair<String, (IAssertionPlantNullable<T>.() -> Unit)>) {
    checkGenericNarrowingAssertion(description, act, immediate, lazy, *otherMethods)
}

internal fun <T> SpecBody.checkGenericNarrowingAssertion(
    description: String, act: (T.() -> Unit) -> Unit,
    immediate: (T.() -> Unit), lazy: (T.() -> Unit), vararg otherMethods: Pair<String, (T.() -> Unit)>) {

    group(description) {
        mapOf("immediate" to immediate, "lazy" to lazy, *otherMethods).forEach { checkMethod, assertion ->
            test("in case of $checkMethod evaluation") {
                act(assertion)
            }
        }
    }
}


