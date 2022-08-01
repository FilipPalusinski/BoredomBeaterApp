package pl.studioandroida.niemanudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.studioandroida.niemanudy.data.remote.ActivityDto
import pl.studioandroida.niemanudy.ui.theme.NieMaNudyTheme
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NieMaNudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { } ,
                        //floatingActionButtonPosition = FabPosition.End,
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {}
                            ) {
                                Icon(Icons.Filled.Star,"")
                            }
                        }
                        , content = {
                        },
                        bottomBar = {BottomBar()})
                }
            }
        }
    }
}



@Composable
fun Activity(result: ActivityDto) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        Text(
            text = "If you are bored you can:",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )
        Text(
            text = result.activity,
                fontSize = 17.sp
        )
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Activity type: ${result.type}",
            fontSize = 22.sp,
        )



        Spacer(modifier = Modifier.height(8.dp))
        lateinit var text: String
        if(result.participants>1){
            text = "You will need ${result.participants} participants"
        } else{
            text = "You can do it alone"
        }
        Text(
        text = text,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        val uriHandler = LocalUriHandler.current
        val context = LocalContext.current
        ClickableText(
            text = AnnotatedString(result.link),
            style = TextStyle(
                color = Blue,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
            onClick = {
                try {
                    uriHandler.openUri(result.link)
                }
                catch (e: Exception){
                    Toast.makeText(
                        context,
                        "Something went wrong. Can't open this link.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
        Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = { /*TODO*/ }
                ){
                Text(text = "Not Today",fontSize = 16.sp,)
            }



    }
}

@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Star,"")
        },
            label = { Text(text = "Favorite") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })


    }
}

