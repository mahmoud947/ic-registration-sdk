package com.example.icr_core.error

open class ICRException(message: String? = null, cause: Throwable? = null) :
    Throwable(message, cause)

class DatabaseInsertException(cause: Throwable? = null) : ICRException("Failed to insert user into the database.", cause)

class UnknownInsertException(cause: Throwable? = null) : ICRException("An unknown error occurred while inserting the user.", cause)

class NoFaceDetectedException(cause: Throwable? = null) : ICRException("No face detected.", cause)

class UnknownException(cause: Throwable? = null) : ICRException("An unknown error occurred.", cause)