package com.example.android.composeuidemos.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.composeuidemos.meditation_ui.BottomMenuContent
import com.example.android.composeuidemos.meditation_ui.FeaturedItem
import com.example.android.composeuidemos.R
import com.example.android.composeuidemos.standardQuadFromTo
import com.example.android.composeuidemos.ui.theme.AquaBlue
import com.example.android.composeuidemos.ui.theme.Beige1
import com.example.android.composeuidemos.ui.theme.Beige2
import com.example.android.composeuidemos.ui.theme.Beige3
import com.example.android.composeuidemos.ui.theme.BlueViolet1
import com.example.android.composeuidemos.ui.theme.BlueViolet2
import com.example.android.composeuidemos.ui.theme.BlueViolet3
import com.example.android.composeuidemos.ui.theme.ButtonBlue
import com.example.android.composeuidemos.ui.theme.DarkerButtonBlue
import com.example.android.composeuidemos.ui.theme.DeepBlue
import com.example.android.composeuidemos.ui.theme.LightGreen1
import com.example.android.composeuidemos.ui.theme.LightGreen2
import com.example.android.composeuidemos.ui.theme.LightGreen3
import com.example.android.composeuidemos.ui.theme.LightRed
import com.example.android.composeuidemos.ui.theme.OrangeYellow1
import com.example.android.composeuidemos.ui.theme.OrangeYellow2
import com.example.android.composeuidemos.ui.theme.OrangeYellow3
import com.example.android.composeuidemos.ui.theme.TextWhite


// the whole homeScreen

fun listOfFeatures() = listOf(
    FeaturedItem(
        title = "Sleep meditation",
        R.drawable.ic_headphone,
        BlueViolet1,
        BlueViolet2,
        BlueViolet3
    ),
    FeaturedItem(
        title = "Tips for sleeping",
        R.drawable.ic_videocam,
        LightGreen1,
        LightGreen2,
        LightGreen3
    ),
    FeaturedItem(
        title = "Night island",
        R.drawable.ic_headphone,
        OrangeYellow1,
        OrangeYellow2,
        OrangeYellow3
    ),
    FeaturedItem(
        title = "Calming sounds",
        R.drawable.ic_headphone,
        Beige1,
        Beige2,
        Beige3
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection(name = "Jorge")
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(listOfFeatures())
        }
        // the bottomNavigation
        BottomMenu(
            bottomItems = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Meditate", R.drawable.ic_bubble),
                BottomMenuContent("Sleep", R.drawable.ic_moon),
                BottomMenuContent("Music", R.drawable.ic_music),
                BottomMenuContent("Profile", R.drawable.ic_profile),
            ),
            modifier = Modifier.align(Alignment.BottomCenter) // thanks to this it goes to the bottom
        )
    }
}


@Composable
fun GreetingSection(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Good Morning $name",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.body1
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
fun ChipSection(chips: List<String>) {
    var selectedChipIndex by remember {
        mutableStateOf(-1)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                // does not take size of box because it takes the needed size for the text
                // the last padding pushes the content into the box which simulates the size
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp) // this padding pushes content inside
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp) // the padding from the whole screen
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp) // the one that pushes the content inside
            .fillMaxWidth()
    ) {

        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Meditation - 3-10 min",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        // the circle shape that holds our button
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<FeaturedItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                //DummyFeaturedSection(feature = features[it])
                FeaturedItemComposable(feature = features[it])
            }
        }
    }
}


@Composable
fun FeaturedItemComposable(feature: FeaturedItem) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp) // the one that gives separation between items
            .aspectRatio(1f) // form of square
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor) // at last the background with the shape clipped
    ) {

        // waves logic section
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f) // from start to 30% of height
        val mediumColoredPoint2 =
            Offset(width * 0.1f, height * 0.35f)// from 0.1 width to 0.35 of height
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())


        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // end of waves logic section

        // needed when we want to draw something
        Canvas(modifier = Modifier.fillMaxSize()) {
            // this part are the waves that are in the boxes
            drawPath(path = mediumColoredPath, feature.mediumColor)
            drawPath(path = lightColoredPath, feature.lightColor)
        }
        // this box is inside the fillMaxSize of the canvas
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart) // the topStart of the box
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart) // the left start of the box
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

// this is the one that I made real quick to practice a little bit without the waves
//https://stackoverflow.com/questions/62939473/how-to-add-margin-in-jetpack-compose
@Composable
fun DummyFeaturedSection(feature: FeaturedItem) {
    // the box is our holder of all the items

    // AS SAW IN THE TUTORIAL THE COMPOSABLES APPLY THE MODIFIERS AND THE COMPONENTS SEQUENTIALLY
    // SO BASICALLY WILL BE APPLIED AS DECLARED
    Box(
        modifier = Modifier
            .padding(8.dp) // IT'S VERY IMPORTANT TO ADD THE PADDING FIRST IF
            // THIS GIVES THE "MARGIN" IF THE BACKGROUND IS APPLIED FIRST THEN THE SPACE GIVEN
            //WILL STILL CONTAIN THE COLOR AND THE SIMULATION OR MARGIN WILL BE LOST
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)// THE BACKGROUND AT LAST IN ORDER TO APPLY IT TO THE CONTENT ALREADY CLIPPED
            .fillMaxWidth()

    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .height(200.dp)
                // .aspectRatio(1f) // probably better than to use height() means that is a square
                .padding(16.dp)
        ) {
            Text(text = feature.title)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(painter = painterResource(feature.iconId), contentDescription = "Icon")
                Button(onClick = { }) {
                    Text(text = "Start")
                }
            }
        }
    }
}

@Composable
fun BottomMenu(
    bottomItems: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        bottomItems.forEachIndexed { index, bottomMenuContent ->
            // display the composable inside this row
            BottomMenuItem(
                item = bottomMenuContent,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    // The whole column will react to the on click 
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onItemClick.invoke()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp) // pushes the content inside
        ) {
            // will be inside the box with its background
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        // below the icon and box shadow
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }

}

