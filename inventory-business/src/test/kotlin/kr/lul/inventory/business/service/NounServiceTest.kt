package kr.lul.inventory.business.service

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.test.business.NounBusinessUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-08
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
@Transactional
class NounServiceTest {
    private val log = LoggerFactory.getLogger(NounServiceTest::class.java)!!

    @Autowired
    private lateinit var nounService: NounService
    @Autowired
    private lateinit var testUtil: NounBusinessUtil

    @Test
    fun `test create Noun with random params`() {
        // GIVEN
        val params = testUtil.createParams()
        log.debug("GIVEN - params={}", params)

        // WHEN
        val noun = nounService.create(params)
        log.debug("WHEN - noun={}", noun)

        // THEN
        assertThat(noun)
                .extracting(Noun.ATTR_KEY, Noun.ATTR_LABEL, Noun.ATTR_LABEL_CODE)
                .containsSequence(params.key, params.label, params.labelCode)
        assertThat(noun.getId())
                .isGreaterThan(0L)
    }
}
