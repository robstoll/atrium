plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.buildParameters)
    api("com.github.vlsi.crlf:com.github.vlsi.crlf.gradle.plugin:1.89")
    api("com.github.vlsi.gradle-extensions:com.github.vlsi.gradle-extensions.gradle.plugin:1.89")
    api("com.dorongold.plugins:task-tree:2.1.1")
}
