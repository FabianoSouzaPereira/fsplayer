package com.example.baseapp.features.login.presentation

import com.example.baseapp.core.helpers.exceptions.CommonError


/** Using object inside a sealed class ensures that the represented states are immutable and unique,
 *  which helps avoid inconsistencies and simplifies state management.
 *  1- Resource Savings: Since each error is represented by a unique instance (object), you don't create multiple unnecessary
 *     instances, saving memory.
 *  2- Ease of Use: Accessing errors like LoginPresenterError.UserNotFound is simple and clear, making the code more readable
 *     and easy to understand.
 *  3- Uniqueness Guarantee: Each error is guaranteed to be unique, which is ideal for fixed states like predefined errors.
 */

sealed class LoginError(message: String) : CommonError(message) {
    object UserNotFound : LoginError("User not found.")
    object LoginFailed : LoginError("Login failed. Please try again.")
}
