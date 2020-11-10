# Sample JS project: Atrium with mocha

This example project is heavily based on [kotlin's example](https://github.com/JetBrains/kotlin-examples/blob/master/gradle/js-tests/mocha).
Yet with a few optimizations and adaptations:
- it stores nodejs and npm only once per project (and not for every sub project)
- it uses `--prefer-offline` for a faster build and offline capability
- it uses sourceMap so that debugging in kotlin code is possible (as good as your IDE supports it)
- it includes all test dependencies, also runtime only dependencies
- it does not force a `moduleKind` on src/main
- last but not least and this is the important part, **it establishes a dependency to Atrium on JS level** 
  which is necessary due to the loosely coupled design of Atrium and dead code elimination.