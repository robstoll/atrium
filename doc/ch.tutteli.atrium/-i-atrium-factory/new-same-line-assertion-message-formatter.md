[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [IAtriumFactory](index.md) / [newSameLineAssertionMessageFormatter](.)

# newSameLineAssertionMessageFormatter

`abstract fun newSameLineAssertionMessageFormatter(objectFormatter: `[`IObjectFormatter`](../../ch.tutteli.atrium.reporting/-i-object-formatter/index.md)`): `[`IAssertionMessageFormatter`](../../ch.tutteli.atrium.reporting/-i-assertion-message-formatter/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L171)

Creates an [IAssertionMessageFormatter](../../ch.tutteli.atrium.reporting/-i-assertion-message-formatter/index.md) which puts messages of the form 'a: b' on the same line.

### Parameters

`objectFormatter` - The formatter which is used to format objects other than [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md) and [Message](#).

**Return**
The newly created assertion formatter.

