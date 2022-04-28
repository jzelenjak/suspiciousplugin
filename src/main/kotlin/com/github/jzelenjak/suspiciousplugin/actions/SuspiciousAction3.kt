package com.github.jzelenjak.suspiciousplugin.actions

import com.github.jzelenjak.suspiciousplugin.SusIdeaDataDialogWrapper
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * A suspicious action that shows a custom dialog and allows to enter mode, username and password
 */
class SuspiciousAction3 : AnAction() {

    // The important method that has to be overridden. Called whenever a user triggers the action
    override fun actionPerformed(e: AnActionEvent) {
        showCustomDialog(e);
    }

    // Display a custom dialog. Here we will have a state for user's credentials
    private fun showCustomDialog(e: AnActionEvent) {
        val susIdeaDataDialogWrapper = SusIdeaDataDialogWrapper();
        susIdeaDataDialogWrapper.show();
    }
}