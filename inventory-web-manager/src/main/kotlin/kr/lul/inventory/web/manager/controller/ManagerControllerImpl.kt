package kr.lul.inventory.web.manager.controller

import kr.lul.inventory.business.borderline.ManagerBorderline
import kr.lul.inventory.business.borderline.cmd.CreateManagerCmd
import kr.lul.inventory.design.domain.AttributeValidationException
import kr.lul.inventory.web.manager.configuration.ErrorCode.ManagerErrorCode
import kr.lul.inventory.web.manager.controller.request.CreateManagerReq
import kr.lul.inventory.web.manager.mapping.IndexMvc
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
                    false, arrayOf(ManagerErrorCode.CREATE_CONFIRM_NOT_MATCH), arrayOf(),
                    "secret confirm does not match."))
        }
    }

    private fun doCreateForm(model: Model): String {
        if (!model.containsAttribute(M.CREATE_MANAGER_REQ))
            model.addAttribute(M.CREATE_MANAGER_REQ, CreateManagerReq())

        return V.CREATE_FORM
    }

    private fun doCreate(req: CreateManagerReq, result: BindingResult, model: Model): String =
            try {
                managerBorderline.create(CreateManagerCmd(
                        req.email!!,
                        req.name!!,
                        req.secret!!
                ))

                "redirect:${IndexMvc.C.FULL_API_SIGN_IN}"
            } catch (e: AttributeValidationException) {
                result.addError(FieldError(M.CREATE_MANAGER_REQ, e.attribute, e.value,
                        false, arrayOf(ManagerErrorCode.CREATE_UNKNOWN), arrayOf(), e.message))
                doCreateForm(model)
            }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.web.manager.controller.ManagerController
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun createForm(model: Model): String {
        val template = doCreateForm(model)

        model.addAttribute(M.CREATE_MANAGER_REQ, CreateManagerReq())
        return V.CREATE_FORM
    }

    override fun create(@ModelAttribute(M.CREATE_MANAGER_REQ) @Valid req: CreateManagerReq, binding: BindingResult,
                        model: Model): String {
        if (log.isTraceEnabled)
            log.trace("args : req={}, binding={}, model={}", req, binding, model)
        validate(req, binding)

        val template = if (binding.hasErrors())
            doCreateForm(model)
        else
            doCreate(req, binding, model)

        if (log.isTraceEnabled)
            log.trace("binding : template='{}', model={}", template, model)
        return template
    }
}
