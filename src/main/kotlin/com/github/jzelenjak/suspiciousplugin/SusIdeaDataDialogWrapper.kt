package com.github.jzelenjak.suspiciousplugin

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JPasswordField
import javax.swing.JTextField

class SusIdeaDataDialogWrapper : DialogWrapper(true) {

    // The elements of this dialog
    private val panel = JPanel(GridBagLayout());
    private val txtMode = JTextField();
    private val txtUserName = JTextField();
    private val txtPassword = JPasswordField();


    init {
        // Initialise the dialog wrapper (a call to the parent)
        init();
        title = "SusIdeaDemo Data";
        val state = SusIdeaDemoPluginSettings.getInstance().state;

        // Set the text that has been stored (if it actually has been stored) to the text field
        if (state != null) {
            txtMode.text = state.mode;
        }

        // "The combined name of your service and name of service that requires authentication."
        val credentialAttributes = CredentialAttributes("SusIdeaPlugin");
        // Get the stored credentials
        val credentials = PasswordSafe.instance.get(credentialAttributes);
        // Set the credentials to the corresponding text fields
        txtUserName.text = credentials?.userName.toString();
        txtPassword.text = credentials?.getPasswordAsString().toString();
    }

    // Create the actual panel
    override fun createCenterPanel(): JComponent {
        // Create a grid bag
        val gb = GridBag()
            .setDefaultInsets(Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBagConstraints.HORIZONTAL);

        panel.preferredSize = Dimension(400, 200);

        // Set the elements into the grid. 20% of the horizontal space is the label, 80% is the actual text field (vertically they have equal weights)
        panel.add(label("mode"), gb.nextLine().next().weightx(0.2));
        panel.add(txtMode, gb.next().weightx(0.8));
        panel.add(label("username"), gb.nextLine().next().weightx(0.2));
        panel.add(txtUserName, gb.next().weightx(0.8));
        panel.add(label("password"), gb.nextLine().next().weightx(0.2));
        panel.add(txtPassword, gb.next().weightx(0.8));

        return panel;
    }

    // When the user clicks `OK`
    override fun doOKAction() {
        // Get the input data
        val mode = txtMode.text;
        val username = txtUserName.text;
        val password = txtPassword.password;

        // Get the state
        val state = SusIdeaDemoPluginSettings.getInstance().state;
        // Set the mode if it is not null (if it has been entered)
        state?.mode = mode;

        // "The combined name of your service and name of service that requires authentication."
        val credentialAttributes = CredentialAttributes("SusIdeaPlugin", "suskey");
        // Create credentials
        val credentials = Credentials(username, password);
        // Store them safely
        PasswordSafe.instance.set(credentialAttributes, credentials);
    }

    // A helper method to create a label
    private fun label(text: String): JComponent {
        val label = JBLabel(text);
        label.componentStyle = UIUtil.ComponentStyle.SMALL;
        label.fontColor = UIUtil.FontColor.BRIGHTER;
        label.border = JBUI.Borders.empty(0, 5, 2, 0);
        return label;
    }
}