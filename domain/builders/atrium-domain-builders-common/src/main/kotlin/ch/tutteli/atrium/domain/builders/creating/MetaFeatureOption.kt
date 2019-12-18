package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.reporting.MethodCallFormatter
import ch.tutteli.atrium.reporting.Reporter
import kotlin.reflect.*


/**
 * Helper class to circumvent overload bugs and KFunction bugs incorporated in Kotlin -- use [f] and in case you run
 * into an overload ambiguity, then either [p] (for property) or one of the `fN` functions (e.g. [f2] for
 * a function which expects 2 arguments).
 */
//TODO move to API with 1.0.0?
class MetaFeatureOption<T>(private val expect: Expect<T>) {

    /**
     * Creates a [MetaFeature] for the given [property] => use [p] in case of ambiguity issues.
     *
     * Notice for assertion function writers: you should use `_domain.property` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.property(List<Int>::size)`).
     * This way we are always able to report the property, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f(property: KProperty0<R>): MetaFeature<R> = p(property)

    //@formatter:off
    /**
     * Creates a [MetaFeature] for the given function [f] without arguments => use [f0] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use `_domain.f0` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f0(MyClass::fun)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f(f: KFunction0<R>): MetaFeature<R> =
        f0(f)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 1 argument => use [f1] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use `_domain.f1` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f1(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, R> f(f: KFunction1<A1, R>, a1: A1): MetaFeature<R> =
        f1(f, a1)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 2 arguments => use [f2] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use `_domain.f2` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f2(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, R> f(f: KFunction2<A1, A2, R>, a1: A1, a2: A2): MetaFeature<R> =
        f2(f, a1, a2)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 3 arguments => use [f3] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use `_domain.f3` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f3(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, R> f(f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): MetaFeature<R> =
        f3(f, a1, a2, a3)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 4 arguments => use [f4] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use `_domain.f4` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f4(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, R> f(f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): MetaFeature<R> =
        f4(f, a1, a2, a3, a4)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 5 arguments => use [f5] in case of
     * ambiguity issues.
     *
     * Notice for assertion function writers: you should use `_domain.f5` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f5(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, A5, R> f(f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): MetaFeature<R> =
        f5(f, a1, a2, a3, a4, a5)

    //used to distinguish property/functions

    /**
     * Creates a [MetaFeature] for the given property [property].
     *
     * Notice for assertion function writers: you should use `_domain.p` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.featpure(List<Int>::size)`).
     * This way we are always able to report the property, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> p(property: KProperty0<R>): MetaFeature<R> =
        MetaFeatureBuilder.property(property)

    /**
     * Creates a [MetaFeature] for the given function [f] without arguments.
     *
     * Notice for assertion function writers: you should use `_domain.f0` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f0(MyClass::fun)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <R> f0(f: KFunction0<R>): MetaFeature<R> =
        MetaFeatureBuilder.f0(expect, f)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 1 argument.
     *
     * Notice for assertion function writers: you should use `_domain.f1` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f1(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, R> f1(f: KFunction1<A1, R>, a1: A1): MetaFeature<R> =
        MetaFeatureBuilder.f1(expect, f, a1)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 2 arguments.
     *
     * Notice for assertion function writers: you should use `_domain.f2` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f2(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, R> f2(f: KFunction2<A1, A2, R>, a1: A1, a2: A2): MetaFeature<R> =
        MetaFeatureBuilder.f2(expect, f, a1, a2)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 3 arguments.
     *
     * Notice for assertion function writers: you should use `_domain.f3` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f3(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, R> f3(f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): MetaFeature<R> =
        MetaFeatureBuilder.f3(expect, f, a1, a2, a3)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 4 arguments.
     *
     * Notice for assertion function writers: you should use `_domain.f4` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f4(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, R> f4(f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): MetaFeature<R> =
        MetaFeatureBuilder.f4(expect, f, a1, a2, a3, a4)

    /**
     * Creates a [MetaFeature] for the given function [f] which expects 5 arguments.
     *
     * Notice for assertion function writers: you should use `_domain.f5` and pass a
     * class reference instead of using this convenience function (e.g. `_domain.f5(MyClass::fun, ...)`).
     * This way we are always able to report the function name, even if the subject is not defined which occurs if a
     * previous transformation of the subject could not be carried out.
     */
    fun <A1, A2, A3, A4, A5, R> f5(f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): MetaFeature<R> =
        MetaFeatureBuilder.f5(expect, f, a1, a2, a3, a4, a5)

    //@formatter:on
}

/**
 * Builder which helps to create [MetaFeature] inter alia by using the [MethodCallFormatter] defined initially by the
 * [Reporter]
 */
@Suppress("UNUSED_PARAMETER" /* we will need it as soon as methodCallFormatter is taken from the specified Reporter */)
object MetaFeatureBuilder {

    fun <TProperty> property(property: KProperty0<TProperty>) = MetaFeature(property.name, property.invoke())

    //@formatter:off
    fun <R> f0(assertionContainer: Expect<*>, f: KFunction0<R>) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf()), f.invoke())

    fun <A1, R> f1(assertionContainer: Expect<*>, f: KFunction1<A1, R>, a1: A1) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf<Any?>(a1)), f.invoke(a1))

    fun <A1, A2, R> f2(assertionContainer: Expect<*>, f: KFunction2<A1, A2, R>, a1: A1, a2: A2) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2)), f.invoke(a1, a2))

    fun <A1, A2, A3, R> f3(assertionContainer: Expect<*>, f: KFunction3<A1, A2, A3, R>, a1: A1, a2: A2, a3: A3) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3)), f.invoke(a1, a2, a3))

    fun <A1, A2, A3, A4, R> f4(assertionContainer: Expect<*>, f: KFunction4<A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4)), f.invoke(a1, a2, a3, a4))

    fun <A1, A2, A3, A4, A5, R> f5(assertionContainer: Expect<*>, f: KFunction5<A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5) =
        MetaFeature(coreFactory.newMethodCallFormatter().formatCall(f.name, arrayOf(a1, a2, a3, a4, a5)), f.invoke(a1, a2, a3, a4, a5))
    //@formatter:on
}
