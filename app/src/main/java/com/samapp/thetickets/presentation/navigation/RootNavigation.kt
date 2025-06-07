package com.samapp.thetickets.presentation.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.samapp.thetickets.presentation.Screens.AddTicketScreen
import com.samapp.thetickets.presentation.Screens.EditTicketDetails
import com.samapp.thetickets.presentation.Screens.HomeScreen
import com.samapp.thetickets.presentation.Screens.TicketDetailsScreen
import com.samapp.thetickets.presentation.components.FloatingActionButtonComposable
import com.samapp.thetickets.presentation.components.topbars.AddTicketTopBar
import com.samapp.thetickets.presentation.components.topbars.EditTicketDetailsScreen
import com.samapp.thetickets.presentation.components.topbars.HomeTopBar
import com.samapp.thetickets.presentation.components.topbars.TicketDetailTopBar
import com.samapp.thetickets.presentation.viewModels.ticketViewModel
import com.samapp.thetickets.ui.theme.Background
import com.samapp.thetickets.ui.theme.OnSurface
import com.samapp.thetickets.ui.theme.Surface

fun isForwardNavigation(previous: String?, current: String?): Boolean {
    // Simple heuristic: assume forward if current route is "deeper"
    if (previous == null || current == null) return true
    return current.length > previous.length
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rootNavigation() {
    val navController = rememberNavController()
    val enterTransition: EnterTransition = fadeIn() + slideInHorizontally(initialOffsetX = { it })
    val exitTransition: ExitTransition = fadeOut() + slideOutHorizontally(targetOffsetX = { -it })
    val popEnterTransition: EnterTransition =
        fadeIn() + slideInHorizontally(initialOffsetX = { -it })
    val popExitTransition: ExitTransition = fadeOut() + slideOutHorizontally(targetOffsetX = { it })

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    var previousRoute by remember { mutableStateOf<String?>(null) }
    val viewModel: ticketViewModel = hiltViewModel()
    val addTicketState by viewModel.addTicketState.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        topBar = {
            AnimatedContent(
                modifier = Modifier.background(Surface),
                targetState = currentRoute,
//                transitionSpec = {
//                    val forward = isForwardNavigation(previousRoute, targetState)
//                    previousRoute = targetState
//
//                    if (forward) {
//                        (fadeIn(animationSpec = tween(300)) + slideInHorizontally { it }) with
//                                (fadeOut(animationSpec = tween(300)) + slideOutHorizontally { -it })
//                    } else {
//                        (fadeIn(animationSpec = tween(300)) + slideInHorizontally { -it }) with
//                                (fadeOut(animationSpec = tween(300)) + slideOutHorizontally { it })
//                    }
//                }
            ) { route ->
                when {
                    currentRoute == Screen.Home.route -> HomeTopBar()
                    currentRoute == Screen.AddTicket.route -> AddTicketTopBar(onBack = { navController.navigateUp() })
                    currentRoute?.startsWith("ticket_details/") == true -> {
                        val ticketId = navController.currentBackStackEntry?.arguments
                            ?.getString("ticketId")
                            ?.toIntOrNull()

                        Log.d("root", ticketId.toString())
                        TicketDetailTopBar(
                            onBack = { navController.navigateUp() },
                            onUpdate = {
                                ticketId?.let { id ->
                                    Log.d("root", id.toString())
                                    navController.navigate(Screen.EditTicket(id).createRoute())
                                }
                            },
                            onDelete = {
                                ticketId?.let { id ->
                                    viewModel.deleteTicket(id)
                                    navController.navigateUp()
                                }
                            }
                        )
                    }

                    currentRoute?.startsWith("edit_ticket/") == true -> EditTicketDetailsScreen(
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            AnimatedContent(currentRoute) { route ->
                when (route) {
                    Screen.Home.route -> FloatingActionButtonComposable(
                        {
                            navController.navigate(Screen.AddTicket.route)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                Screen.Home.route,
                enterTransition = { enterTransition },
                exitTransition = { exitTransition },
                popEnterTransition = { popEnterTransition },
                popExitTransition = { popExitTransition }
            ) {
                HomeScreen(
                    viewModel = viewModel,
                    onTicketClick = { id ->
                        navController.navigate(Screen.TicketDetails(id).createRoute())
                    }
                )
            }
            composable(
                route = Screen.AddTicket.route,
                enterTransition = { enterTransition },
                exitTransition = { exitTransition },
                popEnterTransition = { popEnterTransition },
                popExitTransition = { popExitTransition }
            ) {
                AddTicketScreen(
                    onAddTicket = { ticket ->
                        viewModel.addTicket(ticket = ticket)
                    },
                    viewModel,
                    onAddSuccess = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = "ticket_details/{ticketId}",
                enterTransition = { enterTransition },
                exitTransition = { exitTransition },
                popEnterTransition = { popEnterTransition },
                popExitTransition = { popExitTransition }
            ) { entry ->
                val id = entry.arguments?.getString("ticketId")?.toIntOrNull() ?: return@composable
                TicketDetailsScreen(
                    ticketId = id,
                    viewModel
                )
            }
            composable(
                route = "edit_ticket/{ticketId}",
                enterTransition = { enterTransition },
                exitTransition = { exitTransition },
                popEnterTransition = { popEnterTransition },
                popExitTransition = { popExitTransition }
            ) { entry ->
                val id = entry.arguments?.getString("ticketId")?.toIntOrNull() ?: return@composable
                EditTicketDetails(
                    ticketId = id,
                    viewModel,
                    onUpdateTicket = { ticket ->
                        viewModel.updateTicket(ticket = ticket)
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}