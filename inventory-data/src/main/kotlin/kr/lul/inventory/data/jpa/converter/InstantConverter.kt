package kr.lul.inventory.data.jpa.converter

import java.time.Instant
import javax.persistence.AttributeConverter
import javax.persistence.Converter

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Converter(autoApply = true)
class InstantConverter : AttributeConverter<Instant, Long> {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // org.hibernate.mapping.AttributeContainer
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun convertToDatabaseColumn(instant: Instant?): Long? = when (instant) {
        null -> null
        else -> instant.toEpochMilli()
    }

    override fun convertToEntityAttribute(utcMillis: Long?): Instant? = when (utcMillis) {
        null -> null
        else -> Instant.ofEpochMilli(utcMillis)
    }
}
