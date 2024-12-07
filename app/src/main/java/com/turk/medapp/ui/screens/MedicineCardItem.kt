package com.turk.medapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.turk.medapp.R
import com.turk.medapp.data.dtos.Medicine
import com.turk.medapp.ui.theme.MediumSpacer
import com.turk.medapp.ui.theme.MediumTitle
import com.turk.medapp.ui.theme.SmallTitle
import com.turk.medapp.ui.theme.mediumUnit

@Composable
fun MedicineCardItem(data: Medicine, onCLick: (Medicine) -> Unit={}) {

    Column(modifier = Modifier
        .padding(top = mediumUnit)
        .fillMaxWidth()
        .clickable {
            onCLick(data)
        }) {
        Card(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .fillMaxWidth()
        ) {
            MediumSpacer()
            MediumTitle(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = String.format(stringResource(R.string.illnessName), data.illnessName),
                color = Color.Black
            )
            SmallTitle(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = String.format(stringResource(R.string.drugName), data.name),
                color = Color.Black
            )
            SmallTitle(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = String.format(stringResource(R.string.doseName), data.dose),
                color = Color.Black
            )
            SmallTitle(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = String.format(stringResource(R.string.strengthName), data.strength),
                color = Color.Black
            )
            MediumSpacer()
        }
    }
}