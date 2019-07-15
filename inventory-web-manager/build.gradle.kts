dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-dto"))
    implementation(project(":inventory-business"))

    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-thymeleaf")
    api("io.github.jpenren:thymeleaf-spring-data-dialect")
    api("org.springframework.boot:spring-boot-starter-security")
}
