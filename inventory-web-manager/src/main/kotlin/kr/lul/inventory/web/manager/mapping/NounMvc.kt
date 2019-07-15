package kr.lul.inventory.web.manager.mapping

/**
 * @author justburrow
 * @since 2019-07-10
 */
object NounMvc {
    object M {
        const val CREATE_NOUN_REQ = "createNounReq"
    }

    object V {
        const val PREFIX = "layout/noun"

        const val CREATE_FORM = "$PREFIX/create"
    }

    object C {
        const val GROUP = "/nouns"

        const val API_CREATE_FORM = "/create"
        const val API_CREATE = ""

        const val FULL_API_CREATE_FORM = "$GROUP$API_CREATE_FORM"
        const val FULL_API_CREATE = GROUP
    }
}
