package com.github.jzelenjak.suspiciousplugin.services

import com.intellij.openapi.project.Project
import com.github.jzelenjak.suspiciousplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
