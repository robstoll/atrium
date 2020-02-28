package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.feature.FeatureStep
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.MetaFeatureOption
import ch.tutteli.atrium.domain.creating.MetaFeature
import kotlin.reflect.*


/**
 * Extracts the [property] out of the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the given [property].
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(property: KProperty1<T, R>): FeatureExpect<T, R> =
    ExpectImpl.feature.property(this, property).getExpectOfFeature()

//infix fun <T, R> Expect<T>.feature(property: P<T, R>): FeatureExpect<T, R> =
//    ExpectImpl.feature.property(this, property.property).getExpectOfFeature()

/**
 * Extracts the property defined by [withAssertionCreator].[value][WithAssertionCreator.value]
 * out of the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [WithAssertionCreator.assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(withAssertionCreator: WithAssertionCreator<KProperty1<T, R>, R>): Expect<T> =
    ExpectImpl.feature.property(this, withAssertionCreator.value).addToInitial(withAssertionCreator.assertionCreator)

/**
 * Extracts the value which is returned when calling [f] on the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @return An [Expect] for the return value of calling [f] on the current subject of the assertion.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(f: KFunction1<T, R>): FeatureExpect<T, R> =
    ExpectImpl.feature.f0(this, f).getExpectOfFeature()


/**
 * Extracts the property defined by [withAssertionCreator].[value][WithAssertionCreator.value]
 * out of the current subject of the assertion,
 * creates a new [Expect] for it,
 * applies an assertion group based on the given [WithAssertionCreator.assertionCreator] for the feature and
 * returns the initial [Expect] with the current subject.
 *
 * @return The current [Expect].
 * @throws AssertionError Might throw an [AssertionError] in case the created [AssertionGroup] does not hold.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(withAssertionCreator: WithAssertionCreator<KFunction1<T, R>, R>): Expect<T> =
    ExpectImpl.feature.f0(this, withAssertionCreator.value).addToInitial(withAssertionCreator.assertionCreator)


/**
 * Represents a [description] of a feature together with an [extractor] which extract the feature as such of type [R]
 * out of the subject of the assertion with type [T].
 *
 * Use one of the [withArgs] overload to create this representation.
 *
 * @property description The description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of the assertion.
 *
 * @since 0.10.0
 */
data class DescriptionWithProvider<T, R>(val description: String, val extractor: (T) -> R)

/**
 * Extracts a feature using the given [DescriptionWithProvider.extractor] out of the current subject of the assertion,
 * creates a new [Expect] for it and
 * returns it so that subsequent calls are based on the feature.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(withArgs: DescriptionWithProvider<T, R>): FeatureExpect<T, R> =
//    FeatureStep(ExpectImpl.feature.manualFeature(this, withArgs.description, withArgs.extractor))
    ExpectImpl.feature.manualFeature(this, withArgs.description, withArgs.extractor).getExpectOfFeature()

///**
// * Extracts a feature using the given [DescriptionWithProvider.extractor] out of the current subject of the assertion,
// * creates a new [Expect] for it and
// * returns it so that subsequent calls are based on the feature.
// *
// * @since 0.10.0
// */
//infix fun <T, R> Expect<T>.feature(withArgs: WithAssertionCreator<DescriptionWithProvider<T, R>, R>): Expect<T> =
//    ExpectImpl.feature.manualFeature(
//        this,
//        withArgs.value.description,
//        withArgs.value.extractor
//    ).addToInitial(withArgs.assertionCreator)


//@formatter:off
fun <T, A1, R> withArgs(p: KFunction2<T, A1, R>, a1: A1): DescriptionWithProvider<T, R> =
    DescriptionWithProvider(p.name) { p.invoke(it, a1) }

fun <T, A1, R> withArgs(p: KFunction2<T, A1, R>, a1: A1, assertionCreator: Expect<R>.() -> Unit): DescriptionWithProvider<T, R> =
    DescriptionWithProvider(p.name) { p.invoke(it, a1) }

fun <T, A1, A2, R> withArgs(p: KFunction3<T, A1, A2, R>, a1: A1, a2: A2): DescriptionWithProvider<T, R> =
    DescriptionWithProvider(p.name) { p.invoke(it, a1, a2) }

fun <T, A1, A2, A3, R> withArgs(p: KFunction4<T, A1, A2, A3, R>, a1: A1, a2: A2, a3: A3): DescriptionWithProvider<T, R> =
    DescriptionWithProvider(p.name) { p.invoke(it, a1, a2, a3) }

fun <T, A1, A2, A3, A4, R> withArgs(p: KFunction5<T, A1, A2, A3, A4, R>, a1: A1, a2: A2, a3: A3, a4: A4): DescriptionWithProvider<T, R> =
    DescriptionWithProvider(p.name) { p.invoke(it, a1, a2, a3, a4) }

fun <T, A1, A2, A3, A4, A5, R> withArgs(p: KFunction6<T, A1, A2, A3, A4, A5, R>, a1: A1, a2: A2, a3: A3, a4: A4, a5: A5): DescriptionWithProvider<T, R> =
    DescriptionWithProvider(p.name) { p.invoke(it, a1, a2, a3, a4, a5) }
//@formatter:on


/**
 * Extracts a feature out of the current subject of the assertion,
 * based on the given [provider] and
 * creates a [FeatureStep] which allows to define what should be done with it.
 *
 * @param provider Creates a [MetaFeature] where the subject of the assertion is available via
 *   implicit parameter `it`. Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 *   e.g. `feature { f(it::size) }`
 *
 * @return An [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
infix fun <T, R> Expect<T>.feature(provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>): FeatureExpect<T, R> =
//    FeatureStep(ExpectImpl.feature.genericSubjectBasedFeature(this) { MetaFeatureOption(this).provider(it) })
    ExpectImpl.feature.genericSubjectBasedFeature(this) { MetaFeatureOption(this).provider(it) }.getExpectOfFeature()

/**
 * Extracts a feature out of the current subject of the assertion,
 * based on the given [provider] and
 * creates a [FeatureStep] which allows to define what should be done with it.
 *
 * @param provider Creates a [MetaFeature] where the subject of the assertion is available via
 *   implicit parameter `it`. Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 *   e.g. `feature { f(it::size) }`
 *
 * @return An [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
//infix fun <T, R> Expect<T>.feature(provider: WithAssertionCreator<MetaFeatureOption<T>.(T) -> MetaFeature<R>, R>): Expect<T> =
//    ExpectImpl.feature.genericSubjectBasedFeature(this) { MetaFeatureOption(this).(provider.value)(it) }
//        .addToInitial(provider.assertionCreator)


/**
 * Creates a [MetaFeature] using the given [provider] and [description].
 *
 * @return The newly created [MetaFeature]
 */
@Suppress("unused" /* unused receiver, but that's fine */)
fun <T, R> MetaFeatureOption<T>.f(description: String, provider: R): MetaFeature<R> =
    MetaFeature(description, provider)


fun <T> notImplemented(): Expect<T> = TODO()

data class Person(val name: String, val firstName: String, val age: Int) {
    fun foo(int: Int) = "a"
    fun bar() = "b"
}

data class P<T, R>(val property: KProperty1<T, R>, val maybeAssertionCreator: (Expect<R>.() -> Unit)?) {
    constructor(property: KProperty1<T, R>) : this(property, null)
}




@ExperimentalWithOptions
fun test() {

    val l: Expect<List<Int>> = notImplemented()

    val p: Expect<Pair<String, Int>> = notImplemented()

    l get 1 toBe 2
    l get 1 { o toBe 1 } get 2 { o toBe 2 }




    p first o toBe "hello"
    p second o toBe 1
    p and {
        first {
            o feature String::length toBe 1
        }
        first withRepresentation "name" toBe "robert"
        second toBe 1
    }

    val e: Expect<Person> = notImplemented()

    e feature Person::name withRepresentation "first name" toBe "robert"
    e feature Person::name toBe "robert"
    e feature Person::name { o toBe "robert" }

    e feature withArgs(Person::foo, 1) toBe "a"
    e feature withArgs(Person::foo, 1) { o toBe "a" }

    e feature Person::bar toBe "a"
    e feature Person::bar { o toBe "a" }

    e feature { f(it::age) } toBe 20
    e feature { f(it::foo, 1) } toBe "hello"
    e feature { f(it::bar) } toBe "hello"

    e feature { f(it::age) } it { o toBe 1 }
    e feature { f("bla", it.age) } toBe 1
    e feature { f("bla", it.age) } it { o toBe 1 }

//    e feature { manual("age") { it.age } } it o isGreaterThan 2
}

// uncomment and `l get 1` above fails
//infix operator fun Int.invoke(assertionCreator: Expect<Int>.() -> Unit): String = "a"

