package kr.lul.inventory.business.converter

import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.dto.ManagerDetailDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
internal class ManagerConverterImpl : ManagerConverter {
    private val log = LoggerFactory.getLogger(ManagerConverterImpl::class.java)!!

    @Autowired
    private lateinit var timeProvider: TimeProvider

    private fun detail(manager: Manager): ManagerDetailDto =
            ManagerDetailDto(manager.id,
                    manager.email,
                    manager.name,
                    timeProvider.toZoneDateTime(manager.createdAt))

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.converter.ManagerConverter
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> convert(manager: Manager, type: KClass<T>): T {
        if (log.isTraceEnabled)
            log.trace("args : manager={}, type={}", manager, type)

        val dto: T = when (type) {
            ManagerDetailDto::class -> detail(manager) as T
            else -> throw IllegalArgumentException("unsupported type : $type")
        }

        if (log.isTraceEnabled) log.trace("return : {}", dto)
        return dto
    }
}
