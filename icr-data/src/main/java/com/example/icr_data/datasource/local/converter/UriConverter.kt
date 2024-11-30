package com.example.icr_data.datasource.local.converter

import android.net.Uri
import androidx.room.TypeConverter

class UriConverter {

    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return if (uriString == null) null else Uri.parse(uriString)
    }
}
