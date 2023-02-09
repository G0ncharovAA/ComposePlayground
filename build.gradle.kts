ext {
    extra["compose_version"] = "1.3.1"
    extra["hilt_version"] = "2.44"
    extra["nav_version"] = "2.5.3"
}

plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}