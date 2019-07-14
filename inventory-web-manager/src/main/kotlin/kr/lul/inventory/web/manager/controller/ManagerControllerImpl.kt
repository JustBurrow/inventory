package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.business.borderline.ManagerBorderline
import kr.lul.inventory.business.borderline.cmd.CreateManagerCmd
import kr.lul.inventory.design.domain.AttributeValidationException
import kr.lul.inventory.web.manager.controller.request.CreateManagerReq
import kr.lul.inventory.web.manager.mapping.ManagerMvc.M
import kr.lul.inventory.web.manager.mapping.ManagerMvc.V
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ModelAttribute
import javax.validation.Valid

@Controller
internal class ManagerControllerImpl : ManagerController {
    private val log = LoggerFactory.getLogger(ManagerControllerImpl::class.java)!!

    @Autowired
    private lateinit var managerBorderline: ManagerBorderline

    private fun validate(req: CreateManagerReq, result: BindingResult) {
        if (null != req.secret && !req.secret.equals(req.confirm)) {
            result.addError(FieldError(M.CREATE_MANAGER_REQ, "confirm", null,
                    false, arrayOf("err.manager.create.confirm-not-match"), arrayOf(),
                    "secret confirm does not match."))
        }
    }

    private fun doCreateForm(model: Model): String {
        if (!model.containsAttribute(M.CREATE_MANAGER_REQ))
            model.addAttribute(M.CREATE_MANAGER_REQ, CreateManagerReq())

        return V.CREATE_FORM
    }

    private fun doCreate(req: CreateManagerReq, result: BindingResult, model: Model): String {
        return try {
            managerBorderline.create(CreateManagerCmd(
                    req.email!!,
                    req.name!!,
                    req.secret!!
            ))

            "redirect:/"
        } catch (e: AttributeValidationException) {
            result.addError(FieldError(M.CREATE_MANAGER_REQ, e.attribute, e.value, false,
                    arrayOf(), arrayOf(), e.message))
            doCreateForm(model)
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.ManagerController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun createForm(model: Model): String {
        val template = doCreateForm(model)

        model.addAttribute(M.CREATE_MANAGER_REQ, CreateManagerReq())
        return V.CREATE_FORM
    }

    override fun create(@ModelAttribute(M.CREATE_MANAGER_REQ) @Valid req: CreateManagerReq, result: BindingResult,
                        model: Model): String {
        if (log.isTraceEnabled)
            log.trace("args : req={}, result={}, model={}", req, result, model)
        validate(req, result)
        val template = if (result.hasErrors())
            doCreateForm(model)
        else
            doCreate(req, result, model)

        if (log.isTraceEnabled) log.trace("result : template='{}', model={}", template, model)
        return template
    }
}
