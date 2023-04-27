plugins {
    id("java-platform")
    id("build-logic.reproducible-builds")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("mavenJavaPlatform") {
            from(components["javaPlatform"])
        }
    }
}
