package kr.lul.inventory.design.domain

import kr.lul.inventory.design.domain.NounType.COUNTABLE
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-15
 */
interface CountableNoun : Noun {
    interface Updater : Noun.Updater {
        override val type: NounType
            get() = COUNTABLE
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.CountableNoun
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override val type: NounType
        get() = NounType.COUNTABLE

    override fun updater(updatedAt: Instant): Updater
}
