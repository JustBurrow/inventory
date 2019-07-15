dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-dto"))
    implementation(project(":inventory-business"))

    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-thymeleaf")
    api("org.springframework.boot:spring-boot-starter-security")

    api("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    api("io.github.jpenren:thymeleaf-spring-data-dialect")
}
