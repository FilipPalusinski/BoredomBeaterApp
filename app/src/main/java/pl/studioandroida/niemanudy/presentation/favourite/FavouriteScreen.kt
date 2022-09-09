package pl.studioandroida.niemanudy.presentation.favourite

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.studioandroida.niemanudy.domain.model.Activity
import java.lang.Exception


@Composable
fun FavouriteScreen(
    viewModel: FavouriteViewModel = hiltViewModel()

) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            content = {padding ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    var listCounter = 0
                    items(state.activities) { activity ->
                        listCounter++
                        ActivityListItem(activity, viewModel, listCounter)
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
                            .align(Alignment.Center)
                    )
                }
                if(state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            },

                floatingActionButton = {
                    val mContext = LocalContext.current
                    FloatingActionButton(
                        onClick = {
                            Toast.makeText(mContext, "Long Press Activity to delete.", Toast.LENGTH_LONG).show()
                        },
                        content = {
                            Icon(
                                Icons.Filled.QuestionMark,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    )
                })


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActivityListItem(
    activity: pl.studioandroida.niemanudy.domain.model.Activity,
    viewModel: FavouriteViewModel,
    listCounter: Int
    ) {
    val expanded = remember { mutableStateOf(false) }
    val expandedHeight = if (expanded.value) 140.dp else 35.dp
    Column(
        modifier = Modifier
            .combinedClickable(onClick = {
                expanded.value = !expanded.value
            },
                onLongClick = {
                    viewModel.deleteActivityFromFav(activity)

                })

            .padding(20.dp)
            .height(expandedHeight)
            .fillMaxWidth()
    ) {

            Text(
                text = "$listCounter. ${activity.activity}",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(2.dp)

            )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
                text = "type: ${activity.type}",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(2.dp)
            )
            Text(
                text = "participants: ${activity.participants}",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(2.dp)
            )
          if(!activity.link.equals("")){
              Row() {
                  val uriHandler = LocalUriHandler.current
                  val context = LocalContext.current
                  Text(
                      text = AnnotatedString(activity.link),
                      style = TextStyle(
                          color = Color.Blue,
                          fontWeight = FontWeight.Bold,
                          fontSize = 16.sp,
                      ),
                      modifier = Modifier
                          .padding(2.dp)
                          .clickable(onClick = {
                              if (expanded.value) {
                                  try {
                                      uriHandler.openUri(activity.link)
                                  } catch (e: Exception) {
                                      Toast
                                          .makeText(
                                              context,
                                              "Something went wrong. Can't open this link.",
                                              Toast.LENGTH_SHORT
                                          )
                                          .show()
                                  }
                              }
                          }),
                  )
              }


        }


    }
}