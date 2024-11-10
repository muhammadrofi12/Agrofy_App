package com.example.agrofy_app.ui.screen.manajemen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.data.DummyData
import com.example.agrofy_app.ui.components.RiwayatItem
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import java.util.Calendar

@Composable
fun ManajemenRiwayatScreen(
    navController: NavController
) {
    var selectedTab by remember { mutableIntStateOf(1) }
    val tabs = listOf("Progres Limbah", "Riwayat")

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Riwayat Limbah",
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
                                if (index == 0) {
                                    navController.navigate("progress") {
                                        popUpTo("riwayat") { inclusive = true }
                                    }
                                }
                            },
                            selectedContentColor = GreenPrimary,
                            unselectedContentColor = Color.Gray
                        )
                    }
                }

                // Tanggal untuk cetak laporan
                DateInputFields(
                    onGenerateReport = { startDate, endDate ->
                        // Handle report generation here
                        // You can implement PDF generation or any other report format
                    }
                )

                // Limbah List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(DummyData.limbahItem) { limbah ->
                        RiwayatItem(limbah)
                    }
                }
            }
        }
    }
}


@Composable
fun DateInputFields(
    onGenerateReport: (String, String) -> Unit
) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Dialog untuk memilih tanggal awal
    val startDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            startDate = "$dayOfMonth/${month + 1}/$year"
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )

    // Dialog untuk memilih tanggal akhir
    val endDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            endDate = "$dayOfMonth/${month + 1}/$year"
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Tanggal Awal
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Tanggal Awal",
                    style = PoppinsRegular12,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                TextField(
                    value = startDate,
                    onValueChange = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .clickable { startDatePickerDialog.show() },
                    enabled = false,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        disabledTextColor = Color.Black,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(4.dp),
                    readOnly = true,
                    singleLine = true
                )
            }

            // Tanggal Akhir
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Tanggal Akhir",
                    style = PoppinsRegular12,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                TextField(
                    value = endDate,
                    onValueChange = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .clickable { endDatePickerDialog.show() },
                    enabled = false,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        disabledTextColor = Color.Black,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(4.dp),
                    readOnly = true,
                    singleLine = true
                )
            }
        }

        Button(
            onClick = { onGenerateReport(startDate, endDate) },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Cetak Laporan", color = Color.White)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ManajemenRiwayatScreenPreview() {
    val navController = rememberNavController()
    ManajemenRiwayatScreen(navController = navController)
}