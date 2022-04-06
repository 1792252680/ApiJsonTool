package com.github.1792252680.apijsontool.services

import com.intellij.openapi.project.Project
import com.github.1792252680.apijsontool.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
