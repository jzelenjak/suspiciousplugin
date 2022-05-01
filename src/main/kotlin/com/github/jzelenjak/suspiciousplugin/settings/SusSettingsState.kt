package com.github.jzelenjak.suspiciousplugin.settings

/**
 * This class is the actual data class that stores the values of the Settings entries.
 */
data class SusSettingsState
    constructor(var numImpostors: String = "60", var doYouLikeAmongUs: Boolean = false);
