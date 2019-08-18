package kr.lul.inventory.design.domain

import kr.lul.inventory.design.domain.NounType.IDENTIFIABLE
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-15
 */
interface IdentifiableNoun : Noun {
    interface Updater : Noun.Updater {
        override val type: NounType
            get() = IDENTIFIABLE
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.IdentifiableNoun
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override val type: NounType
        get() = NounType.IDENTIFIABLE

    override fun updater(updatedAt: Instant): Updater
}
