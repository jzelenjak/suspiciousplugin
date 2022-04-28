package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.Messages

/**
 * A suspicious action that allows the user to choose a directory
 */
class SuspiciousAction2 : AnAction() {

    // The important method that has to be overridden. Called whenever a user triggers the action
    override fun actionPerformed(e: AnActionEvent) {
        showFileDialog(e);
    }

    // Shows a file dialog
    private fun showFileDialog(e: AnActionEvent) {
        // create a file chooser descriptor with parameters: `chooseFiles`, `chooseFolders`, `chooseJars`, `chooseJarsAsFiles`, `chooseJarContents`, `chooseMultiple`
        val fileChooserDescriptor = FileChooserDescriptor(false, true, false, false, false, false);
        fileChooserDescriptor.title = "Suspicious Pick Directory";
        fileChooserDescriptor.description = "Suspicious chooser amogus";

        // Let the user choose a file, then execute a callback
        FileChooser.chooseFile(fileChooserDescriptor, e.project, null) {
            // Show message dialog with the following parameters: project, message, title and icon
            Messages.showMessageDialog(e.project, "You have selected ${it.path}", "Selected Path", Messages.getInformationIcon());
        }
    }
}