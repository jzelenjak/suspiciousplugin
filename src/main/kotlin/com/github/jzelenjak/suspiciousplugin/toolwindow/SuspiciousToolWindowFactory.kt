package com.github.jzelenjak.suspiciousplugin.toolwindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import javax.swing.JLabel
import javax.swing.SwingConstants

/**
 * A factory class that is needed for a tool window
 */
class SuspiciousToolWindowFactory : ToolWindowFactory {

    /**
     * Create the content for the tool window and register the content
     */
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val testGeniePanelWrapper = SuspiciousToolWindow(toolWindow);
        val contentFactory : ContentFactory = ContentFactory.SERVICE.getInstance();
        val content : Content = contentFactory.createContent(testGeniePanelWrapper.getContent(), "Suspicious Parameters", false);
        toolWindow.contentManager.addContent(content);

        val label = JLabel("This is where all the suspicious people will appear");
        label.horizontalAlignment = SwingConstants.CENTER;
        toolWindow.contentManager.addContent(contentFactory.createContent(label, "Suspicious People", false));
    }
}