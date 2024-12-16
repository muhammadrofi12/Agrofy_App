package com.example.agrofy_app.ui.screen.manajemen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.data.DummyData
import com.example.agrofy_app.ui.components.ProgressItem
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenPrimary

@Composable
fun ManajemenProgressScreen(
    navController: NavController
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Progres Limbah", "Riwayat")

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Progres Limbah",
                img = R.drawable.ic_back_circle,
                onIconButtonClick = { navController.navigate("manajemen") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Tab Layout
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color.White,
                    contentColor = GreenPrimary,
                    indicator = { tabPositions ->
                        SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            height = 2.dp,
                            color = GreenPrimary
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = selectedTab == index,
                            onClick = {
                                selectedTab = index
                                if (index == 1) {
                                    navController.navigate("riwayat") {
                                        popUpTo("progress") { inclusive = true }
                                    }
                                }
                            },
                            selectedContentColor = GreenPrimary,
                            unselectedContentColor = Color.Gray
                        )
                    }
                }

                // Total
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .background(GreenPrimary, shape = RoundedCornerShape(12.dp))
//                            .padding(horizontal = 16.dp, vertical = 8.dp)
//                            .zIndex(2f)
//                    ) {
//                        Text(
//                            text = "Total",
//                            style = PoppinsBold20,
//                            color = Color.White
//                        )
//                    }
//
//                    Box(
//                        modifier = Modifier
//                            .offset(x = (-20).dp)
//                            .zIndex(1f)
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .background(GreenLight, shape = RoundedCornerShape(12.dp))
//                                .border(width = 2.dp, color = GreenPrimary, shape = RoundedCornerShape(12.dp))
//                                .padding(start = 28.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
//
//                        ) {
//                            Text(
//                                text = "100 kg",
//                                style = PoppinsBold18,
//                                color = Color.Black
//                            )
//                        }
//                    }
//
//                }

                Spacer(modifier = Modifier.height(12.dp))

                // Limbah List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(DummyData.limbahItem) { limbah ->
                        ProgressItem(
                            limbah = limbah,
                            navController = navController
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManajemenProgressScreenPreview() {
    val navController = rememberNavController()
    ManajemenProgressScreen(navController = navController)
}