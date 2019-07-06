@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.FeatureAssertions
import ch.tutteli.atrium.domain.creating.feature.extract.FeatureExtractor
import ch.tutteli.atrium.domain.creating.featureAssertions
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.*
import kotlin.jvm.JvmName

/**
 * Delegates inter alia to the implementation of [FeatureAssertions].
 * In detail, it implements [FeatureAssertions] by delegating to [featureAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object FeatureAssertionsBuilder : FeatureAssertions {

    inline val extractor: FeatureExtractor.RepresentationOption get() = FeatureExtractor.builder

    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, property: KProperty1<T, TProperty>)
        = property(plant, { @Suppress("DEPRECATION") property.invoke(plant.subject) }, Untranslatable(property.name))

    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>)
        = property(plant, property, Untranslatable(property.name))

    override inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, noinline subjectProvider: () -> TProperty, name: Translatable): AssertionPlant<TProperty>
        = featureAssertions.property(plant, subjectProvider, name)

    override inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, noinline subjectProvider: () -> TProperty, noinline representationProvider: () -> Any?, name: Translatable): AssertionPlant<TProperty>
        = featureAssertions.property(plant, subjectProvider, representationProvider, name)


    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, property: KProperty1<T, TProperty>, noinline assertionCreator: AssertionPlant<TProperty>.() -> Unit)
        = property(plant, { @Suppress("DEPRECATION") property.invoke(plant.subject) },  Untranslatable(property.name), assertionCreator)

    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>, noinline assertionCreator: AssertionPlant<TProperty>.() -> Unit)
        = property(plant, property,  Untranslatable(property.name), assertionCreator)

    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, noinline subjectProvider: () -> TProperty, name: Translatable, noinline assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
        = property(plant, subjectProvider, name).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, noinline subjectProvider: () -> TProperty, noinline representationProvider: () -> Any?, name: Translatable, noinline assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
        = property(plant, subjectProvider, representationProvider, name).addAssertionsCreatedBy(assertionCreator)


    inline fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, property: KProperty1<T, TProperty>): AssertionPlantNullable<TProperty> {
        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val l = { @Suppress("DEPRECATION")  property.invoke(plant.subject) }
        return property(plant, l, Untranslatable(property.name))
    }

    inline fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>)
        = property(plant, property, Untranslatable(property.name))

    override inline fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, noinline subjectProvider: () -> TProperty, name: Translatable): AssertionPlantNullable<TProperty>
        = featureAssertions.property(plant, subjectProvider, name)

    override inline fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, noinline subjectProvider: () -> TProperty, noinline representationProvider: () -> Any?, name: Translatable): AssertionPlantNullable<TProperty>
        = featureAssertions.property(plant, subjectProvider, representationProvider, name)


    //Arg0
    @JvmName("safeReturnValueOf0")
    inline fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, method: KFunction1<T, R>): AssertionPlant<R>
        = returnValueOf0(plant, { @Suppress("DEPRECATION") method(plant.subject) }, method.name)

    inline fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<R>): AssertionPlant<R>
        = returnValueOf0(plant, method, method.name)

    override inline fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, noinline method: () -> R, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf0(plant, method, name)

    override inline fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, noinline method: () -> R, noinline representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf0(plant, method, representationProvider, name)


    @JvmName("safeReturnValueOf0")
    inline fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, method: KFunction1<T, R>, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf0(plant, { @Suppress("DEPRECATION") method(plant.subject) }, method.name, assertionCreator)

    inline fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<R>, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf0(plant, method, method.name, assertionCreator)

    inline fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, noinline method: () -> R, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf0(plant, method, name).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, noinline method: () -> R, noinline representationProvider: () -> Any?, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf0(plant, method, representationProvider, name).addAssertionsCreatedBy(assertionCreator)


    @JvmName("safeReturnValueOf0")
    inline fun <T : Any, R : Any?> returnValueOf0(plant: AssertionPlant<T>, method: KFunction1<T, R>): AssertionPlantNullable<R> {
        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val l = { @Suppress("DEPRECATION") method(plant.subject) }
        return returnValueOf0(plant, l, l, method.name)
    }

    inline fun <T : Any, R : Any?> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<R>): AssertionPlantNullable<R>
        = returnValueOf0(plant, method, method.name)

    override inline fun <T : Any, R : Any?> returnValueOf0(plant: AssertionPlant<T>, noinline method: () -> R, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf0(plant, method, name)

    override inline fun <T : Any, R : Any?> returnValueOf0(plant: AssertionPlant<T>, noinline method: () -> R, noinline representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf0(plant, method, representationProvider, name)


    //Arg1
    @JvmName("safeReturnValueOf1")
    inline fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, method: KFunction2<T, T1, R>, arg1: T1): AssertionPlant<R>
        = returnValueOf1(plant, {a1 -> @Suppress("DEPRECATION") method(plant.subject, a1) }, arg1, method.name)

    inline fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, R>, arg1: T1): AssertionPlant<R>
        = returnValueOf1(plant, method, arg1, method.name)

    override inline fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, noinline method: (T1) -> R, arg1: T1, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf1(plant, method, arg1, name)

    override inline fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, noinline method: (T1) -> R, arg1: T1, noinline representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf1(plant, method, arg1, representationProvider, name)


    @JvmName("safeReturnValueOf1")
    inline fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, method: KFunction2<T, T1, R>, arg1: T1, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf1(plant, {a1 -> @Suppress("DEPRECATION") method(plant.subject, a1) }, arg1, method.name, assertionCreator)

    inline fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, R>, arg1: T1, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf1(plant, method, arg1, method.name, assertionCreator)

    inline fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, noinline method: (T1) -> R, arg1: T1, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf1(plant, method, arg1, name).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, noinline method: (T1) -> R, arg1: T1, noinline representationProvider: () -> Any?, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf1(plant, method, arg1, representationProvider, name).addAssertionsCreatedBy(assertionCreator)


    @JvmName("safeReturnValueOf1")
    inline fun <T : Any, T1, R : Any?> returnValueOf1(plant: AssertionPlant<T>, method: KFunction2<T, T1, R>, arg1: T1): AssertionPlantNullable<R> {
        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val l: (T1) -> R = {a1 -> @Suppress("DEPRECATION") method(plant.subject, a1) }
        return returnValueOf1(plant, l, arg1, method.name)
    }

    inline fun <T : Any, T1, R : Any?> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, R>, arg1: T1): AssertionPlantNullable<R>
        = returnValueOf1(plant, method, arg1, method.name)

    override inline fun <T : Any, T1, R : Any?> returnValueOf1(plant: AssertionPlant<T>, noinline method: (T1) -> R, arg1: T1, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf1(plant, method, arg1, name)

    override inline fun <T : Any, T1, R : Any?> returnValueOf1(plant: AssertionPlant<T>, noinline method: (T1) -> R, arg1: T1, noinline representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf1(plant, method, arg1, representationProvider, name)


    //Arg2
    @JvmName("safeReturnValueOf2")
    inline fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2): AssertionPlant<R>
        = returnValueOf2(plant, {a1, a2 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2) }, arg1, arg2, method.name)

    inline fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlant<R>
        = returnValueOf2(plant, method, arg1, arg2, method.name)

    override inline fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, noinline method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf2(plant, method, arg1, arg2, name)

    override inline fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, noinline method: (T1, T2) -> R, arg1: T1, arg2: T2, noinline representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf2(plant, method, arg1, arg2, representationProvider, name)


    @JvmName("safeReturnValueOf2")
    inline fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf2(plant, {a1, a2 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2) }, arg1, arg2, method.name, assertionCreator)

    inline fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf2(plant, method, arg1, arg2, method.name, assertionCreator)

    inline fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, noinline method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf2(plant, method, arg1, arg2, name).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, noinline method: (T1, T2) -> R, arg1: T1, arg2: T2, noinline representationProvider: () -> Any?, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf2(plant, method, arg1, arg2, representationProvider, name).addAssertionsCreatedBy(assertionCreator)


    @JvmName("safeReturnValueOf2")
    inline fun <T : Any, T1, T2, R : Any?> returnValueOf2(plant: AssertionPlant<T>, method: KFunction3<T, T1, T2, R>, arg1: T1, arg2: T2): AssertionPlantNullable<R> {
        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val l : (T1, T2) -> R = {a1, a2 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2) }
        return returnValueOf2(plant, l, arg1, arg2, method.name)
    }

    inline fun <T : Any, T1, T2, R : Any?> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, R>, arg1: T1, arg2: T2): AssertionPlantNullable<R>
        = returnValueOf2(plant, method, arg1, arg2, method.name)

    override inline fun <T : Any, T1, T2, R : Any?> returnValueOf2(plant: AssertionPlant<T>, noinline method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf2(plant, method, arg1, arg2, name)

    override inline fun <T : Any, T1, T2, R : Any?> returnValueOf2(plant: AssertionPlant<T>, noinline method: (T1, T2) -> R, arg1: T1, arg2: T2, noinline representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf2(plant, method, arg1, arg2, representationProvider, name)


    //Arg3
    @JvmName("safeReturnValueOf3")
    inline fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<R>
        = returnValueOf3(plant, {a1, a2, a3 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3) }, arg1, arg2, arg3, method.name)

    inline fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<R>
        = returnValueOf3(plant, method, arg1, arg2, arg3, method.name)

    override inline fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, noinline method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3, name)

    override inline fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, noinline method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, noinline representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3, representationProvider, name)


    @JvmName("safeReturnValueOf3")
    inline fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf3(plant, {a1, a2, a3 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3) }, arg1, arg2, arg3, method.name, assertionCreator)

    inline fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf3(plant, method, arg1, arg2, arg3, method.name, assertionCreator)

    inline fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, noinline method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf3(plant, method, arg1, arg2, arg3, name).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, noinline method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, noinline representationProvider: () -> Any?, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf3(plant, method, arg1, arg2, arg3, representationProvider, name).addAssertionsCreatedBy(assertionCreator)


    @JvmName("safeReturnValueOf3")
    inline fun <T : Any, T1, T2, T3, R : Any?> returnValueOf3(plant: AssertionPlant<T>, method: KFunction4<T, T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<R> {
        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val l : (T1, T2, T3) -> R = {a1, a2, a3 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3) }
        return returnValueOf3(plant, l, arg1, arg2, arg3, method.name)
    }

    inline fun <T : Any, T1, T2, T3, R : Any?> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, R>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<R>
        = returnValueOf3(plant, method, arg1, arg2, arg3, method.name)

    override inline fun <T : Any, T1, T2, T3, R : Any?> returnValueOf3(plant: AssertionPlant<T>, noinline method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3, name)

    override inline fun <T : Any, T1, T2, T3, R : Any?> returnValueOf3(plant: AssertionPlant<T>, noinline method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, noinline representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3, representationProvider, name)


    //Arg4
    @JvmName("safeReturnValueOf4")
    inline fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<R>
        = returnValueOf4(plant, {a1, a2, a3, a4 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3, a4) }, arg1, arg2, arg3, arg4, method.name)

    inline fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<R>
        = returnValueOf4(plant, method, arg1, arg2, arg3, arg4, method.name)

    override inline fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4, name)

    override inline fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, noinline representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4, representationProvider, name)


    @JvmName("safeReturnValueOf4")
    inline fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf4(plant, {a1, a2, a3, a4 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3, a4) }, arg1, arg2, arg3, arg4, method.name, assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf4(plant, method, arg1, arg2, arg3, arg4, method.name, assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf4(plant, method, arg1, arg2, arg3, arg4, name).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, noinline representationProvider: () -> Any?, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf4(plant, method, arg1, arg2, arg3, arg4, representationProvider, name).addAssertionsCreatedBy(assertionCreator)


    @JvmName("safeReturnValueOf4")
    inline fun <T : Any, T1, T2, T3, T4, R : Any?> returnValueOf4(plant: AssertionPlant<T>, method: KFunction5<T, T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<R> {
        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val l : (T1, T2, T3, T4) -> R = {a1, a2, a3, a4 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3, a4) }
        return returnValueOf4(plant, l, arg1, arg2, arg3, arg4, method.name)
    }

    inline fun <T : Any, T1, T2, T3, T4, R : Any?> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<R>
        = returnValueOf4(plant, method, arg1, arg2, arg3, arg4, method.name)

    override inline fun <T : Any, T1, T2, T3, T4, R : Any?> returnValueOf4(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4, name)

    override inline fun <T : Any, T1, T2, T3, T4, R : Any?> returnValueOf4(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, noinline representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4, representationProvider, name)


    //Arg5
    @JvmName("safeReturnValueOf5")
    inline fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<R>
        = returnValueOf5(plant, {a1, a2, a3, a4, a5 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3, a4, a5) }, arg1, arg2, arg3, arg4, arg5, method.name)

    inline fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<R>
        = returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, method.name)

    override inline fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, name)

    override inline fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, noinline representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = featureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name)


    @JvmName("safeReturnValueOf5")
    inline fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf5(plant, {a1, a2, a3, a4, a5 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3, a4, a5) }, arg1, arg2, arg3, arg4, arg5, method.name, assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, method.name, assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, name).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, noinline representationProvider: () -> Any?, name: String, noinline assertionCreator: AssertionPlant<R>.() -> Unit): AssertionPlant<R>
        = returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name).addAssertionsCreatedBy(assertionCreator)


    @JvmName("safeReturnValueOf5")
    inline fun <T : Any, T1, T2, T3, T4, T5, R : Any?> returnValueOf5(plant: AssertionPlant<T>, method: KFunction6<T, T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<R> {
        //TODO get rid of l if https://youtrack.jetbrains.com/issue/KT-23768 is fixed
        val l: (T1, T2, T3, T4, T5) -> R = {a1, a2, a3, a4, a5 -> @Suppress("DEPRECATION") method(plant.subject, a1, a2, a3, a4, a5) }
        return returnValueOf5(plant, l, arg1, arg2, arg3, arg4, arg5, method.name)
    }

    inline fun <T : Any, T1, T2, T3, T4, T5, R : Any?> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, R>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<R>
        = returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, method.name)

    override inline fun <T : Any, T1, T2, T3, T4, T5, R : Any?> returnValueOf5(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, name)

    override inline fun <T : Any, T1, T2, T3, T4, T5, R : Any?> returnValueOf5(plant: AssertionPlant<T>, noinline method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, noinline representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = featureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name)
}
