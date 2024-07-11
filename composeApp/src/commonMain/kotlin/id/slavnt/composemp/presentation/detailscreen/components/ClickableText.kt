package id.slavnt.composemp.presentation.detailscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun ClickableText(text: String, onClick: () -> Unit, style: TextStyle, modifier: Modifier) {
    val annotatedText = buildAnnotatedString {
        append(text)
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline
            ),
            start = 0,
            end = text.length
        )
        addStringAnnotation(
            tag = "URL",
            annotation = text,
            start = 0,
            end = text.length
        )
    }

    BasicText(
        text = annotatedText,
        style = style,
        modifier = modifier.clickable { onClick() }
    )
}