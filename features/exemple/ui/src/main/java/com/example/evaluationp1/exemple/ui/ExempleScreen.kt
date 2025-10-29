package com.example.evaluationp1.exemple.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState

class ExempleScreen {
}

@Composable
fun ExempleScreen(
    viewModel: ExempleViewModel,
    onEditType: (Int) -> Unit,
    onCreateType: () -> Unit,
    onDeleteType: () -> Unit,
    navController: NavController
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is TypeEdited -> onEditType(event.id)
                is TypeCreated -> onCreateType()
                is TypeDeleted -> onDeleteType()
            }
        }
    }

    Content(
        uiState = uiState,
        onAction = viewModel::onAction,
        navController = navController,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}

@RequiresApi(Build.VERSION_CODES.FROYO)
@Composable
private fun Content(
    uiState: TypeUiState,
    onAction: (TypeUiAction) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        topBar = { AppTopBar() },
        bottomBar = {
            AdminNavigationBar(navController = navController, currentRoute = currentRoute)
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(LightBlue)
                    .clickable { onAction(ClickedOnCreate) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Créer un type",
                    tint = DarkBlue,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
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
                    text = "Gestion des types",
                    style = MaterialTheme.typography.headlineLarge,
                    color = DarkBlue,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                HorizontalDivider(color = LightBlue.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(uiState.types.size) { index ->
                val type = uiState.types[index]
                TypeExpandableItem(
                    type = type,
                    onToggle = { onAction(ClickedOnToggle(type.id)) },
                    onModifier = { onAction(ClickedOnEdit(type.id)) },
                    onDelete = { onAction(ClickedOnDelete(type.id)) },
                    onCreate = { onAction(ClickedOnCreate) }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.FROYO)
@Composable
fun TypeExpandableItem(
    type: Type,
    onToggle: () -> Unit,
    onModifier: () -> Unit,
    onDelete: () -> Unit,
    onCreate: () -> Unit
) {
    Column {
        // Carte principale (titre)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clickable { onToggle() },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = type.nom,
                    color = DarkBlue,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = if (type.expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                    contentDescription = "Expand",
                    tint = AccentYellow
                )
            }
        }

        // Détails développés
        AnimatedVisibility(visible = type.expanded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Type de salle : ${type.nom}",
                        color = DarkBlue,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Description : ${type.description}",
                        color = DarkBlue.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = onModifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AccentYellow
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Modifier", color = DarkBlue, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = onDelete,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = LightBlue
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Supprimer", color = DarkBlue, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}