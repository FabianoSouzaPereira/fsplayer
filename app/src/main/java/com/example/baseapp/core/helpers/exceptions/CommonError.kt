/** Using object inside a sealed class ensures that the represented states are immutable and unique,
 *  which helps avoid inconsistencies and simplifies state management.
 *  1- Resource Savings: Since each error is represented by a unique instance (object), you don't create multiple unnecessary
 *     instances, saving memory.
 *  2- Ease of Use: Accessing errors like LoginPresenterError.UserNotFound is simple and clear, making the code more readable
 *     and easy to understand.
 *  3- Uniqueness Guarantee: Each error is guaranteed to be unique, which is ideal for fixed states like predefined errors.
 */

package com.example.baseapp.core.helpers.exceptions

open class CommonError(val message: String) {
    object TimeoutError : CommonError("Request timed out. Please check your internet connection.")
    object Unauthorized : CommonError("Unauthorized access. Please check your credentials.")
    object ValidationError : CommonError("Validation failed. Please check the entered data.")
    object UnknownError : CommonError("Unknown error.")
}