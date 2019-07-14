dependencies {
    implementation(project(":inventory-design"))
    implementation(project(":inventory-dto"))
    implementation(project(":inventory-business"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("io.github.jpenren:thymeleaf-spring-data-dialect")
}
