---
name: ⭐ Good First Issue (for maintainers)
about: Create a good first issue
labels: good first issue, help wanted
---
*Platform* (all, jvm, js): all
*Extension* (none, kotlin 1.3): none

## Code related feature
```
expect(

//instead of

expect(
```

Following the things you need to do:


*logic*
- [ ] extend XyAssertions with a function `foo` (see YzAssertions as a guideline)
- [ ] implement foo in DefaultXyAssertions.kt by using the `ExpectImpl.feature.extractor...`

*api-fluent*
- [ ] provide a val which returns `Expect<T>` in xYExpectations.kt (see yzExpectations.kt as a guideline)
- [ ] provide a fun which expects an `assertionCreator`-lambda and returns `Expect<AB>` in xyExpectations.kt (see yzExpectations.kt as a guideline)
- [ ] add `@since 0.18.0` (adapt to current [milestone](https://github.com/robstoll/atrium/milestones)) to KDOC
- [ ] extend or write a separate Spec named XyExpectationsSpec in specs-common (see for instance YzExpectationsSpec) and extend it in atrium-api-fluent-en_GB-common/src/test

*api-infix*
- [ ] provide a val which returns `Expect<T>` in xYExpectations.kt(see yzExpectations.kt as a guideline)
- [ ] provide a fun which expects an `assertionCreator`-lambda and returns `Expect<AB>` in xyExpectations.kt (see yzExpectations.kt as a guideline)
- [ ] add `@since 0.18.0` (adapt to current [milestone](https://github.com/robstoll/atrium/milestones)) to KDOC
- [ ] extend or write a separate Spec named XyExpectationsSpec in specs-common (see for instance YzExpectationsSpec) and extend it in atrium-api-infix-en_GB-common/src/test


## Non-Code related feature
**Is your feature request related to a problem? Please describe.**
A clear and concise description of what the problem is. Ex. I'm always frustrated when [...]

**Describe the solution you'd like**
A clear and concise description of what you want to happen.

**Describe alternatives you've considered**
A clear and concise description of any alternative solutions or features you've considered.

## Your first contribution?
- Write a comment `I'll work on this` if you would like to take this issue over. 
  This way we get the chance to revise the description in case things have changed in the meantime, we might give you additional hints and we can assign the task to you, so that others do not start as well.
- See [Your first code contribution](https://github.com/robstoll/atrium/blob/main/.github/CONTRIBUTING.md#your-first-code-contribution) for guidelines.  
- Do not hesitate to ask questions here or to contact us via [Atrium's slack channel](https://kotlinlang.slack.com/team/U3DE1TXKP) if you need help
  ([Invite yourself](https://slack.kotlinlang.org/) in case you do not have an account yet).
