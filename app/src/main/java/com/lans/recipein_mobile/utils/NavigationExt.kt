package com.lans.recipein_mobile.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph

fun NavController.safeNavigate(direction: NavDirections) {
    val currentDestination = this.currentDestination
    if (currentDestination != null) {
        val action = currentDestination.getAction(direction.actionId)
        if (action != null) {
            val destinationId = action.destinationId
            val currentNode =
                if (currentDestination is NavGraph) currentDestination else currentDestination.parent
            if (destinationId != 0 && currentNode != null && currentNode.findNode(destinationId) != null) {
                this.navigate(direction)
            }
        }
    }
}