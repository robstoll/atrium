[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [IOneMessageAssertion](.)

# IOneMessageAssertion

`interface IOneMessageAssertion : `[`IAssertion`](../-i-assertion/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/assertions/IOneMessageAssertion.kt#L8)

The base interface for [IAssertion](../-i-assertion/index.md)s which have one [Message](../-message/index.md).

It provides a default implementation for [IAssertion.holds](../-i-assertion/holds.md) which delegates to [Message.holds](../-message/holds.md).

### Properties

| [message](message.md) | `abstract val message: `[`Message`](../-message/index.md)<br>The message of this assertion. |

### Functions

| [holds](holds.md) | `open fun holds(): Boolean`<br>Holds if its [message](message.md) holds. |

### Inheritors

| [OneMessageAssertion](../-one-message-assertion/index.md) | `class OneMessageAssertion : IOneMessageAssertion`<br>A default implementation for IOneMessageAssertion. |

