package com.example.kishath.core.network.exceptions

class BadConnectionException(
    messageResId: Int? = com.example.kishath.core.R.string.you_are_offline,
    descriptionResId: Int? = com.example.kishath.core.R.string.no_internet_connection_description
) : AppException()