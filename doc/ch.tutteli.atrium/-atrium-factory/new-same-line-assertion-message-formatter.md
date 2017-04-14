[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [AtriumFactory](index.md) / [newSameLineAssertionMessageFormatter](.)

# newSameLineAssertionMessageFormatter

`fun newSameLineAssertionMessageFormatter(objectFormatter: `[`IObjectFormatter`](../../ch.tutteli.atrium.reporting/-i-object-formatter/index.md)`): `[`IAssertionMessageFormatter`](../../ch.tutteli.atrium.reporting/-i-assertion-message-formatter/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L54)

Overrides [IAtriumFactory.newSameLineAssertionMessageFormatter](../-i-atrium-factory/new-same-line-assertion-message-formatter.md)

Creates an [IAssertionMessageFormatter](../../ch.tutteli.atrium.reporting/-i-assertion-message-formatter/index.md) which puts messages of the form 'a: b' on the same line.

### Parameters

`objectFormatter` - The formatter which is used to format objects other than [IAssertion](#) and [Message](#).

**Return**
The newly created assertion formatter.

