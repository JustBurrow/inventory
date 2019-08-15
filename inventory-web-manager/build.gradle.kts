import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-dto"))
    implementation(project(":inventory-business"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("io.github.jpenren:thymeleaf-spring-data-dialect")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework:spring-tx")

    implementation("org.thymeleaf.extras:thymeleaf-extras-java8time")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    implementation("com.github.bufferings:thymeleaf-extras-nl2br")
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = true
    launchScript()
}
