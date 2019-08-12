package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.business.borderline.cmd.*
import kr.lul.inventory.design.domain.LimitedNoun.Companion.ATTR_LIMIT
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_CREATED_AT
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_DESCRIPTION
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_ID
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_KEY
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_LABEL
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_LABEL_CODE
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_TYPE
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_UPDATED_AT
import kr.lul.inventory.design.domain.NounType
import kr.lul.inventory.dto.*
import kr.lul.inventory.test.AbstractTest
import kr.lul.inventory.test.NounUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.ZonedDateTime
import java.util.UUID.randomUUID
import java.util.concurrent.ThreadLocalRandom.current

/**
 * @author justburrow
 * @since 2019-07-09
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
class NounBorderlineImplTest : AbstractTest() {
    private val log = LoggerFactory.getLogger(NounBorderlineImplTest::class.java)!!

    @Autowired
    private lateinit var borderline: NounBorderline

    @Autowired
    private lateinit var nounUtil: NounUtil

    private lateinit var before: ZonedDateTime

    @Before
    fun setUp() {
        before = timeProvider.zonedDateTime
    }

    @Test
    fun `test create(cmd) CountableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val cmd = CreateCountableNounCmd(
                randomUUID(),
                manager.id,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                nounUtil.description)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val noun = borderline.create(cmd)
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .isInstanceOf(CountableNounDetailDto::class.java)
                .extracting(ATTR_TYPE, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION)
                .containsSequence(NounType.COUNTABLE, cmd.key, cmd.label, cmd.labelCode, cmd.description)
        assertThat(noun.id)
                .isPositive()
        assertThat(noun.createdAt)
                .isEqualTo(noun.updatedAt)
                .isAfter(before)
    }

    @Test
    fun `test read(cmd) CountableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val expected = borderline.create(CreateCountableNounCmd(
                randomUUID(),
                manager.id,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                nounUtil.description))
        log.debug("GIVEN - expected=$expected")

        val cmd = ReadNounCmd(randomUUID(), manager.id, expected.id)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val actual = borderline.read<CountableNounDetailDto>(cmd)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isNotNull
                .isInstanceOf(CountableNounDetailDto::class.java)
                .isNotSameAs(expected)
                .isEqualTo(expected)
                .extracting(ATTR_ID, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(expected.id, expected.key, expected.label, expected.labelCode, expected.description,
                        expected.createdAt, expected.updatedAt)
        assertThat(actual?.manager)
                .extracting(Manager.ATTR_ID, Manager.ATTR_NAME)
                .containsSequence(manager.id, manager.name)
    }

    @Test
    fun `test create(cmd) IdentifiableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val cmd = CreateIdentifiableNounCmd(randomUUID(), manager.id, nounUtil.key, nounUtil.label,
                nounUtil.labelCode, nounUtil.description)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val noun = borderline.create(cmd)
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .isInstanceOf(IdentifiableNounDetailDto::class.java)
                .extracting(ATTR_TYPE, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION)
                .containsSequence(NounType.IDENTIFIABLE, cmd.key, cmd.label, cmd.labelCode, cmd.description)
        assertThat(noun.id)
                .isPositive()
        assertThat(noun.createdAt)
                .isEqualTo(noun.updatedAt)
                .isAfter(before)
    }

    @Test
    fun `test read(cmd) IdentifiableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val expected = borderline.create(CreateIdentifiableNounCmd(
                randomUUID(),
                manager.id,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                nounUtil.description))
        log.debug("GIVEN - expected=$expected")

        val cmd = ReadNounCmd(randomUUID(), manager.id, expected.id)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val actual = borderline.read<NounDetailDto>(cmd)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isNotNull
                .isInstanceOf(IdentifiableNounDetailDto::class.java)
                .isNotSameAs(expected)
                .isEqualTo(expected)
                .extracting(ATTR_ID, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(expected.id, expected.key, expected.label, expected.labelCode, expected.description,
                        expected.createdAt, expected.updatedAt)
        assertThat(actual?.manager)
                .extracting(Manager.ATTR_ID, Manager.ATTR_NAME)
                .containsSequence(manager.id, manager.name)
    }

    @Test
    fun `test create(cmd) LimitedCountableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val cmd = CreateLimitedCountableNounCmd(randomUUID(), manager.id, nounUtil.key, nounUtil.label,
                nounUtil.labelCode, current().nextInt(1, Int.MAX_VALUE), nounUtil.description)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val noun = borderline.create(cmd)
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .isInstanceOf(LimitedCountableNounDetailDto::class.java)
                .extracting(ATTR_TYPE, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT, ATTR_DESCRIPTION)
                .containsSequence(NounType.LIMITED_COUNTABLE, cmd.key, cmd.label, cmd.labelCode, cmd.limit,
                        cmd.description)
        assertThat(noun.id)
                .isPositive()
        assertThat(noun.createdAt)
                .isEqualTo(noun.updatedAt)
                .isAfter(before)
    }

    @Test
    fun `test read(cmd) LimitedCountableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val expected = borderline.create(CreateLimitedCountableNounCmd(
                randomUUID(),
                manager.id,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                current().nextInt(1, Int.MAX_VALUE),
                nounUtil.description))
        log.debug("GIVEN - expected=$expected")

        val cmd = ReadNounCmd(randomUUID(), manager.id, expected.id)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val actual = borderline.read<NounDetailDto>(cmd)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isNotNull
                .isInstanceOf(LimitedCountableNounDetailDto::class.java)
                .isNotSameAs(expected)
                .isEqualTo(expected)
                .extracting(ATTR_ID, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(expected.id, expected.key, expected.label, expected.labelCode, expected.limit,
                        expected.description, expected.createdAt, expected.updatedAt)
        assertThat(actual?.manager)
                .extracting(Manager.ATTR_ID, Manager.ATTR_NAME)
                .containsSequence(manager.id, manager.name)
    }

    @Test
    fun `test create(cmd) LimitedIdentifiableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val cmd = CreateLimitedIdentifiableNounCmd(randomUUID(), manager.id, nounUtil.key, nounUtil.label,
                nounUtil.labelCode, current().nextInt(1, Int.MAX_VALUE), nounUtil.description)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val noun = borderline.create(cmd)
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .isInstanceOf(LimitedIdentifiableNounDetailDto::class.java)
                .extracting(ATTR_TYPE, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT, ATTR_DESCRIPTION)
                .containsSequence(NounType.LIMITED_IDENTIFIABLE, cmd.key, cmd.label, cmd.labelCode, cmd.limit,
                        cmd.description)
        assertThat(noun.id)
                .isPositive()
        assertThat(noun.createdAt)
                .isEqualTo(noun.updatedAt)
                .isAfter(before)
    }

    @Test
    fun `test read(cmd) LimitedIdentifiableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val expected = borderline.create(CreateLimitedIdentifiableNounCmd(
                randomUUID(),
                manager.id,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                current().nextInt(1, Int.MAX_VALUE),
                nounUtil.description))
        log.debug("GIVEN - expected=$expected")

        val cmd = ReadNounCmd(randomUUID(), manager.id, expected.id)
        log.debug("GIVEN - cmd=$cmd")

        // WHEN
        val actual = borderline.read<NounDetailDto>(cmd)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isNotNull
                .isInstanceOf(LimitedIdentifiableNounDetailDto::class.java)
                .isNotSameAs(expected)
                .isEqualTo(expected)
                .extracting(ATTR_ID, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(expected.id, expected.key, expected.label, expected.labelCode, expected.limit,
                        expected.description, expected.createdAt, expected.updatedAt)
        assertThat(actual?.manager)
                .extracting(Manager.ATTR_ID, Manager.ATTR_NAME)
                .containsSequence(manager.id, manager.name)
    }

    @Test
    fun `test read(cmd) with not exist noun id(Int#MAX_VALUE)`() {
        // GIVEN
        val manager = managerUtil.manager()
        log.debug("GIVEN - manager=$manager")

        // WHEN
        val noun = borderline.read<NounDetailDto>(ReadNounCmd(randomUUID(), manager.id, Int.MAX_VALUE))
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .isNull()
    }

    @Test
    fun `test read(cmd) with not exist noun id(0)`() {
        // GIVEN
        val manager = managerUtil.manager()
        log.debug("GIVEN - manager=$manager")

        // WHEN
        val noun = borderline.read<NounDetailDto>(ReadNounCmd(randomUUID(), manager.id, 0))
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .isNull()
    }
}
