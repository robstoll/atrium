[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [AssertionGroup](.)

# AssertionGroup

`data class AssertionGroup : `[`IAssertionGroup`](../-i-assertion-group/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/assertions/AssertionGroup.kt#L11)

Represent a group of [IAssertion](../-i-assertion/index.md)s identified by a [name](name.md) and an associated [subject](subject.md).

### Constructors

| [&lt;init&gt;](-init-.md) | `AssertionGroup(name: String, subject: Any, assertions: List<`[`IAssertion`](../-i-assertion/index.md)`>)` |

### Properties

| [assertions](assertions.md) | `val assertions: List<`[`IAssertion`](../-i-assertion/index.md)`>`<br>The assertions of this group, which are defined for [subject](subject.md). |
| [name](name.md) | `val name: String`<br>The name of the group. |
| [subject](subject.md) | `val subject: Any`<br>The subject for which the [assertions](assertions.md) are defined. |

### Inherited Functions

| [holds](../-i-assertion-group/holds.md) | `open fun holds(): <ERROR CLASS>`<br>Holds if all its [assertions](../-i-assertion-group/assertions.md) hold. |

