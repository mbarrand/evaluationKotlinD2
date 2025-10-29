package com.example.evaluationp1.agents.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.SpeakerHigh
import com.example.evaluationp1.core.ui.component.NavigationBar
import com.example.evaluationp1.core.system.playDefault
import com.example.evaluationp1.core.system.vibrate
import android.widget.Toast
import com.example.evaluationp1.features.agents.ui.R
import androidx.compose.ui.res.stringResource

/***
 * Screen permettant d'afficher la liste des agents
 */
@Composable
fun AgentsScreen(
    viewModel: AgentsViewModel,
    onAgentClick: (String) -> Unit,
    navController: NavController
) {
    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is SendSoundAndVibration -> onAgentClick(event.id)
                UsingLocalCache -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.toast_cached_data),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Content(
        uiState = uiState,
        onAction = viewModel::onAction,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
}

/***
 * Composant d'affichage de la liste des agents
 */
@Composable
private fun Content(
    uiState: AgentUiState,
    onAction: (AgentUiAction) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(navController = navController, currentRoute = currentRoute)
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.title_agents),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(uiState.agents.size) { index ->
                val agent = uiState.agents[index]
                AgentCard(
                    label = agent.name,
                    onAgentClick = {
                        context.vibrate()
                        context.playDefault(R.raw.ace)
                    }
                )
            }
        }
    }
}

/***
 * Composant des cards des agents
 */
@Composable
fun AgentCard(
    label: String,
    onAgentClick: () -> Unit
) {
    Card(
        onClick = onAgentClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Icon(
                imageVector = PhosphorIcons.Bold.SpeakerHigh,
                contentDescription = stringResource(R.string.cd_speaker),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}