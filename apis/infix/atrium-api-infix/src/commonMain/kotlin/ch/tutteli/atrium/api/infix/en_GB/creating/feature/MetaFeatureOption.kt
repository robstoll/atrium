package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.feature.MetaFeature
import ch.tutteli.atrium.api.infix.en_GB.feature
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.reporting.MethodCallFormatter
import kotlin.reflect.*

/**
 * Represents the default options available for a user if he uses [feature] with the [MetaFeatureOption]-lambda overload.
 */
class MetaFeatureOption<T>(expect: Expect<T>) {

    @OptIn(ExperimentalComponentFactoryContainer::class)
    private val methodCallFormatter = expect._logic.components.build<MethodCallFormatter>()

    /**
     * Creates a [MetaFeature] for the given [property] => use [p] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(List<Int>::size)`).
     * This way we are always able to report the property, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f(property: KProperty0<R>): MetaFeature<R> = p(property)

    //@formatter:off
    /**
     * Creates a [MetaFeature] for the given function [f] without arguments => use [f0] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f(f: KFunction0<R>): MetaFeature<R> =
        f0(f)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 1 argument => use [f1] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, R> f(f: KFunction1<A1, R>, a1: A1): MetaFeature<R> =
        f1(f, a1)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 2 arguments => use [f2] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, R> f(f: KFunction2<A1, A2, R>, a1: A1, a2: A2): MetaFeature<R> =
        f2(f, a1, a2)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 3 arguments => use [f3] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, R> f(f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): MetaFeature<R> =
        f3(f, a1, a2, a3)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 4 arguments => use [f4] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, R> f(f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): MetaFeature<R> =
        f4(f, a1, a2, a3, a4)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 5 arguments => use [f5] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, A5, R> f(f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): MetaFeature<R> =
        f5(f, a1, a2, a3, a4, a5)

    //used to distinguish property/functions

    /**
     * Creates a [MetaFeature] for the given property [property].
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(List<Int>::size)`).
     * This way we are always able to report the property, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> p(property: KProperty0<R>): MetaFeature<R> =
        MetaFeature(property.name, property.invoke())

    /**
     * Creates a [MetaFeature] for the given function [f] without arguments.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f0(f: KFunction0<R>): MetaFeature<R> =
        MetaFeature(methodCallFormatter.formatCall(f.name, arrayOf()), f.invoke())

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 1 argument.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, R> f1(f: KFunction1<A1, R>, a1: A1): MetaFeature<R> =
        MetaFeature(methodCallFormatter.formatCall(f.name, arrayOf<Any?>(a1)), f.invoke(a1))

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 2 arguments.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, R> f2(f: KFunction2<A1, A2, R>, a1: A1, a2: A2): MetaFeature<R> =
        MetaFeature(methodCallFormatter.formatCall(f.name, arrayOf(a1, a2)), f.invoke(a1, a2))

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 3 arguments.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, R> f3(f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): MetaFeature<R> =
        MetaFeature(methodCallFormatter.formatCall(f.name, arrayOf(a1, a2, a3)), f.invoke(a1, a2, a3))

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 4 arguments.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, R> f4(f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): MetaFeature<R> =
        MetaFeature(methodCallFormatter.formatCall(f.name, arrayOf(a1, a2, a3, a4)), f.invoke(a1, a2, a3, a4))

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 5 arguments.
     *
     * Notice for assertion function writers: you should use [feature] and pass a
     * class reference instead of using this convenience function (e.g. `feature(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, A5, R> f5(f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): MetaFeature<R> =
        MetaFeature(methodCallFormatter.formatCall(f.name, arrayOf(a1, a2, a3, a4, a5)), f.invoke(a1, a2, a3, a4, a5))
    //@formatter:on
}
