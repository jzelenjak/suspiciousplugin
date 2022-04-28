package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * A suspicious action that asks the user to choose a file using a fancy selector
 */
class SuspiciousAction6 : AnAction(){

    // The important method that has to be overridden. Called whenever a user triggers the action
    override fun actionPerformed(e: AnActionEvent) {
        val dialogWrapper : DialogWrapper = object : DialogWrapper(e.project) {
            val txtFieldBrowse = TextFieldWithBrowseButton();
            val panel : JPanel = JPanel(BorderLayout());
            val descriptor = FileChooserDescriptor(true, false, false, false, false, false);

            init {
                descriptor.title = "Suspicious Pick File";
                descriptor.description = "Suspicious chooser amogus";

                txtFieldBrowse.addBrowseFolderListener("Your Choice", "", e.project, descriptor);

                init();
            }

            override fun createCenterPanel(): JComponent {
                txtFieldBrowse.size = Dimension(900, 40);

                panel.size = Dimension(1000, 200);
                panel.add(txtFieldBrowse);

                return panel;
            }

            override fun doOKAction() {
                Messages.showInfoMessage(e.project,"You have chosen file `${txtFieldBrowse.text}`", "Your Selection");
                close(0);
            }
        }
        dialogWrapper.show();
    }

}