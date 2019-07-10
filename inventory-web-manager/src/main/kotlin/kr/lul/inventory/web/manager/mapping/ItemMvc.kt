package kr.lul.inventory.web.manager.mapping

/**
 * @author justburrow
 * @since 2019-07-10
 */
object ItemMvc {
    object M {
        const val CREATE_REQ = "createReq"
    }

    object V {
        const val PREFIX = "item/"

        const val CREATE_FORM = "${PREFIX}create"
    }

    object C {

        const val GROUP = "/item"
        const val API_CREATE_FORM = "/create"
        const val API_CREATE = ""

    }
}
