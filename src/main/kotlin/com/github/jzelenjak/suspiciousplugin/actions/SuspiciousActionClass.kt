package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiClass
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil

class SuspiciousActionClass : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val psiClass = e.dataContext.getData(CommonDataKeys.PSI_ELEMENT)!! as PsiClass
        val psiFile = e.dataContext.getData(CommonDataKeys.PSI_FILE)
        val classFQN = psiClass.qualifiedName ?: return

        Messages.showInfoMessage(
            "Selected class $classFQN by invoking action ${ActionManager.getInstance().getId(this)}\n\n"
            + "Usages: ${ReferencesSearch.search(psiClass).findAll().map{ it.resolve().toString().split(":")[1] }}\n\n"
            + "This class has a superclass: ${psiClass.superClass?.qualifiedName}",
            "Selected Suspicious Class"
        )
    }

    override fun update(e: AnActionEvent) {
        val psiElement = e.dataContext.getData(CommonDataKeys.PSI_ELEMENT)
        println("Invoking update method of ${ActionManager.getInstance().getId(this)} on $psiElement")
        e.presentation.isVisible = psiElement is PsiClass
    }
}