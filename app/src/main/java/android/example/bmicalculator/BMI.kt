package android.example.bmicalculator

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BMI(
    val name:String,
    val bmi: Float,
    val bodyType:String
) : Parcelable
