package kr.lul.inventory.business.service

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.business.service.params.CreateCountableNounParams
import kr.lul.inventory.business.service.params.CreateIdentifiableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedCountableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedIdentifiableNounParams
import kr.lul.inventory.data.jpa.entity.CountableNounEntity
import kr.lul.inventory.data.jpa.entity.IdentifiableNounEntity
import kr.lul.inventory.data.jpa.entity.LimitedIdentifiableNounEntity
import kr.lul.inventory.design.domain.IdentifiableNoun
import kr.lul.inventory.design.domain.LimitedNoun.Companion.ATTR_LIMIT
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_CREATED_AT
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_DESCRIPTION
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_ID
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_KEY
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_LABEL
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_LABEL_CODE
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_MANAGER
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_TYPE
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_UPDATED_AT
import kr.lul.inventory.design.domain.NounType
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.test.ManagerUtil
import kr.lul.inventory.test.NounUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.UUID.randomUUID
import java.util.concurrent.ThreadLocalRandom.current
import javax.persistence.EntityManager

/**
 * @author justburrow
 * @since 2019-08-04
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
@Transactional
class NounServiceImplTest {
    private val log = LoggerFactory.getLogger(NounServiceImplTest::class.java)!!

    @Autowired
    private lateinit var service: NounService

    @Autowired
    private lateinit var timeProvider: TimeProvider
    @Autowired
    private lateinit var managerUtil: ManagerUtil
    @Autowired
    private lateinit var nounUtil: NounUtil
    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var before: Instant

    @Before
    fun setUp() {
        before = timeProvider.instant
    }

    @Test
    fun `test create(params) IdentifiableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val params = CreateIdentifiableNounParams(
                randomUUID(),
                manager,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                nounUtil.description,
                before)
        log.debug("GIVEN - params=$params")

        // WHEN
        val noun: IdentifiableNoun = service.create(params)
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .isInstanceOf(IdentifiableNounEntity::class.java)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.IDENTIFIABLE, manager, params.key, params.label, params.labelCode,
                        params.description, before, before)
        assertThat(noun.id)
                .isPositive()
    }

    @Test
    fun `test read(id) IdentifiableNoun`() {
        // GIVEN
        val expected = nounUtil.identifiable()
        val id = expected.id
        val manager = expected.manager
        val key = expected.key
        val label = expected.label
        val labelCode = expected.labelCode
        val description = expected.description
        val createdAt = expected.createdAt
        val updatedAt = expected.updatedAt
        log.debug("GIVEN - expected=$expected")

        entityManager.clear()

        // WHEN
        val actual = service.read<Noun>(id)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isNotSameAs(expected)
                .isEqualTo(expected)
                .isInstanceOf(IdentifiableNounEntity::class.java)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.IDENTIFIABLE, manager, key, label, labelCode, description,
                        createdAt, updatedAt)
    }

    @Test
    fun `test create(params) CountableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val params = CreateCountableNounParams(
                randomUUID(),
                manager,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                nounUtil.description,
                before)
        log.debug("GIVEN - params=$params")

        // WHEN
        val actual = service.create(params)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.COUNTABLE, manager, params.key, params.label, params.labelCode,
                        params.description, before, before)
        assertThat(actual.id)
                .isPositive()
    }

    @Test
    fun `test read(id) CountableNoun`() {
        // GIVEN
        val expected = nounUtil.countable()
        val id = expected.id
        val manager = expected.manager
        val key = expected.key
        val label = expected.label
        val labelCode = expected.labelCode
        val description = expected.description
        val createdAt = expected.createdAt
        val updatedAt = expected.updatedAt
        log.debug("GIVEN - expected=$expected")

        entityManager.clear()

        // WHEN
        val actual = service.read<Noun>(id)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isNotSameAs(expected)
                .isEqualTo(expected)
                .isInstanceOf(CountableNounEntity::class.java)
                .extracting(ATTR_ID, ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(id, NounType.COUNTABLE, manager, key, label, labelCode, description,
                        createdAt, updatedAt)
    }

    @Test
    fun `test create create(params) LimitedIdentifiableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val params = CreateLimitedIdentifiableNounParams(
                randomUUID(),
                manager,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                current().nextInt(1, Int.MAX_VALUE),
                nounUtil.description,
                before)
        log.debug("GIVEN - params=$params")

        // WHEN
        val noun = service.create(params)
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE,
                        ATTR_LIMIT, ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.LIMITED_IDENTIFIABLE, manager, params.key, params.label, params.labelCode,
                        params.limit, params.description, params.createAt, params.createAt)
        assertThat(noun.id)
                .isPositive()
    }

    @Test
    fun `test read(id) LimitedIdentifiableNoun`() {
        // GIVEN
        val expected = nounUtil.limitedIdentifiable()
        val id = expected.id
        val manager = expected.manager
        val key = expected.key
        val label = expected.label
        val labelCode = expected.labelCode
        val limit = expected.limit
        val description = expected.description
        val createdAt = expected.createdAt
        val updatedAt = expected.updatedAt
        log.debug("GIVEN - expected=$expected")

        entityManager.clear()

        // WHEN
        val actual = service.read<Noun>(id)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isNotSameAs(expected)
                .isEqualTo(expected)
                .isInstanceOf(LimitedIdentifiableNounEntity::class.java)
                .extracting(ATTR_ID, ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(id, NounType.LIMITED_IDENTIFIABLE, manager, key, label, labelCode, limit,
                        description, createdAt, updatedAt)
    }

    @Test
    fun `test create(params) LimitedCountableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        val params = CreateLimitedCountableNounParams(
                randomUUID(),
                manager,
                nounUtil.key,
                nounUtil.label,
                nounUtil.labelCode,
                current().nextInt(1, Int.MAX_VALUE),
                nounUtil.description,
                before)
        log.debug("GIVEN - params=$params")

        // WHEN
        val noun = service.create(params)
        log.debug("WHEN - noun=$noun")

        // THEN
        assertThat(noun)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE,
                        ATTR_LIMIT, ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.LIMITED_COUNTABLE, manager, params.key, params.label, params.labelCode,
                        params.limit, params.description, params.createAt, params.createAt)
        assertThat(noun.id)
                .isPositive()
    }
}
