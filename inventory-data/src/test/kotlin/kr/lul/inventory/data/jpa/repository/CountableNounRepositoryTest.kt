package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.data.ManagerUtil
import kr.lul.inventory.data.NounUtil
import kr.lul.inventory.data.jpa.entity.CountableNounEntity
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_CREATED_AT
import kr.lul.inventory.design.domain.Noun.Companion.ATTR_DESCRIPTION
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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import javax.persistence.EntityManager

/**
 * @author justburrow
 * @since 2019-07-06
 */
@RunWith(SpringRunner::class)
@DataJpaTest
@ContextConfiguration(classes = [DataModuleTestConfiguration::class])
class CountableNounRepositoryTest {
    private val log = LoggerFactory.getLogger(CountableNounRepositoryTest::class.java)!!
    @Autowired
    private lateinit var repository: CountableNounRepository

    @Autowired
    private lateinit var nounRepository: NounRepository
    @Autowired
    private lateinit var timeProvider: TimeProvider
    @Autowired
    private lateinit var entityManager: EntityManager
    @Autowired
    private lateinit var nounUtil: NounUtil
    @Autowired
    private lateinit var managerUtil: ManagerUtil

    private lateinit var before: Instant

    @Before
    fun setUp() {
        before = timeProvider.instant
    }

    @Test
    fun `test findAll()`() {
        // WHEN
        val list = repository.findAll()
        log.debug("WHEN - list=$list")

        // THEN
        assertThat(list)
                .isEmpty()
    }

    @Test
    fun `test save()`() {
        // GIVEN
        val password = managerUtil.password
        val manager = managerUtil.manager(password)
        log.debug("GIVEN - manager=$manager, password='$password'")

        val key = nounUtil.key
        val label = nounUtil.label
        val labelCode = nounUtil.labelCode
        val description = nounUtil.description
        val expected = CountableNounEntity(manager, key, label, labelCode, description, before)
        val id = expected.id
        log.debug("GIVEN - expected=$expected, id=$id")

        // WHEN
        val actual = repository.saveAndFlush(expected)
        log.debug("WHEN - actual=$actual")

        // THEN
        assertThat(actual)
                .extracting(ATTR_TYPE, ATTR_MANAGER, ATTR_KEY, ATTR_LABEL, ATTR_LABEL_CODE, ATTR_DESCRIPTION,
                        ATTR_CREATED_AT, ATTR_UPDATED_AT)
                .containsSequence(NounType.COUNTABLE, manager, key, label, labelCode, description, before, before)
        assertThat(actual.id)
                .isNotEqualTo(id)
                .isGreaterThan(0)

        entityManager.flush()
        entityManager.clear()

        val noun = nounRepository.findById(actual.id).get()
        log.debug("THEN - noun=$noun")

        assertThat(noun)
                .isInstanceOf(CountableNounEntity::class.java)
    }
}
