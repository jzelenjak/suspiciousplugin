package com.github.jzelenjak.suspiciousplugin.actions

import com.github.jzelenjak.suspiciousplugin.SusPopupList
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.popup.JBPopupFactory

/**
 * A suspicious action that allows the user to choose an item from a list
 */
class SuspiciousAction4 : AnAction() {

    // The important method that has to be overridden. Called whenever a user triggers the action
    override fun actionPerformed(e: AnActionEvent) {
        val susPopupList = SusPopupList("sus-idea-list", mutableListOf("amogus", "impostor", "sus"));
        if (e.project != null) {
            JBPopupFactory.getInstance().createListPopup(susPopupList, 5).showCenteredInCurrentWindow(e.project!!); // `!!` is a non-null assertion
        }
    }
}