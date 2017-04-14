[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [IAssertionGroup](.)

# IAssertionGroup

`interface IAssertionGroup : `[`IAssertion`](../-i-assertion/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/assertions/IAssertionGroup.kt#L7)

The base interface for [IAssertion](../-i-assertion/index.md) groups, providing a default implementation for [IAssertion.holds](../-i-assertion/holds.md)
which returns `true` if all its [assertions](assertions.md) hold.

### Properties

| [assertions](assertions.md) | `abstract val assertions: List<`[`IAssertion`](../-i-assertion/index.md)`>`<br>The assertions of this group, which are defined for [subject](subject.md). |
| [name](name.md) | `abstract val name: String`<br>The name of the group. |
| [subject](subject.md) | `abstract val subject: Any`<br>The subject for which the [assertions](assertions.md) are defined. |

### Functions

| [holds](holds.md) | `open fun holds(): <ERROR CLASS>`<br>Holds if all its [assertions](assertions.md) hold. |

### Inheritors

| [AssertionGroup](../-assertion-group/index.md) | `data class AssertionGroup : IAssertionGroup`<br>Represent a group of [IAssertion](../-i-assertion/index.md)s identified by a [name](../-assertion-group/name.md) and an associated [subject](../-assertion-group/subject.md). |

