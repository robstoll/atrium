package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import kotlin.reflect.*

@Deprecated("use AssertImpl.feature.property, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.property(plant, property)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(plant, property)

@Deprecated("use AssertImpl.feature.property, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.property(plant, property, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(plant, property, assertionCreator)

@Deprecated("use AssertImpl.feature.property, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.property(plant, property)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TProperty : Any?> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(plant, property)

//Arg0
@Deprecated("use AssertImpl.feature.returnValueOf0, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf0(plant, method)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(plant, method)

@Deprecated("use AssertImpl.feature.returnValueOf0, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf0(plant, name, method)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(plant, name, method)

@Deprecated("use AssertImpl.feature.returnValueOf0, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf0(plant, method, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(plant, method, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf0, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf0(plant, name, method, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(plant, name, method, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf0, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf0(plant, method)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf0(plant, method)

@Deprecated("use AssertImpl.feature.returnValueOf0, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf0(plant, name, method)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf0(plant, name, method)


//Arg1
@Deprecated("use AssertImpl.feature.returnValueOf1, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf1(plant, method, arg1)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(plant, method, arg1)

@Deprecated("use AssertImpl.feature.returnValueOf1, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf1(plant, name, method, arg1)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(plant, name, method, arg1)

@Deprecated("use AssertImpl.feature.returnValueOf1, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf1(plant, method, arg1, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(plant, method, arg1, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf1, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf1(plant, name, method, arg1, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(plant, name, method, arg1, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf1, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf1(plant, method, arg1)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf1(plant, method, arg1)

@Deprecated("use AssertImpl.feature.returnValueOf1, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf1(plant, name, method, arg1)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf1(plant, name, method, arg1)


//Arg2
@Deprecated("use AssertImpl.feature.returnValueOf2, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf2(plant, method, arg1, arg2)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(plant, method, arg1, arg2)

@Deprecated("use AssertImpl.feature.returnValueOf2, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf2(plant, name, method, arg1, arg2)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(plant, name, method, arg1, arg2)

@Deprecated("use AssertImpl.feature.returnValueOf2, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf2(plant, method, arg1, arg2, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(plant, method, arg1, arg2, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf2, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf2(plant, name, method, arg1, arg2, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(plant, name, method, arg1, arg2, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf2, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf2(plant, method, arg1, arg2)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf2(plant, method, arg1, arg2)

@Deprecated("use AssertImpl.feature.returnValueOf2, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf2(plant, name, method, arg1, arg2)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf2(plant, name, method, arg1, arg2)


//Arg3
@Deprecated("use AssertImpl.feature.returnValueOf3, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf3(plant, method, arg1, arg2, arg3)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(plant, method, arg1, arg2, arg3)

@Deprecated("use AssertImpl.feature.returnValueOf3, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf3(plant, name, method, arg1, arg2, arg3)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(plant, name, method, arg1, arg2, arg3)

@Deprecated("use AssertImpl.feature.returnValueOf3, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf3(plant, method, arg1, arg2, arg3, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(plant, method, arg1, arg2, arg3, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf3, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf3(plant, name, method, arg1, arg2, arg3, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(plant, name, method, arg1, arg2, arg3, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf3, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf3(plant, method, arg1, arg2, arg3)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf3(plant, method, arg1, arg2, arg3)

@Deprecated("use AssertImpl.feature.returnValueOf3, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf3(plant, name, method, arg1, arg2, arg3)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf3(plant, name, method, arg1, arg2, arg3)


//Arg4
@Deprecated("use AssertImpl.feature.returnValueOf4, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf4(plant, method, arg1, arg2, arg3, arg4)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(plant, method, arg1, arg2, arg3, arg4)

@Deprecated("use AssertImpl.feature.returnValueOf4, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)

@Deprecated("use AssertImpl.feature.returnValueOf4, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf4(plant, method, arg1, arg2, arg3, arg4, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(plant, method, arg1, arg2, arg3, arg4, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf4, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf4, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf4(plant, method, arg1, arg2, arg3, arg4)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf4(plant, method, arg1, arg2, arg3, arg4)

@Deprecated("use AssertImpl.feature.returnValueOf4, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)


//Arg5
@Deprecated("use AssertImpl.feature.returnValueOf5, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5)

@Deprecated("use AssertImpl.feature.returnValueOf5, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)

@Deprecated("use AssertImpl.feature.returnValueOf5, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf5, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

@Deprecated("use AssertImpl.feature.returnValueOf5, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5)

@Deprecated("use AssertImpl.feature.returnValueOf5, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.feature.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)
