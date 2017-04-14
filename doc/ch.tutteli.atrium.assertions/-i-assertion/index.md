[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [IAssertion](.)

# IAssertion

`interface IAssertion` [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/assertions/IAssertion.kt#L6)

The base interface of all assertions, providing the method [holds](holds.md).

### Functions

| [holds](holds.md) | `abstract fun holds(): Boolean`<br>Indicates whether the assertion holds or not. |

### Inheritors

| [IAssertionGroup](../-i-assertion-group/index.md) | `interface IAssertionGroup : IAssertion`<br>The base interface for IAssertion groups, providing a default implementation for [IAssertion.holds](holds.md)
which returns `true` if all its [assertions](../-i-assertion-group/assertions.md) hold. |
| [IFeatureAssertionGroup](../-i-feature-assertion-group/index.md) | `interface IFeatureAssertionGroup : IAssertion`<br>The base interface for feature IAssertion groups, providing a default implementation for [IAssertion.holds](holds.md)
which returns `true` if all its [assertions](../-i-feature-assertion-group/assertions.md) hold. |
| [IMultiMessageAssertion](../-i-multi-message-assertion/index.md) | `interface IMultiMessageAssertion : IAssertion`<br>The base interface for IAssertions which have multiple [Message](../-message/index.md)s. |
| [IOneMessageAssertion](../-i-one-message-assertion/index.md) | `interface IOneMessageAssertion : IAssertion`<br>The base interface for IAssertions which have one [Message](../-message/index.md). |

