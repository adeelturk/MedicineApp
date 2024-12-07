package com.turk.medapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.turk.medapp.R
import com.turk.medapp.data.dtos.Medicine
import com.turk.medapp.ui.theme.SmallHeading
import com.turk.medapp.ui.theme.mediumUnit
import com.turk.medapp.ui.theme.smallUnit

@Composable
fun MedicineCardDetail(medicine: Medicine?, goBack: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = mediumUnit, vertical = smallUnit),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(color = Color.Transparent)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
                    .padding(top = smallUnit)
            ) {
                IconButton(onClick = {
                    goBack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                SmallHeading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    text = stringResource(R.string.detail_title)
                )

            }
            medicine?.run {
                MedicineCardItem(medicine)
            }

        }
    }

}