[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [OneMessageAssertion](.)

# OneMessageAssertion

`class OneMessageAssertion : `[`IOneMessageAssertion`](../-i-one-message-assertion/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/assertions/OneMessageAssertion.kt#L11)

A default implementation for [IOneMessageAssertion](../-i-one-message-assertion/index.md).

### Constructors

| [&lt;init&gt;](-init-.md) | `OneMessageAssertion(description: String, representation: Any, holds: Boolean)`<br>`OneMessageAssertion(description: String, representation: Any, check: () -> Boolean)` |

### Properties

| [message](message.md) | `val message: <ERROR CLASS>`<br>The message of this assertion. |

### Functions

| [toString](to-string.md) | `fun toString(): <ERROR CLASS>`<br>Delegates to [message](message.md)'s [Message.toString](#). |

### Inherited Functions

| [holds](../-i-one-message-assertion/holds.md) | `open fun holds(): Boolean`<br>Holds if its [message](../-i-one-message-assertion/message.md) holds. |

