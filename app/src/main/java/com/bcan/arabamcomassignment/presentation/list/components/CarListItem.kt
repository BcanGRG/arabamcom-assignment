package com.bcan.arabamcomassignment.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bcan.arabamcomassignment.R
import com.bcan.arabamcomassignment.data.model.response.CarListResponse

@Composable
fun CarListItem(
    car: CarListResponse,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(4.dp)
            .height(80.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RectangleShape,
    ) {
        val carPhoto = car.photo?.replace("{0}", "800x600")
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = carPhoto,
                contentDescription = car.title,
                modifier = Modifier
                    .height(72.dp)
                    .width(92.dp),
                placeholder = painterResource(id = R.drawable.loading_car_placeholder),
                error = painterResource(id = R.drawable.error_car_image)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 6.dp),
            ) {
                Text(
                    text = car.title ?: "Veri bulunamadı",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 11.sp,
                    )
                )

                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "${car.location?.cityName} / ${car.location?.townName}",
                        fontSize = 9.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = car.priceFormatted ?: "Veri bulunamadı",
                        fontSize = 10.sp,
                        color = Color.Red
                    )
                }
            }
        }
    }
}