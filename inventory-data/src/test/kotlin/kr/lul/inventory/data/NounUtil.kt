package kr.lul.inventory.data

import kr.lul.inventory.design.domain.Noun.Companion.DESCRIPTION_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.KEY_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.LABEL_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.isValidKey
import kr.lul.inventory.design.domain.Noun.Companion.isValidLabel
import kr.lul.inventory.design.domain.Noun.Companion.isValidLabelCode
import org.apache.commons.lang3.RandomStringUtils.*
import org.slf4j.LoggerFactory
import java.util.concurrent.ThreadLocalRandom.current

/**
 * @author justburrow
 * @since 2019-08-04
 */
class NounUtil {
    private val log = LoggerFactory.getLogger(NounUtil::class.java)!!

    val key: String
        get() {
            var key: String

            do {
                key = "test.${randomAlphabetic(1)}${randomAlphanumeric(0, KEY_MAX_LENGTH - 15)}"
                        .toLowerCase()
            } while (!isValidKey(key))

            return key
        }
    val label: String
        get() {
            var label: String

            do {
                label = "test noun label - ${random(current().nextInt(1, LABEL_MAX_LENGTH - 20))}"
            } while (!isValidLabel(label))

            return label
        }

    val labelCode: String
        get() {
            var labelCode: String

            do {
                labelCode = "test.${randomAlphabetic(1)}".toLowerCase()
            } while (!isValidLabelCode(labelCode))

            return labelCode
        }

    val description: String
        get() = random(current().nextInt(0, 1 + DESCRIPTION_MAX_LENGTH))
}
