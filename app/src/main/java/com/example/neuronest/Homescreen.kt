package com.neurokids.app.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// ─── Section Steps ────────────────────────────────────────────────────────────

/**
 * Every activity has exactly 4 ordered sections the child must complete.
 * [stepIndex] 0-based position used for progress calculation.
 */
enum class ActivitySection(val label: String, val icon: ImageVector, val stepIndex: Int) {
    PRESENTATION("Presentation", Icons.Default.AutoStories, 0),
    DRAG_DROP("Drag & Drop", Icons.Default.TouchApp, 1),
    SELECTION("Selection", Icons.Default.CheckBox, 2),
    YES_NO("Yes / No", Icons.Default.Quiz, 3)
}

// ─── Data Model ───────────────────────────────────────────────────────────────

/**
 * [sectionsCompleted] — how many of the 4 sections the child has finished (0–4).
 * progress is derived automatically from sectionsCompleted.
 */
data class Activity(
    val id: Int,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val color: Color,
    val gradientEnd: Color,
    /** 0 = not started, 1 = Presentation done, 2 = Drag&Drop done, 3 = Selection done, 4 = all done */
    val sectionsCompleted: Int = 0,
    val level: String = "Level 1",
    val subItems: List<String> = emptyList()
) {
    val totalSections: Int get() = ActivitySection.entries.size          // always 4
    val progress: Float get() = sectionsCompleted / totalSections.toFloat()
    val isCompleted: Boolean get() = sectionsCompleted >= totalSections
}

// ─── Sample Data ──────────────────────────────────────────────────────────────

val activities = listOf(
    Activity(
        id = 1, title = "Emotions", subtitle = "Recognise & express feelings",
        icon = Icons.Default.EmojiEmotions,
        color = Color(0xFFFF5722), gradientEnd = Color(0xFFFFAB91),
        sectionsCompleted = 4, level = "Level 1"
    ),
    Activity(
        id = 2, title = "My Personal Space", subtitle = "Understanding personal boundaries",
        icon = Icons.Default.PersonPin,
        color = Color(0xFF7C4DFF), gradientEnd = Color(0xFFB39DDB),
        sectionsCompleted = 3, level = "Level 1"
    ),
    Activity(
        id = 3, title = "Good & Bad Touch", subtitle = "Stay safe & speak up",
        icon = Icons.Default.PanTool,
        color = Color(0xFF00BCD4), gradientEnd = Color(0xFF80DEEA),
        sectionsCompleted = 2, level = "Level 2"
    ),
    Activity(
        id = 4, title = "Manners", subtitle = "Please, thank you & more",
        icon = Icons.Default.EmojiPeople,
        color = Color(0xFF4CAF50), gradientEnd = Color(0xFFA5D6A7),
        sectionsCompleted = 1, level = "Level 2"
    ),
    Activity(
        id = 5, title = "Social Stories", subtitle = "Learn kind behaviour",
        icon = Icons.Default.People,
        color = Color(0xFFE91E63), gradientEnd = Color(0xFFF48FB1),
        sectionsCompleted = 0, level = "Level 3",
        subItems = listOf("No Hitting", "No Pinching", "No Biting", "No Spitting", "No Shouting")
    ),
    Activity(
        id = 6, title = "Brain Boosting", subtitle = "Critical thinking at home",
        icon = Icons.Default.Psychology,
        color = Color(0xFFFFC107), gradientEnd = Color(0xFFFFE082),
        sectionsCompleted = 0, level = "Level 3"
    ),
    Activity(
        id = 7, title = "Simple Yoga", subtitle = "ABCD Pose — calm your body",
        icon = Icons.Default.SelfImprovement,
        color = Color(0xFF009688), gradientEnd = Color(0xFF80CBC4),
        sectionsCompleted = 0, level = "Level 4"
    )
)

// ─── Root Screen ──────────────────────────────────────────────────────────────

@Composable
fun HomeDashboard(
    childName: String = "Alex",
    onNavigate: (String) -> Unit = {},
    overallProgress: Float = activities.run {
        sumOf { it.sectionsCompleted }.toFloat() / (size * 4)
    }
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4FF))
    ) {
        LeftSidebar(
            childName = childName,
            overallProgress = overallProgress,
            onNavigate = onNavigate,
            modifier = Modifier.width(300.dp).fillMaxHeight()
        )
        RightContent(
            onNavigate = onNavigate,
            modifier = Modifier.weight(1f).fillMaxHeight()
        )
    }
}

// ─── Left Sidebar ─────────────────────────────────────────────────────────────

@Composable
fun LeftSidebar(childName: String, overallProgress: Float,  onNavigate: (String) -> Unit = {}, modifier: Modifier = Modifier) {
    var animatedProgress by remember { mutableFloatStateOf(0f) }
    val completedCount = activities.count { it.isCompleted }
    val isPreview = LocalInspectionMode.current
    var showLogoutDialog by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!isPreview) delay(400)
        animate(
            initialValue = 0f, targetValue = overallProgress,
            animationSpec = tween(durationMillis = if (isPreview) 0 else 1400, easing = EaseOutCubic)
        ) { v, _ -> animatedProgress = v }
    }

    Card(
        modifier = modifier
            .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 10.dp)
            .shadow(12.dp, RoundedCornerShape(32.dp)),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))
            AnimatedAvatarBubble()
            Spacer(Modifier.height(16.dp))
            Text("Hi, $childName! 👋", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3D3D3D))
            Text("Keep up the great work!", fontSize = 13.sp, color = Color(0xFF9E9E9E))
            Spacer(Modifier.height(28.dp))
            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                StatChip("$completedCount", "Done", Color(0xFF4CAF50))
                StatChip("${activities.size - completedCount}", "Left", Color(0xFFFF5722))
                StatChip("${activities.size}", "Total", Color(0xFF7C4DFF))
            }
            Spacer(Modifier.height(32.dp))
            SideNavItem(Icons.Default.Home, "Home", selected = true, Color(0xFF7C4DFF),
                onClick = { onNavigate("DashBoard") })
            Spacer(Modifier.height(8.dp))
            SideNavItem(Icons.Default.Info, "About App", selected = false, Color(0xFF7C4DFF),
                onClick = { onNavigate("about") })
            Spacer(Modifier.height(8.dp))
            SideNavItem(Icons.Default.PlayCircle, "Tutorial", selected = false, Color(0xFF7C4DFF),
                onClick = { onNavigate("tutorial") })
            Spacer(Modifier.height(8.dp))
            SideNavItem(Icons.Default.Feedback, "Feedback", selected = false, Color(0xFF7C4DFF),
                onClick = { onNavigate("feedback") })
            SideNavItem(Icons.Default.Logout, "Logout", selected = false, Color(0xFFE53935),
                onClick = { showLogoutDialog = true })

            if (showLogoutDialog) {
                AlertDialog(
                    onDismissRequest = { showLogoutDialog = false },
                    title = { Text("Logout", fontWeight = FontWeight.Bold) },
                    text = { Text("Are you sure you want to logout?") },
                    confirmButton = {
                        TextButton(onClick = {
                            showLogoutDialog = false
                            onNavigate("LoginScreen")
                        }) {
                            Text("Yes, Logout", color = Color(0xFFE53935))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showLogoutDialog = false }) {
                            Text("Cancel", color = Color(0xFF7C4DFF))
                        }
                    }
                )
            }
            Spacer(Modifier.height(8.dp))
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.height(16.dp))
            OutlinedButton(
                onClick = { onNavigate("settings")}, shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF7C4DFF)),
                border = BorderStroke(1.5.dp, Color(0xFFEDE7F6))
            ) {
                Icon(Icons.Default.Settings, null, Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Settings", fontSize = 14.sp)
            }
        }
    }
}

// ─── Pulsing Avatar ───────────────────────────────────────────────────────────

@Composable
fun AnimatedAvatarBubble() {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.06f,
        animationSpec = infiniteRepeatable(tween(1200, easing = EaseInOutSine), RepeatMode.Reverse),
        label = "scale"
    )
    Box(
        modifier = Modifier.size(100.dp).scale(pulseScale).clip(CircleShape)
            .background(Brush.linearGradient(listOf(Color(0xFF7C4DFF), Color(0xFF00BCD4)))),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.EmojiNature, null, tint = Color.White, modifier = Modifier.size(56.dp))
    }
}

// ─── Level Ring ───────────────────────────────────────────────────────────────



// ─── Stat Chip ────────────────────────────────────────────────────────────────

@Composable
fun StatChip(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(48.dp).clip(RoundedCornerShape(14.dp))
                .background(color.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = color)
        }
        Spacer(Modifier.height(4.dp))
        Text(label, fontSize = 11.sp, color = Color(0xFF9E9E9E))
    }
}

// ─── Side Nav Item ────────────────────────────────────────────────────────────

@Composable
fun SideNavItem(icon: ImageVector, label: String, selected: Boolean, color: Color, onClick: () -> Unit = {} ) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            .background(if (selected) color.copy(alpha = 0.10f) else Color.Transparent)    .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, label, tint = if (selected) color else Color(0xFFBDBDBD), modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(14.dp))
        Text(
            label, fontSize = 15.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) color else Color(0xFF9E9E9E)
        )
        if (selected) {
            Spacer(Modifier.weight(1f))
            Box(Modifier.size(8.dp).clip(CircleShape).background(color))
        }
    }
}

// ─── Right Content ────────────────────────────────────────────────────────────

@Composable
fun RightContent( onNavigate: (String) -> Unit = {}, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 10.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)) {
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Activities", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3D3D3D))
                Text(
                    "${activities.count { it.isCompleted }} of ${activities.size} completed",
                    fontSize = 13.sp, color = Color(0xFF9E9E9E)
                )
            }
            Box(
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFEDE7F6))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("A1  ›", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF7C4DFF))
            }
        }
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            itemsIndexed(activities) { index, activity ->
                AnimatedTabletCard(activity = activity, index = index,  onNavigate = onNavigate )
            }
        }
    }
}

// ─── Animated Card Wrapper ────────────────────────────────────────────────────

@Composable
fun AnimatedTabletCard(activity: Activity, index: Int,  onNavigate: (String) -> Unit = {}  ) {
    val isPreview = LocalInspectionMode.current
    var visible by remember { mutableStateOf(isPreview) }
    LaunchedEffect(Unit) {
        if (!isPreview) {
            delay(index * 70L + 100L)
            visible = true
        }
    }
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            initialScale = 0.85f,
            animationSpec = spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessMedium)
        ) + fadeIn(tween(250))
    ) {
        TabletActivityCard(activity = activity, onNavigate = onNavigate )
    }
}

// ─── Tablet Activity Card ─────────────────────────────────────────────────────

@Composable
fun TabletActivityCard(activity: Activity,onNavigate: (String) -> Unit = {}) {
    val isCompleted = activity.isCompleted
    val cardBg = if (isCompleted)
        Brush.linearGradient(listOf(activity.color, activity.gradientEnd))
    else
        Brush.linearGradient(listOf(Color.White, Color.White))

    Card(
        modifier = Modifier.fillMaxWidth().shadow(6.dp, RoundedCornerShape(24.dp))
            .clickable {
                // Map activity id to navigation route
                when (activity.id) {
                    1 -> onNavigate("Task3SelectionScreen")   // Emotions
                    2 -> onNavigate("DragandDropQuiz")        // Personal Space
                    3 -> onNavigate("Task1SelectionScreen")   // Good & Bad Touch
                    4 -> onNavigate("Task2SelectionScreen")   // Manners
                    5 -> onNavigate("task4selection1")        // Social Stories
                    // add more as needed
                }
            },
        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().background(cardBg).padding(18.dp)
        ) {
            Column {
                // ── Top row: icon + completion badge ──────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier.size(54.dp).clip(RoundedCornerShape(16.dp))
                            .background(
                                if (isCompleted) Color.White.copy(alpha = 0.25f)
                                else activity.color.copy(alpha = 0.13f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            activity.icon, activity.title,
                            tint = if (isCompleted) Color.White else activity.color,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    // Completion badge or section count
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                            .background(
                                if (isCompleted) Color.White.copy(alpha = 0.30f)
                                else activity.color.copy(alpha = 0.10f)
                            )
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCompleted) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(Icons.Default.Check, "Done", tint = Color.White, modifier = Modifier.size(14.dp))
                                Text("Done", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        } else {
                            Text(
                                "${activity.sectionsCompleted}/${activity.totalSections}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = activity.color
                            )
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // ── Title & subtitle ──────────────────────────────────────────
                Text(
                    activity.title, fontSize = 17.sp, fontWeight = FontWeight.Bold,
                    color = if (isCompleted) Color.White else Color(0xFF3D3D3D)
                )
                Text(
                    activity.subtitle, fontSize = 12.sp,
                    color = if (isCompleted) Color.White.copy(alpha = 0.75f) else Color(0xFF9E9E9E)
                )

                // ── Sub-items ─────────────────────────────────────────────────
                if (activity.subItems.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        activity.subItems.take(3).forEach { item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Box(
                                    Modifier.size(5.dp).clip(CircleShape)
                                        .background(
                                            if (isCompleted) Color.White.copy(alpha = 0.70f)
                                            else activity.color.copy(alpha = 0.60f)
                                        )
                                )
                                Text(
                                    item, fontSize = 11.sp,
                                    color = if (isCompleted) Color.White.copy(alpha = 0.85f) else Color(0xFF757575)
                                )
                            }
                        }
                        if (activity.subItems.size > 3) {
                            Text(
                                "+${activity.subItems.size - 3} more", fontSize = 10.sp,
                                color = if (isCompleted) Color.White.copy(alpha = 0.65f) else activity.color,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(14.dp))

                // ── Section Steps ─────────────────────────────────────────────


                Spacer(Modifier.height(12.dp))

                // ── Overall progress bar ──────────────────────────────────────

                }
            }
        }
    }


// ─── Section Steps Row ────────────────────────────────────────────────────────
/**
 * Shows 4 step bubbles: Presentation, Drag & Drop, Selection, Yes/No
 * ✓ filled = completed, number = not yet done, connector line between steps.
 */
@Composable
fun SectionStepsRow(activity: Activity) {
    val isCompleted = activity.isCompleted
    val sections = ActivitySection.entries

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        sections.forEachIndexed { index, section ->
            val stepDone = activity.sectionsCompleted > section.stepIndex
            val stepActive = activity.sectionsCompleted == section.stepIndex

            // Connector line before each step except the first
            if (index > 0) {
                val prevDone = activity.sectionsCompleted > sections[index - 1].stepIndex
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .background(
                            when {
                                isCompleted -> Color.White.copy(alpha = 0.5f)
                                prevDone -> activity.color.copy(alpha = 0.7f)
                                else -> activity.color.copy(alpha = 0.15f)
                            }
                        )
                )
            }

            // Step bubble
        }
    }
}

// ─── Previews ─────────────────────────────────────────────────────────────────

@Preview(
    name = "Tablet Landscape 10-inch",
    device = "spec:width=1280dp,height=800dp,dpi=240,isRound=false,chinSize=0dp,orientation=landscape",
    showBackground = true
)
@Composable
fun HomeDashboardTabletPreview() {
    MaterialTheme { HomeDashboard() }
}

@Preview(
    name = "Tablet Portrait 10-inch",
    device = "spec:width=800dp,height=1280dp,dpi=240,isRound=false,chinSize=0dp,orientation=portrait",
    showBackground = true
)
@Composable
fun HomeDashboardTabletPortraitPreview() {
    MaterialTheme { HomeDashboard() }
}