[atrium](../../index.md) / [ch.tutteli.atrium.assertions](../index.md) / [FeatureAssertionGroup](.)

# FeatureAssertionGroup

`data class FeatureAssertionGroup : `[`IFeatureAssertionGroup`](../-i-feature-assertion-group/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-assertions/src/main/kotlin/ch/tutteli/atrium/assertions/FeatureAssertionGroup.kt#L11)

Represent a group of [IAssertion](../-i-assertion/index.md) identified by a [featureName](feature-name.md) and a belonging [subSubject](sub-subject.md).

### Constructors

| [&lt;init&gt;](-init-.md) | `FeatureAssertionGroup(featureName: String, subSubject: Any, assertions: List<`[`IAssertion`](../-i-assertion/index.md)`>)` |

### Properties

| [assertions](assertions.md) | `val assertions: List<`[`IAssertion`](../-i-assertion/index.md)`>`<br>The assertions of this group, which are defined for [subSubject](sub-subject.md). |
| [featureName](feature-name.md) | `val featureName: String`<br>The name of the feature. |
| [subSubject](sub-subject.md) | `val subSubject: Any`<br>The subject of this feature, for which the [assertions](assertions.md) are defined for. |

### Inherited Functions

| [holds](../-i-feature-assertion-group/holds.md) | `open fun holds(): <ERROR CLASS>`<br>Holds if all its [assertions](../-i-feature-assertion-group/assertions.md) hold. |

