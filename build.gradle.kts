ext {
    extra["compose_version"] = "1.1.0-beta01"
}

plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.31" apply false
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}