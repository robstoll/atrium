[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [ThrowableFluent](index.md) / [create](.)

# create

`fun create(assertionVerb: String, act: () -> Unit, assertionChecker: `[`IAssertionChecker`](../../ch.tutteli.atrium.checking/-i-assertion-checker/index.md)`): `[`ThrowableFluent`](index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/creating/ThrowableFluent.kt#L67)

Creates a [ThrowableFluent](index.md) where executing [act](create.md#ch.tutteli.atrium.creating.ThrowableFluent.Companion$create(kotlin.String, kotlin.Function0((kotlin.Unit)), ch.tutteli.atrium.checking.IAssertionChecker)/act) will determine the
[subject](IAssertionPlantWithCommonFields.CommonFields.subject) of [commonFields](common-fields.md).

**Return**
The newly created [ThrowableFluent](index.md).

