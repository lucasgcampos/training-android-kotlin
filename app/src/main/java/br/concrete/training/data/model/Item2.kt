package br.concrete.training.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by eliete on 2/15/18.
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Item2(var task : String, var description: String) : Parcelable {

}