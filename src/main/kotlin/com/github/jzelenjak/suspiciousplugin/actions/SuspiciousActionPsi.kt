package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages

class SuspiciousActionPsi : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showInfoMessage("Selected ${e.dataContext.getData(CommonDataKeys.PSI_ELEMENT)}", "Selected PSI Element")
    }
}