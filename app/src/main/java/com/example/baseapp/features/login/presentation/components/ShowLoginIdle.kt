package com.example.baseapp.features.login.presentation.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baseapp.R
import com.example.baseapp.core.utils.LoadFontsFamily
import com.example.baseapp.features.login.presentation.LoginViewModel

@Composable
fun ShowLoginIdle(
    gradient: Brush,
    username: String,
    viewModel: LoginViewModel,
    focusRequester: FocusRequester,
    password: String,
    keyboardController: SoftwareKeyboardController?,
    showPassword: MutableState<Boolean>,
    isPasswordEmpty: Boolean,
    isFormValid: Boolean,
    context: Context
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.login),
                    fontSize = with(LocalDensity.current) {
                        dimensionResource(id = R.dimen.title_font_text_size).value.sp
                    },
                    fontFamily = LoadFontsFamily.karlaFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontStyle = FontStyle.Normal
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.minium_space_betwing_elements)))
                TextField(
                    value = username,
                    onValueChange = {
                        viewModel.username.value = it
                    },
                    label = { Text(stringResource(R.string.email)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequester.requestFocus()
                        }
                    ),
                    textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    shape = RoundedCornerShape(25),
                    modifier = Modifier
                        .padding(20.dp, 20.dp, 20.dp, 1.dp)
                        .border(
                            dimensionResource(R.dimen.textfield_border_size),
                            MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(dimensionResource(R.dimen.textfield_rounded_corner_shape))
                        )
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.textfield_rounded_corner_shape)))
                        .focusRequester(focusRequester),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.email_email_com),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            maxLines = 1
                        )
                    },
                    maxLines = 1
                )
                TextField(
                    value = password,
                    onValueChange = {
                        viewModel.password.value = it
                    },
                    label = { Text(stringResource(R.string.password)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    visualTransformation = if (showPassword.value) VisualTransformation.None else
                        PasswordVisualTransformation(),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.None
                    ),
                    shape = RoundedCornerShape(25),
                    modifier = Modifier
                        .padding(20.dp, 6.dp, 20.dp, 20.dp)
                        .border(
                            dimensionResource(R.dimen.textfield_border_size),
                            MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .focusRequester(focusRequester),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.q_nm_44u),
                            fontSize = 16.sp,
                            fontFamily = LoadFontsFamily.montserratFamily,
                            fontWeight = FontWeight.Normal,
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                            ),
                            maxLines = 1
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = stringResource(R.string.password_icon),
                            tint = if (isPasswordEmpty) Color.Gray else Color.Green
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { showPassword.value = !showPassword.value }) {
                            val icon: ImageVector = if (showPassword.value) {
                                ImageVector.vectorResource(id = R.drawable.baseline_remove_red_eye_24)
                            } else {
                                ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24)
                            }
                            Icon(
                                imageVector = icon,
                                contentDescription = if (showPassword.value) stringResource(id = R.string.hide_password) else
                                    stringResource(id = R.string.show_password),
                                tint = if (showPassword.value) Color.Blue else Color.Gray
                            )
                        }
                    },
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        viewModel.performLogin(username, password)
                    },
                    enabled = isFormValid,
                    interactionSource = remember { MutableInteractionSource() },
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_width_medium))
                        .padding(dimensionResource(R.dimen.button_padding))
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape)))
                        .background(
                            brush = if (!isFormValid) Brush.verticalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    MaterialTheme.colorScheme.surfaceVariant
                                )
                            ) else gradient
                        )
                        .border(
                            BorderStroke(
                                width = dimensionResource(R.dimen.button_border_size),
                                color = if (!isFormValid) MaterialTheme.colorScheme.outlineVariant else
                                    MaterialTheme.colorScheme.onPrimary
                            ),
                            shape = RoundedCornerShape(dimensionResource(R.dimen.button_rounded_corner_shape))
                        ),
                    contentPadding = ButtonDefaults.ContentPadding
                ) {
                    Text(
                        text = context.getString(R.string.login),
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        color = if (!isFormValid) MaterialTheme.colorScheme.outline else
                            MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}