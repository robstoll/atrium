[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [Message](.)

# Message

`data class Message` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/assertions/Message.kt#L22)

Represents a message of an [IAssertion](../-i-assertion/index.md).

It consists of:

[description](description.md) : [representation](representation.md) =&gt; [holds](holds.md)

### Constructors

| [&lt;init&gt;](-init-.md) | `Message(description: String, representation: Any, holds: Boolean)`<br>Represents a message of an [IAssertion](../-i-assertion/index.md) which consists of: |

### Properties

| [description](description.md) | `val description: String`<br>Quasi the left hand side of the whole message, e.g., `it starts with`. |
| [holds](holds.md) | `val holds: Boolean`<br>`true` if the assertion holds, `false` otherwise. |
| [representation](representation.md) | `val representation: Any`<br>The representation of the expected value, e.g., `"hello world"`. |

