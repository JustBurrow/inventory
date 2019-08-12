package kr.lul.inventory.test

import kr.lul.inventory.design.util.TimeProvider
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractTest {
    @Autowired
    protected lateinit var managerUtil: ManagerUtil
    @Autowired
    protected lateinit var timeProvider: TimeProvider
}
