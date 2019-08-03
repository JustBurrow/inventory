package kr.lul.inventory.data.dao

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.data.ManagerUtil
import kr.lul.inventory.data.NounUtil
import kr.lul.inventory.data.jpa.entity.CountableNounEntity
import kr.lul.inventory.data.jpa.entity.IdentifiableNounEntity
import kr.lul.inventory.data.jpa.entity.LimitedCountableNounEntity
import kr.lul.inventory.data.jpa.entity.LimitedIdentifiableNounEntity
import kr.lul.inventory.design.domain.CountableNoun
import kr.lul.inventory.design.domain.IdentifiableNoun
import kr.lul.inventory.design.domain.LimitedNoun.Companion.ATTR_LIMIT
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
import javax.persistence.EntityManager

/**
 * @author justburrow
 * @since 2019-08-04
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DataModuleTestConfiguration::class])
@Transactional
class NounDaoImplTest {
    private val log = LoggerFactory.getLogger(NounDaoImplTest::class.java)!!

    @Autowired
    private lateinit var dao: NounDao

    @Autowired
    private lateinit var entityManager: EntityManager
    @Autowired
    private lateinit var managerUtil: ManagerUtil
    @Autowired
    private lateinit var nounUtil: NounUtil
    @Autowired
    private lateinit var timeProvider: TimeProvider

    lateinit var before: Instant

    @Before
    fun setUp() {
        before = timeProvider.instant
    }

    @Test
    fun `test create() with IdentifiableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        log.debug("GIVEN - manager=$manager")

        val expected = nounUtil.freshIdentifiable(manager)
        val id = expected.id
        val key = expected.key
        val label = expected.label
        val labelCode = expected.labelCode
        val description = expected.description
        val createdAt = expected.createdAt
        val updatedAt = expected.updatedAt
        log.debug("GIVEN - expected=$expected")

        // WHEN
        val actual = dao.create(expected)
        log.debug("WHEN - actual")

        // THEN
        assertThat(actual)
                .isInstanceOf(IdentifiableNounEntity::class.java)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.IDENTIFIABLE, manager, key, label, labelCode, description, createdAt,
                        updatedAt)
        assertThat(actual.id)
                .isGreaterThan(id)
    }

    @Test
    fun `test create() with CountableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        log.debug("GIVEN - manager=$manager")

        val expected = nounUtil.freshCountable(manager)
        val id = expected.id
        val key = expected.key
        val label = expected.label
        val labelCode = expected.labelCode
        val description = expected.description
        val createdAt = expected.createdAt
        val updatedAt = expected.updatedAt
        log.debug("GIVEN - expected=$expected")

        // WHEN
        val actual = dao.create(expected)
        log.debug("WHEN - actual")

        // THEN
        assertThat(actual)
                .isInstanceOf(CountableNounEntity::class.java)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.COUNTABLE, manager, key, label, labelCode, description, createdAt, updatedAt)
        assertThat(actual.id)
                .isGreaterThan(id)
    }

    @Test
    fun `test create() with LimitedIdentifiableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        log.debug("GIVEN - manager=$manager")

        val expected = nounUtil.freshLimitedIdentifiable(manager)
        val id = expected.id
        val key = expected.key
        val label = expected.label
        val labelCode = expected.labelCode
        val limit = expected.limit
        val description = expected.description
        val createdAt = expected.createdAt
        val updatedAt = expected.updatedAt
        log.debug("GIVEN - expected=$expected")

        // WHEN
        val actual = dao.create(expected)
        log.debug("WHEN - actual")

        // THEN
        assertThat(actual)
                .isInstanceOf(LimitedIdentifiableNounEntity::class.java)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.LIMITED_IDENTIFIABLE, manager, key, label, labelCode, limit, description,
                        createdAt, updatedAt)
        assertThat(actual.id)
                .isGreaterThan(id)
    }

    @Test
    fun `test create() with LimitedCountableNoun`() {
        // GIVEN
        val manager = managerUtil.manager()
        log.debug("GIVEN - manager=$manager")

        val expected = nounUtil.freshLimitedCountable(manager)
        val id = expected.id
        val key = expected.key
        val label = expected.label
        val labelCode = expected.labelCode
        val limit = expected.limit
        val description = expected.description
        val createdAt = expected.createdAt
        val updatedAt = expected.updatedAt
        log.debug("GIVEN - expected=$expected")

        // WHEN
        val actual = dao.create(expected)
        log.debug("WHEN - actual")

        // THEN
        assertThat(actual)
                .isInstanceOf(LimitedCountableNounEntity::class.java)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.LIMITED_COUNTABLE, manager, key, label, labelCode, limit, description,
                        createdAt, updatedAt)
        assertThat(actual.id)
                .isGreaterThan(id)
    }

    @Test
    fun `test read(id) to get IdentifiableNoun`() {
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

        entityManager.flush()
        entityManager.clear()

        // WHEN
        val actual = dao.read(id) as IdentifiableNoun?
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isInstanceOf(IdentifiableNounEntity::class.java)
                .isEqualTo(expected)
                .isNotSameAs(expected)
                .extracting(ATTR_ID, ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(id, NounType.IDENTIFIABLE, manager, key, label, labelCode, description,
                        createdAt, updatedAt)
    }

    @Test
    fun `test read(id) to get CountableNoun`() {
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

        entityManager.flush()
        entityManager.clear()

        // WHEN
        val actual = dao.read(id) as CountableNoun?
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isInstanceOf(CountableNounEntity::class.java)
                .isEqualTo(expected)
                .isNotSameAs(expected)
                .extracting(ATTR_ID, ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(id, NounType.COUNTABLE, manager, key, label, labelCode, description,
                        createdAt, updatedAt)
    }

    @Test
    fun `test read(id) to get LimitedIdentifiableNoun`() {
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

        entityManager.flush()
        entityManager.clear()

        // WHEN
        val actual = dao.read(id) as IdentifiableNoun?
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isInstanceOf(LimitedIdentifiableNounEntity::class.java)
                .isEqualTo(expected)
                .isNotSameAs(expected)
                .extracting(ATTR_ID, ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(id, NounType.LIMITED_IDENTIFIABLE, manager, key, label, labelCode, limit, description,
                        createdAt, updatedAt)
    }

    @Test
    fun `test read(id) to get LimitedCountableNoun`() {
        // GIVEN
        val expected = nounUtil.limitedCountable()
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

        entityManager.flush()
        entityManager.clear()

        // WHEN
        val actual = dao.read(id) as LimitedCountableNounEntity?
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .isInstanceOf(LimitedCountableNounEntity::class.java)
                .isEqualTo(expected)
                .isNotSameAs(expected)
                .extracting(ATTR_ID, ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_LIMIT,
                        ATTR_DESCRIPTION, ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(id, NounType.LIMITED_COUNTABLE, manager, key, label, labelCode, limit, description,
                        createdAt, updatedAt)
    }
}
