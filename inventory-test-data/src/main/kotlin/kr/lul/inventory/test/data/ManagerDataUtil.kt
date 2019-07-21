package kr.lul.inventory.test.data

import kr.lul.inventory.data.jpa.entity.ManagerCredentialEntity
import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.data.jpa.repository.ManagerRepository
import kr.lul.inventory.design.domain.Manager.Companion.EMAIL_MAX_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.NAME_MAX_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.NAME_MIN_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.PASSWORD_MIN_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.isValidEmail
import kr.lul.inventory.design.domain.Manager.Companion.isValidName
import kr.lul.inventory.design.factory.ManagerFactory
import org.apache.commons.lang3.RandomStringUtils.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.Instant
import java.util.concurrent.ThreadLocalRandom.current

open class ManagerDataUtil {
    private val log = LoggerFactory.getLogger(ManagerDataUtil::class.java)!!

    @Autowired(required = false)
    private lateinit var managerFactory: ManagerFactory
    @Autowired
    private lateinit var managerRepository: ManagerRepository
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun name(): String {
        var name: String

        do {
            name = "manager ${random(current().nextInt(NAME_MIN_LENGTH, 1 + NAME_MAX_LENGTH))}"
            log.trace("name='{}'", name)
        } while (!isValidName(name))

        if (log.isDebugEnabled) log.debug("return : '{}'", name)
        return name
    }

    fun unusedName(): String {
        var name: String
        do {
            name = name()
        } while (managerRepository.existsByName(name))

        return name
    }

    fun email(): String {
        var email: String

        do {
            email = "${randomAlphabetic(1)}${randomAlphanumeric(0, EMAIL_MAX_LENGTH / 2 - 1)}@test".toLowerCase()
            log.trace("email='{}'", email)
        } while (!isValidEmail(email))

        if (log.isDebugEnabled) log.debug("return : '{}'", email)
        return email
    }

    fun unusedEmail(): String {
        var email: String
        do {
            email = email()
        } while (managerRepository.existsByEmail(email))

        return email
    }

    fun password() = random(current().nextInt(PASSWORD_MIN_LENGTH, 4 * PASSWORD_MIN_LENGTH))!!

    /**
     * `unique` 등의 조건을 고려하지 않고, 영속화 하지 않은 [ManagerEntity]를 반환한다.
     */
    fun manager() = managerFactory.instance(email(), name(), Instant.now())

    /**
     * `unique` 등의 조건을 고려하여 , 영속화 할 수 있는 [ManagerEntity]를 반환한다..
     */
    fun unusedManager() = ManagerEntity(unusedEmail(), unusedName(), Instant.now())

    fun credential(nameAsPublicKey: Boolean = true, password: String = password()): ManagerCredentialEntity {
        val manager = manager()

        val credential = if (nameAsPublicKey)
            ManagerCredentialEntity(manager, manager.getName(), passwordEncoder.encode(password), Instant.now())
        else
            ManagerCredentialEntity(manager, manager.getEmail(), passwordEncoder.encode(password), Instant.now())

        if (log.isTraceEnabled)
            log.trace("return : $credential")
        return credential
    }
}
