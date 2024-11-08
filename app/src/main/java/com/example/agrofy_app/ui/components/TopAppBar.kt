package com.example.agrofy_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.ui.theme.PoppinsRegular20

@Composable
fun TopAppBar(
    navController: NavController,
    title: String,
    img: Int,
    isIconButtonEnabled: Boolean = true,
    onIconButtonClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .drawBehind {
                drawRect(
                    color = Color.Black.copy(alpha = 0.1f),
                    topLeft = Offset(0f, size.height),
                    size = Size(size.width, size.height * 0.09f)
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (isIconButtonEnabled) {
                        onIconButtonClick?.invoke() ?: navController.navigateUp()
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .then(
                        if (isIconButtonEnabled) Modifier else Modifier.clickable {  }
                    )
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "Back",
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = title,
                style = PoppinsRegular20,
                color = Color.Black
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    Agrofy_AppTheme {
        TopAppBar(
            navController = rememberNavController(),
            title = "Artikel",
            img = R.drawable.ic_back_circle
        )
    }
}