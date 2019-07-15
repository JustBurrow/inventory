package kr.lul.inventory.data.jpa.factory

import kr.lul.inventory.data.jpa.entity.NounEntity
import kr.lul.inventory.design.factory.NounFactory
import org.springframework.stereotype.Component

/**
 * @author justburrow
 * @since 2019-07-08
 */
@Component
class NounFactoryImpl : NounFactory {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.factory.NounFactory
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun instance(key: String, label: String, labelCode: String) = NounEntity(key, label, labelCode)
}
