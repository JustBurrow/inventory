package kr.lul.inventory.web.manager.mapping

/**
 * @author justburrow
 * @since 2019-07-14
 */
object ManagerMvc {
    object M {
        const val CREATE_MANAGER_REQ = "createManagerReq"
    }

    object V {
        const val PREFIX = "layout/manager"

        const val CREATE_FORM = "$PREFIX/create"
    }

    object C {
        const val GROUP = "/managers"

        const val API_CREATE_FORM = "/create"
        const val API_CREATE = ""
    }
}
