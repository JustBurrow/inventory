package kr.lul.inventory.test.business

import kr.lul.inventory.business.service.params.CreateManagerParams
import kr.lul.inventory.test.data.ManagerDataUtil

open class ManagerBusinessUtl : ManagerDataUtil() {
    fun createParams() = CreateManagerParams(email(), name())
}
