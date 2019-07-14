package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.DataModuleTestConfiguration
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.test.data.NounDataUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author justburrow
 * @since 2019-07-06
 */
@RunWith(SpringRunner::class)
@DataJpaTest
@ContextConfiguration(classes = [DataModuleTestConfiguration::class])
class NounRepositoryTest {
    private val log = LoggerFactory.getLogger(NounRepositoryTest::class.java)!!

    @Autowired
    private lateinit var nounRepository: NounRepository

    @Autowired
    private lateinit var nounDataUtil: NounDataUtil

    @Test
    fun `test findAll()`() {
        assertThat(nounRepository.findAll())
                .isEmpty()
    }

    @Test
    fun `test save() with random Noun instance`() {
        // GIVEN
        val expected = nounDataUtil.random()
        val id = expected.getId()
        val key = expected.getKey()
        val label = expected.getLabel()
        val labelCode = expected.getLabelCode()
        log.debug("GIVEN - noun={}", expected)

        // WHEN
        val actual = nounRepository.save(expected)
        log.debug("WHEN - actual={}", actual)

        // THEN
        assertThat(actual)
                .isNotNull
                .extracting(Noun.ATTR_KEY, Noun.ATTR_LABEL, Noun.ATTR_LABEL_CODE)
                .containsSequence(key, label, labelCode)
        assertThat(actual.getId())
                .isGreaterThan(0L)
                .isNotEqualTo(id)
    }
}
