package com.github.jzelenjak.suspiciousplugin

import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep

// Basically, a popup panel with a list of elements that you can select from
class SusPopupList(title: String, fruits: List<String>) : BaseListPopupStep<String>(title, fruits) {

    override fun isSpeedSearchEnabled(): Boolean {
        return true;
    }

    override fun onChosen(selectedValue: String?, finalChoice: Boolean): PopupStep<*>? {
        Messages.showInfoMessage("You have chosen $selectedValue", "Your Choice");
        return FINAL_CHOICE;
    }
}
