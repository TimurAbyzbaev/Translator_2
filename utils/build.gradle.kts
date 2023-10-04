plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.utils"
}

dependencies {
    implementation(Kotlin.core)
    implementation(Design.appcompat)

    //Test
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.junit_ext)
    androidTestImplementation(TestImpl.espresso)
}