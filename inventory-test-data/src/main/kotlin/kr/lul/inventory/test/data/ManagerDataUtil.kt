package kr.lul.inventory.test.data

import kr.lul.inventory.data.jpa.entity.ManagerEntity
import kr.lul.inventory.design.domain.Manager.Companion.EMAIL_MAX_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.NAME_MAX_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.NAME_MIN_LENGTH
import kr.lul.inventory.design.domain.Manager.Companion.isValidEmail
import kr.lul.inventory.design.domain.Manager.Companion.isValidName
import org.apache.commons.lang3.RandomStringUtils.*
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.concurrent.ThreadLocalRandom.current

class ManagerDataUtil {
    private val log = LoggerFactory.getLogger(ManagerDataUtil::class.java)!!

    fun name(): String {
        var name: String

        do {
            name = "manager ${random(current().nextInt(NAME_MIN_LENGTH, 1 + NAME_MAX_LENGTH))}"
            log.trace("name='{}'", name)
        } while (!isValidName(name))

        if (log.isDebugEnabled) log.debug("return : '{}'", name)
        return name
    }

    fun email(): String {
        var email: String

        do {
            email = "${randomAlphabetic(1)}${randomAlphanumeric(0, EMAIL_MAX_LENGTH / 2 - 1)}@test"
            log.trace("email='{}'", email)
        } while (!isValidEmail(email))

        if (log.isDebugEnabled) log.debug("return : '{}'", email)
        return email
    }

    fun random() = ManagerEntity(email(), name(), Instant.now())
}
