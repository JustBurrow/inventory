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
        const val PREFIX = "layout/item"

        const val CREATE_FORM = "$PREFIX/create"
    }

    object C {
        const val GROUP = "/items"

        const val API_CREATE_FORM = "/create"
        const val API_CREATE = ""

    }
}
