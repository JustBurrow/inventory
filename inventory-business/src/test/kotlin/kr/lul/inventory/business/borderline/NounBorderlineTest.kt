package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.BusinessModuleTestConfiguration
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.test.business.NounBusinessUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author justburrow
 * @since 2019-07-09
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BusinessModuleTestConfiguration::class])
class NounBorderlineTest {
    private val log = LoggerFactory.getLogger(NounBorderlineTest::class.java)!!

    @Autowired
    private lateinit var nounBorderline: NounBorderline
    @Autowired
    private lateinit var testUtil: NounBusinessUtil

    @Before
    fun setUp() {
    }

    @Test
    fun `test create(cmd) with random`() {
        // GIVEN
        val cmd = testUtil.createCmd()
        log.debug("GIVEN - cmd={}", cmd)

        // WHEN
        val dto = nounBorderline.create(cmd)
        log.debug("WHEN - dto={}", dto)

        // Then
        assertThat(dto)
                .extracting(Noun.ATTR_KEY, Noun.ATTR_LABEL, Noun.ATTR_LABEL_CODE)
                .containsSequence(cmd.key, cmd.label, cmd.labelCode)
        assertThat(dto.id)
                .isGreaterThan(0L)
    }
}
