package kr.lul.inventory.test

import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.data.jpa.repository.ManagerRepository
import kr.lul.inventory.design.domain.Manager.Companion.isValidEmail
import kr.lul.inventory.design.domain.Manager.Companion.isValidName
import kr.lul.inventory.design.util.TimeProvider
import org.apache.commons.lang3.RandomStringUtils.random
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant
import java.util.concurrent.ThreadLocalRandom.current

/**
 * @author justburrow
 * @since 2019-07-23
 */
class ManagerUtil {
    private val log = LoggerFactory.getLogger(ManagerUtil::class.java)!!

    @Autowired
    private lateinit var repository: ManagerRepository
    @Autowired
    private lateinit var timeProvider: TimeProvider

    fun name(): String {
        var name: String

        do {
            name = "test.name.${random(current().nextInt(1, 10))}"
        } while (!isValidName(name))

        log.trace("return : '$name'")
        return name
    }

    fun unusedName(): String {
        var name: String

        do {
            name = name()
        } while (repository.existsByName(name))

        log.trace("return : '$name'")
        return name
    }

    fun email(): String {
        var email: String

        do {
            email = "${randomAlphanumeric(1, 20)}@test.email".toLowerCase()
        } while (!isValidEmail(email))

        log.trace("return : '$email'")
        return email
    }

    fun unusedEmail(): String {
        var email: String

        do {
            email = email()
        } while (repository.existsByEmail(email))

        log.trace("return : '$email'")
        return email
    }

    /**
     * 새로 저장할 수 있는 [ManagerEntity].
     */
    fun freshManager(createdAt: Instant = timeProvider.instant) =
            ManagerEntity(unusedEmail(), unusedName(), createdAt)
}
