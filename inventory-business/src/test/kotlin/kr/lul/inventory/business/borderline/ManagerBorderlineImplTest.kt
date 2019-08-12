package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.business.borderline.cmd.CreateManagerCmd
import kr.lul.inventory.business.borderline.cmd.ReadManagerCmd
import kr.lul.inventory.design.domain.AttributeValidationException
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_EMAIL
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_PASSWORD
import kr.lul.inventory.design.domain.Manager.Companion.PASSWORD_MIN_LENGTH
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.test.ManagerUtil
import org.apache.commons.lang3.RandomStringUtils.random
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.lang.Thread.sleep
import java.time.Instant
import java.util.*
import java.util.UUID.randomUUID
import kr.lul.inventory.design.domain.Manager.Companion.ATTR_NAME as ATTR_NAME1

/**
 * @author justburrow
 * @since 2019-08-10
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
class ManagerBorderlineImplTest {
    private val log = LoggerFactory.getLogger(ManagerBorderlineImplTest::class.java)!!

    @Autowired
    private lateinit var borderline: ManagerBorderline

    @Autowired
    private lateinit var managerUtil: ManagerUtil
    @Autowired
    private lateinit var timeProvider: TimeProvider

    private lateinit var before: Instant

    @Before
    fun setUp() {
        before = timeProvider.instant
        sleep(1L)
    }

    @Test
    fun `test create(cmd)`() {
        // GIVEN
        val cmd = CreateManagerCmd(randomUUID(), managerUtil.email, managerUtil.name, managerUtil.password)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val manager = borderline.create(cmd)
        log.debug("WHEN - manager=$manager")

        // THEN
        assertThat(manager)
                .extracting(ATTR_EMAIL, ATTR_NAME1)
                .containsSequence(cmd.email, cmd.name)
        assertThat(manager.id)
                .isPositive()
        assertThat(manager.createdAt)
                .isEqualTo(manager.updatedAt)
                .isAfter(timeProvider.toZoneDateTime(before))
    }

    @Test
    fun `test create(cmd) with same email`() {
        // GIVEN
        val email = managerUtil.email
        val cmd = CreateManagerCmd(UUID.randomUUID(), email, managerUtil.name, managerUtil.password)
        log.debug("GIVEN - cmd=$cmd")
        val manager = borderline.create(cmd)
        log.debug("GIVEN - manager=$manager")

        // WHEN & THEN
        assertThatThrownBy {
            borderline.create(CreateManagerCmd(UUID.randomUUID(), email, managerUtil.name,
                    managerUtil.password))
        }
                .isInstanceOf(AttributeValidationException::class.java)
                .hasMessageStartingWith("already used email")
                .extracting("attribute", "value")
                .containsSequence(ATTR_EMAIL, email)
    }

    @Test
    fun `test create(cmd) with same name`() {
        // GIVEN
        val name = managerUtil.name
        val manager = borderline.create(CreateManagerCmd(UUID.randomUUID(), managerUtil.email, name,
                managerUtil.password))
        log.debug("GIVEN - manager=$manager")

        // WHEN & THEN
        assertThatThrownBy {
            borderline.create(CreateManagerCmd(UUID.randomUUID(), managerUtil.email, name,
                    managerUtil.password))
        }
                .isInstanceOf(AttributeValidationException::class.java)
                .hasMessageStartingWith("already used name")
                .extracting("attribute", "value")
                .containsSequence(ATTR_NAME1, name)
    }

    @Test
    fun `test create(cmd) with empty password`() {
        // GIVEN
        val cmd = CreateManagerCmd(UUID.randomUUID(), managerUtil.email, managerUtil.name, "")
        log.debug("GIVEN - cmd=$cmd")

        // WHEN & THEN
        assertThatThrownBy { borderline.create(cmd) }
                .isInstanceOf(AttributeValidationException::class.java)
                .hasMessageStartingWith("empty password")
                .extracting("attribute", "value")
                .containsSequence(ATTR_PASSWORD, "[ PROTECTED ]")
    }

    @Test
    fun `test create(cmd) with min length password`() {
        // GIVEN
        val cmd = CreateManagerCmd(UUID.randomUUID(), managerUtil.email, managerUtil.name, random(PASSWORD_MIN_LENGTH))
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val manager = borderline.create(cmd)
        log.debug("WHEN - manager=$manager")

        // THEN
        assertThat(manager)
                .extracting(ATTR_EMAIL, Manager.ATTR_NAME)
                .containsSequence(cmd.email, cmd.name)
        assertThat(manager.id)
                .isPositive()
        assertThat(manager.createdAt)
                .isEqualTo(manager.updatedAt)
                .isAfter(timeProvider.toZoneDateTime(before))
    }

    @Test
    fun `test create(cmd) with short password`() {
        // GIVEN
        val cmd = CreateManagerCmd(UUID.randomUUID(), managerUtil.email, managerUtil.name,
                random(PASSWORD_MIN_LENGTH - 1))
        log.debug("GIVEN - cmd=$cmd")

        // WHEN & THEN
        assertThatThrownBy { borderline.create(cmd) }
                .isInstanceOf(AttributeValidationException::class.java)
                .hasMessageStartingWith("too short password")
                .extracting("attribute", "value")
                .containsSequence(ATTR_PASSWORD, "[ PROTECTED ]")
    }

    @Test
    fun `test read(cmd)`() {
        // GIVEN
        val expected = borderline.create(CreateManagerCmd(UUID.randomUUID(), managerUtil.email, managerUtil.name,
                managerUtil.password))
        log.debug("GIVEN - expected=$expected")
        val cmd = ReadManagerCmd(randomUUID(), expected.id)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val actual = borderline.read(cmd)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isNotNull()
                .isNotSameAs(expected)
                .isEqualTo(expected)
    }
}
