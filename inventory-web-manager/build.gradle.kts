import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-dto"))
    implementation(project(":inventory-business"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework:spring-tx")

    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    implementation("io.github.jpenren:thymeleaf-spring-data-dialect")
}

tasks.withType<BootJar> {
    launchScript()
}
