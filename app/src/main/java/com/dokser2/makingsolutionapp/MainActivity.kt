package com.dokser2.makingsolutionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dokser2.makingsolutionapp.ui.theme.MyColor
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakingSolution()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MakingSolution() {
    var first_solution_concentration by remember() {
        mutableStateOf("")
    }
    var second_solution_concentration by remember() {
        mutableStateOf("")
    }
    var findConcentration by remember() {
        mutableStateOf("")
    }
    var findVolume by remember() {
        mutableStateOf("")
    }

    var resultString by remember() {
        mutableStateOf("")
    }

    Image(
        painter = painterResource(R.drawable.background_img),
        contentDescription = "img",
        modifier = Modifier
            .fillMaxSize()
            .alpha(1.0f),
        contentScale = ContentScale.FillBounds

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top


        ) {
        Text(
            text = "ЯК ПРИГОТУВАТИ ПОТРІБНИЙ РОЗЧИН?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            style = TextStyle(Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Text(
            text = "(в розрахунках  густина розчинів НЕ ВРАХОВУЄТЬСЯ!!!)",
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            style = TextStyle(Color.Red, fontSize = 10.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Text(
            text = resultString,
            modifier = Modifier.background(Color.Yellow),
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Blue, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {

                TextField(
                    value = first_solution_concentration,
                    onValueChange = { first_solution_concentration = it },
                    label = {
                        Text(
                            "концентрація\nI р-ну в %",
                            style = TextStyle(color = Color.Blue, fontSize = 12.sp),
                            textAlign = TextAlign.Center, maxLines = 2, fontWeight = FontWeight.Bold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(vertical = 5.dp)
                        .alpha(0.8f)
                        .border(width = 1.dp, Color.Blue, shape = RectangleShape)

                )
                TextField(
                    value = second_solution_concentration,
                    onValueChange = { second_solution_concentration = it },
                    label = {
                        Text(
                            "концентрація\nII р-ну в %",
                            style = TextStyle(color = Color.Blue, fontSize = 12.sp),
                            textAlign = TextAlign.Center, maxLines = 2, fontWeight = FontWeight.Bold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .alpha(0.8f)
                        .border(width = 1.dp, Color.Blue, shape = RectangleShape)
                )

            }

            TextField(
                value = findConcentration,
                onValueChange = { findConcentration = it },
                label = {
                    Text(
                        "ПОТРІБНА концентрація  в %",
                        style = TextStyle(color = Color.Blue, fontSize = 12.sp),
                        textAlign = TextAlign.Center, maxLines = 1, fontWeight = FontWeight.Bold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .alpha(0.8f)
                    .border(width = 1.dp, Color.Blue, shape = RectangleShape)
            )
            TextField(
                value = findVolume,
                onValueChange = { findVolume = it },
                label = {
                    Text(
                        "ПОТРІБНИЙ об'єм в мл",
                        style = TextStyle(color = Color.Blue, fontSize = 12.sp),
                        textAlign = TextAlign.Center, maxLines = 1, fontWeight = FontWeight.Bold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .alpha(0.8f)
                    .border(width = 1.dp, Color.Blue, shape = RectangleShape)
            )

            Button(
                onClick = {
                    resultString = if (
                        first_solution_concentration.isEmpty() ||
                        second_solution_concentration.isEmpty() ||
                        findConcentration.isEmpty() ||
                        findVolume.isEmpty()
                    )
                        "Заповніть усі поля!!!"
                    else if (first_solution_concentration.toFloat() > findConcentration.toFloat() &&
                        second_solution_concentration.toFloat() > findConcentration.toFloat()
                    )

                        "концентрація хоча б одного з розчинів має бути \n НИЖЧЕ за $findConcentration %!!! "
                    else if (first_solution_concentration.toFloat() < findConcentration.toFloat() &&
                        second_solution_concentration.toFloat() < findConcentration.toFloat()
                    )

                        "концентрація хоча б одного з розчинів має бути \n ВИЩЕ за $findConcentration %!!! "
                    else
                        getResult(
                            first_solution_concentration,
                            second_solution_concentration,
                            findConcentration,
                            findVolume
                        )

                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(text = "РОЗРАХУВАТИ")

            }

            Button(
                onClick = {
                    resultString = ""
                    findConcentration = ""
                    first_solution_concentration = ""
                    second_solution_concentration = ""
                    findVolume = ""

                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(text = "ОЧИСТИТИ")

            }
        }

    }
}

fun getResult(
    f_conc: String,
    s_conc: String,
    conc: String,
    volume: String,
): String {

    val f_conc_fl = f_conc.toFloat()
    val s_conc_fl = s_conc.toFloat()
    val conc_fl = conc.toFloat()
    val volume_fl = volume.toFloat()

    if ((f_conc_fl <= conc_fl && s_conc_fl >= conc_fl) ||
        (s_conc_fl <= conc_fl && f_conc_fl >= conc_fl)
    ) {
        val f_dif = abs(f_conc_fl - conc_fl)
        val s_dif = abs(s_conc_fl - conc_fl)
        var f_vol = 0.0
        var s_vol = 0.0

        if (f_conc_fl > s_conc_fl) {
            f_vol = (s_dif * volume_fl * 10 / (f_dif + s_dif)).toInt() / 10.0
            s_vol = volume_fl - f_vol
        } else {
            s_vol = (f_dif * volume_fl * 10 / (f_dif + s_dif)).toInt() / 10.0
            f_vol = volume_fl - s_vol
        }


        return " Для отримання $volume мл. $conc % розчину" +
                " змішайте  $s_vol мл. " +
                " $s_conc % розчину " +
                " та  $f_vol мл. " +
                "$f_conc  % розчину"
    } else
        return ""
}
