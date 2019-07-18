package kr.lul.inventory.test.business

import kr.lul.inventory.business.service.params.CreateManagerParams
import kr.lul.inventory.test.data.ManagerDataUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

open class ManagerBusinessUtil : ManagerDataUtil() {
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun createParams() = CreateManagerParams(email(), name(), password())
}
