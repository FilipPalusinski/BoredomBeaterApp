package pl.studioandroida.niemanudy.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.lang.Exception


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        state.activity?.let { result ->
            Text(
                text = "If you are bored you can:",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
            Text(
                text = result.activity,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(50.dp, 20.dp, 50.dp, 50.dp)
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
                    color = Color.Blue,
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

            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Star,"") },
                text = { Text("Add to Favourites") },
                onClick = { viewModel.addActivityToFav() },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            )
            Spacer(modifier = Modifier.height(90.dp))
            Button(
                onClick = { viewModel.getActivity() }
            ){
                Text(text = "Next Activity",fontSize = 16.sp,)
            }

        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)

            )
        }
        if(state.isLoading) { CircularProgressIndicator() }





    }
}
