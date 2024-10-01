package com.example.nfccard.ui.theme

//import androidx.compose.foundation.shape.RoundedCornerShape
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.Shapes
//import androidx.compose.ui.unit.dp
//
//val Shapes = Shapes(
//    small = RoundedCornerShape(8.dp),
//    medium = RoundedCornerShape(12.dp),
//    large = RoundedCornerShape(16.dp)
//)
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Определяем формы для Material 3
val Shapes = Shapes(
    small = RoundedCornerShape(4.dp), // Маленькие элементы (например, кнопки)
    medium = RoundedCornerShape(8.dp), // Средние элементы (например, карточки)
    large = RoundedCornerShape(16.dp) // Крупные элементы (например, диалоговые окна)
)