package com.turk.medapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.turk.medapp.R
import com.turk.medapp.data.dtos.Medicine
import com.turk.medapp.ui.main.MainViewModel
import com.turk.medapp.ui.theme.MediumHeading
import com.turk.medapp.ui.theme.MediumSpacer
import com.turk.medapp.ui.theme.MediumTitle
import com.turk.medapp.ui.theme.MyApplicationTheme
import com.turk.medapp.ui.theme.SmallHeading
import com.turk.medapp.ui.theme.mediumUnit
import com.turk.medapp.ui.theme.smallUnit

@Composable
fun MedicineListScreen(
    mainViewModel: MainViewModel,
    openDetailPageForSelectedScreen: (Medicine) -> Unit,
    goBack:()->Unit
) {
    val greetTime= stringResource(R.string.g_morning)
    val medicineList=mainViewModel.medicinesList.collectAsState()
    val showDialog = mainViewModel.progressLoading.collectAsState()

    ProgressDialog(showDialog = showDialog.value) {

        mainViewModel.showLoading(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize().background(color=Color.White)
            .padding(horizontal = mediumUnit, vertical = smallUnit),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(color = Color.Transparent)
        ) {


            Row (modifier= Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(top = smallUnit)){
                IconButton(onClick = {
                    goBack()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                SmallHeading( modifier= Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    text = String.format(stringResource(R.string.greetingMessage),mainViewModel.userName,greetTime))

            }

            MediumSpacer()

            MedicineList(medicineList.value) {
                openDetailPageForSelectedScreen(it)
            }
        }


    }

    LaunchedEffect(key1 = mainViewModel) {
        mainViewModel.fetchMedicinesListUseCase()
    }

}


@Composable
fun MedicineList(list:List<Medicine>,getHotelDetails:(Medicine)->Unit) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        items(list) {
            MedicineCardItem(it){
                getHotelDetails(it)
            }
        }

    }
}

@Composable
@Preview
fun PreviewMedicineListScreen() {
    MyApplicationTheme {
        MedicineListScreen(hiltViewModel(),{},{})
    }

}