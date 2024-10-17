package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Searchbar(
    // searchText: String,
     // onTextChange: (String) -> Unit
){

    OutlinedTextField(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(44.dp),
        value =  "" ,// searchText
        onValueChange = {
            // onTextChange(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
            )
        },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close icon",
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.DarkGray.copy(
                alpha = ContentAlpha.medium
            ),
            textColor = Color.DarkGray,
            placeholderColor = Color.White.copy(
                alpha = ContentAlpha.medium
            ),
            unfocusedBorderColor = Color.Black
        ),
        shape = RoundedCornerShape(100.dp),
        placeholder = {
            Text(
                text = "Search..",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp),
                style = LocalTextStyle.current.copy(baselineShift = BaselineShift(0.7f))
            )
        },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = 12.sp),
    )
}