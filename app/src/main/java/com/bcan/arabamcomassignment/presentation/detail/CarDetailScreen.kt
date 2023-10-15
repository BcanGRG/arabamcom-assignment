package com.bcan.arabamcomassignment.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bcan.arabamcomassignment.R
import com.bcan.arabamcomassignment.presentation.detail.components.CarImagePager
import com.bcan.arabamcomassignment.presentation.ui.ArabamContent
import com.bcan.arabamcomassignment.presentation.ui.LoadingIndicator
import com.bcan.arabamcomassignment.presentation.ui.common.LocationWithIcon
import com.bcan.arabamcomassignment.presentation.util.formatImageResolution

@Composable
fun CarDetailScreen(
    navController: NavHostController,
    carId: Int?,
    viewModel: CarDetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ArabamContent(
        title = "İlan Detayı",
        startIcon = R.drawable.ic_back,
        startIconClick = {
            navController.popBackStack()
        }
    ) {

        carId?.let {
            LaunchedEffect(key1 = it) {
                viewModel.getCarDetail(it)
            }
        }

        uiState.errorMessage?.let { message ->
            LaunchedEffect(message) {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.errorMessageShown()
            }
        }

        if (uiState.isLoading) {
            LoadingIndicator()
        }

        val detail = uiState.detail
        val photos = detail?.photos
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )
        {
            photos?.let {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape
                ) {
                    CarImagePager(
                        modifier = Modifier.wrapContentSize(),
                        itemsCount = photos.size
                    ) { index ->
                        AsyncImage(
                            model = photos[index].formatImageResolution(),
                            contentDescription = "",
                            modifier = Modifier.height(250.dp),
                            contentScale = ContentScale.Fit,
                            placeholder = painterResource(id = R.drawable.loading_car_placeholder),
                            error = painterResource(id = R.drawable.error_car_image)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val category = detail?.category?.name?.replace("/", "  >  ") ?: "Kategori yok"
                val date = detail?.dateFormatted ?: "Tarih Bulunamadı"
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = date,
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 8.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold
                    ),
                    textAlign = TextAlign.End
                )

                Text(
                    text = category,
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = detail?.title ?: "Veri bulunamadı",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LocationWithIcon(
                        cityName = detail?.location?.cityName,
                        townName = detail?.location?.townName
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = detail?.priceFormatted ?: "Fiyat bulunamadı",
                        textAlign = TextAlign.End,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = detail?.userInfo?.nameSurname ?: "İsim bulunamadı")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CallEnd,
                            contentDescription = "phone icon",
                            modifier = Modifier
                                .size(15.dp)
                        )
                        Text(text = detail?.userInfo?.phoneFormatted ?: "Telefon bulunamadı")
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 4.dp,
                    backgroundColor = Color.LightGray
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Araç Bilgileri",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            ),
                            textAlign = TextAlign.Center
                        )
                        detail?.properties.let {
                            it?.forEach { property ->
                                if (!property.value.isNullOrEmpty()) {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(text = property.name!!)
                                        Text(
                                            text = property.value!!,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.End
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


