[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [IFeatureAssertionGroup](.)

# IFeatureAssertionGroup

`interface IFeatureAssertionGroup : `[`IAssertion`](../-i-assertion/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/assertions/IFeatureAssertionGroup.kt#L7)

The base interface for feature [IAssertion](../-i-assertion/index.md) groups, providing a default implementation for [IAssertion.holds](../-i-assertion/holds.md)
which returns `true` if all its [assertions](assertions.md) hold.

### Properties

| [assertions](assertions.md) | `abstract val assertions: List<`[`IAssertion`](../-i-assertion/index.md)`>`<br>The assertions of this group, which are defined for [subSubject](sub-subject.md). |
| [featureName](feature-name.md) | `abstract val featureName: String`<br>The name of the feature. |
| [subSubject](sub-subject.md) | `abstract val subSubject: Any`<br>The subject of this feature, for which the [assertions](assertions.md) are defined for. |

### Functions

| [holds](holds.md) | `open fun holds(): <ERROR CLASS>`<br>Holds if all its [assertions](assertions.md) hold. |

### Inheritors

| [FeatureAssertionGroup](../-feature-assertion-group/index.md) | `data class FeatureAssertionGroup : IFeatureAssertionGroup`<br>Represent a group of [IAssertion](../-i-assertion/index.md) identified by a [featureName](../-feature-assertion-group/feature-name.md) and a belonging [subSubject](../-feature-assertion-group/sub-subject.md). |

