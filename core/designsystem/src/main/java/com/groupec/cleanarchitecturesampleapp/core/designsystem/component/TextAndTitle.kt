package com.groupec.cleanarchitecturesampleapp.core.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.groupec.cleanarchitecturesampleapp.core.designsystem.theme.Blue

@Composable
fun HtmlText(
    html: String,
    linkColor: Color = Blue, // Default link color
    textColor: Color = Color.Unspecified,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    val uriHandler = LocalUriHandler.current
    val annotatedText = remember(html) {
        val spanned = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
        val text = spanned.toString()
        buildAnnotatedString {
            append(text)
            val urlSpans =
                spanned.getSpans(0, spanned.length, android.text.style.URLSpan::class.java)
            urlSpans.forEach { urlSpan ->
                val start = spanned.getSpanStart(urlSpan)
                val end = spanned.getSpanEnd(urlSpan)
                val url = urlSpan.url
                addStyle(
                    style = SpanStyle(
                        color = linkColor,
                        fontSize = fontSize,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    ), start = start, end = end
                )
                addLink(
                    url = LinkAnnotation.Url(
                        url = url,
                        linkInteractionListener = {
                            uriHandler.openUri(url)
                        }
                    ),
                    start = start,
                    end = end
                )
            }
        }
    }

    Text(
        text = annotatedText,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    )
}