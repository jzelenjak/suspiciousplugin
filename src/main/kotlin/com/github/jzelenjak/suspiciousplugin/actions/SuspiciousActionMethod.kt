package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import com.intellij.psi.search.searches.OverridingMethodsSearch
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil

class SuspiciousActionMethod : AnAction(){
    override fun actionPerformed(e: AnActionEvent) {
        val psiElement = e.dataContext.getData(CommonDataKeys.PSI_ELEMENT)!!
        val psiFile = e.dataContext.getData(CommonDataKeys.PSI_FILE)

        val method : String = psiElement.toString().split(":")[1]
        val surroundingClass : PsiClass = PsiTreeUtil.getParentOfType(psiElement, PsiClass::class.java) as PsiClass
        val surroundingClassName : String = surroundingClass.qualifiedName ?: return

        Messages.showInfoMessage(
            "Selected method $surroundingClassName::$method by invoking action ${ActionManager.getInstance().getId(this)}\n\n"
                    + "The contents of the method:\n\n${psiElement.text}\n\n"
                    + "Usages: ${ReferencesSearch.search(psiElement).findAll().map{ it.toString().split(":")[1] }}\n\n"
                    + "This method is overridden by: ${OverridingMethodsSearch.search(psiElement as PsiMethod).findAll().map { (PsiTreeUtil.getParentOfType(it, PsiClass::class.java) as PsiClass).toString().split(":")[1] + "::" + it.toString().split(":")[1] }}",
            "Selected Suspicious Method"
        )
    }

    override fun update(e: AnActionEvent) {
        val psiElement = e.dataContext.getData(CommonDataKeys.PSI_ELEMENT)
        println("Invoking update method of ${ActionManager.getInstance().getId(this)} on $psiElement")
        e.presentation.isVisible = psiElement is PsiMethod
    }
}