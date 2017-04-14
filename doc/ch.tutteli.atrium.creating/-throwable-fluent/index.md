[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [ThrowableFluent](.)

# ThrowableFluent

`class ThrowableFluent` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/creating/ThrowableFluent.kt#L19)

Provides [toThrow](to-throw.md) methods for making assertions about a [Throwable](#)
which one expects was thrown.

### Constructors

| [&lt;init&gt;](-init-.md) | `ThrowableFluent(assertionVerb: String, throwable: Throwable?, assertionChecker: `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)`)` |

### Properties

| [commonFields](common-fields.md) | `val commonFields: `[`CommonFields`](../-i-assertion-plant-with-common-fields/-common-fields/index.md)`<Throwable?>`<br>The [commonFields](common-fields.md)'s [subject](../-i-assertion-plant-with-common-fields/-common-fields/subject.md)
    represents the thrown [Throwable](#) and will be used in [ThrowableFluent.toThrow](to-throw.md). Its method
    [IAssertionPlantWithCommonFields.CommonFields.fail](../-i-assertion-plant-with-common-fields/-common-fields/fail.md) is used for failure reporting etc. |

### Functions

| [toThrow](to-throw.md) | `fun <TExpected : Throwable> toThrow(): `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TExpected>`<br>Makes an assertion about the [commonFields](common-fields.md)'s [subject](../-i-assertion-plant-with-common-fields/-common-fields/subject.md)
that it is of the expected type [TExpected](#) and reports an error if subject is `null` or another type
than the expected one.`fun <TExpected : Throwable> toThrow(createAssertions: `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TExpected>.() -> Unit): `[`IAssertionPlant`](../-i-assertion-plant/index.md)`<TExpected>`<br>Makes an assertion about the [commonFields](common-fields.md)'s [subject](../-i-assertion-plant-with-common-fields/-common-fields/subject.md)
that it is of the expected type [TExpected](#) and reports an error if subject is null or another type
than the expected one -- furthermore it [createAssertions](to-throw.md#ch.tutteli.atrium.creating.ThrowableFluent$toThrow(kotlin.Function1((ch.tutteli.atrium.creating.IAssertionPlant((ch.tutteli.atrium.creating.ThrowableFluent.toThrow.TExpected)), kotlin.Unit)))/createAssertions) which are checked additionally as well. |

### Companion Object Properties

| [NO_EXCEPTION_OCCURRED](-n-o_-e-x-c-e-p-t-i-o-n_-o-c-c-u-r-r-e-d.md) | `val NO_EXCEPTION_OCCURRED: String` |

### Companion Object Functions

| [create](create.md) | `fun create(assertionVerb: String, act: () -> Unit, assertionChecker: `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)`): ThrowableFluent`<br>Creates a ThrowableFluent where executing [act](create.md#ch.tutteli.atrium.creating.ThrowableFluent.Companion$create(kotlin.String, kotlin.Function0((kotlin.Unit)), ch.tutteli.atrium.checking.IAssertionChecker)/act) will determine the
[subject](IAssertionPlantWithCommonFields.CommonFields.subject) of [commonFields](common-fields.md). |

