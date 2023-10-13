package com.bcan.arabamcomassignment.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun CarListScreen(
    viewModel: CarListViewModel = hiltViewModel()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(20) {
            CarListItem()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CarListItem() {

    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(4.dp)
            .height(80.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://arbstorage.mncdn.com/ilanfotograflari/2020/11/12/15954751/cad6412e-5500-45fe-84d0-5b57e0d7eb05_image_for_silan_15954751_800x600.jpg",
                contentDescription = "Araba",
                modifier = Modifier
                    .height(72.dp)
                    .width(92.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 6.dp),
            ) {
                Text(
                    text = "Sahibinden Volkswagen Passat 2.0 TDi BlueMotion Comfortline 2015 Model    Bütün ağir bakimlari  yetkili  servis doğuş otoda yapilmiş sifir akü sifir asdansdkjasndkjasdnas dasjkd naskj ndsa ksja asdasdasd",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 11.sp,
                    )
                )

                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "İstanbul / Küçükçekmece", fontSize = 9.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "806.000 TL", fontSize = 10.sp, color = Color.Red)
                }
            }
        }
    }
}