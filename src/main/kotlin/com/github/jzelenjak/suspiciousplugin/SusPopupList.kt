package com.github.jzelenjak.suspiciousplugin

import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep

// Basically, a popup panel with a list of elements that you can select from
class SusPopupList(title: String, fruits: List<String>) : BaseListPopupStep<String>(title, fruits) {

    override fun isSpeedSearchEnabled(): Boolean {
        return true;
    }

    override fun onChosen(selectedValue: String?, finalChoice: Boolean): PopupStep<*>? {
        return FINAL_CHOICE;
    }
}
