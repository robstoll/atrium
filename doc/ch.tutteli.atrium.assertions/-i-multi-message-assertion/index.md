[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [IMultiMessageAssertion](.)

# IMultiMessageAssertion

`interface IMultiMessageAssertion : `[`IAssertion`](../-i-assertion/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/assertions/IMultiMessageAssertion.kt#L8)

The base interface for [IAssertion](../-i-assertion/index.md)s which have multiple [Message](../-message/index.md)s.

It provides a default implementation for [IAssertion.holds](../-i-assertion/holds.md) which returns true if all its [messages](messages.md) hold.

### Properties

| [messages](messages.md) | `abstract val messages: List<`[`Message`](../-message/index.md)`>`<br>The messages of this assertion. |

### Functions

| [holds](holds.md) | `open fun holds(): <ERROR CLASS>`<br>Holds if all its [messages](messages.md) hold. |

