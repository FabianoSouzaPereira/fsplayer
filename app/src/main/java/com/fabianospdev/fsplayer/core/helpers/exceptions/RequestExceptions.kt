package com.fabianospdev.fsplayer.core.helpers.exceptions

/**
 * Base exception for all request errors.
 * This class serves as the parent for all exceptions related to request failures.
 */
open class RequestException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

/**
 * A sealed class representing general request errors.
 * This is a base class for all specific types of general request exceptions.
 */
sealed class GeneralRequestException(message: String? = null, cause: Throwable? = null) : RequestException(message, cause)

/**
 * Exception representing network-related errors.
 * This exception is thrown when there is a network error during a request.
 */
open class NetworkException(message: String? = "Network error", cause: Throwable? = null) : GeneralRequestException(message, cause)

/**
 * Exception thrown when a request times out.
 * This exception is triggered when the request exceeds the allowed time limit.
 */
open class TimeoutException(message: String? = "Request timed out", cause: Throwable? = null) : NetworkException(message, cause)

/**
 * Exception thrown when there is a server error.
 * This exception indicates that the server encountered an issue while processing the request.
 */
open class ServerException(message: String? = "Server error", cause: Throwable? = null) : GeneralRequestException(message, cause)

/**
 * Exception thrown when a request is unauthorized.
 * This exception indicates that the user does not have the necessary permissions to access the resource.
 */
open class UnauthorizedException(message: String? = "Unauthorized", cause: Throwable? = null) : GeneralRequestException(message, cause)

/**
 * Exception thrown when the request is malformed or incorrect.
 * This exception is raised when the client sends a request that the server cannot understand or process.
 */
open class BadRequestException(message: String? = "Bad request", cause: Throwable? = null) : GeneralRequestException(message, cause)

/**
 * Exception thrown when access to a resource is forbidden.
 * This exception indicates that the user is not allowed to access the requested resource.
 */
open class ForbiddenException(message: String? = "Forbidden access", cause: Throwable? = null) : GeneralRequestException(message, cause)

/**
 * Exception thrown when there is a conflict in the data.
 * This exception indicates that the request cannot be completed due to a data conflict, such as attempting to create a duplicate resource.
 */
open class ConflictException(message: String? = "Data conflict", cause: Throwable? = null) : GeneralRequestException(message, cause)

/**
 * A sealed class representing exceptions related to user actions.
 * This is a base class for all user-related exceptions.
 */
sealed class UserException(message: String? = null, cause: Throwable? = null) : RequestException(message, cause)

/**
 * Exception thrown when a user is not found.
 * This exception is raised when an attempt is made to access a user that does not exist.
 */
open class UserNotFoundException(message: String? = "User not found", cause: Throwable? = null) : UserException(message, cause)

/**
 * Exception thrown when an email address is already in use.
 * This exception is triggered when a user tries to register with an email that is already associated with another account.
 */
open class EmailAlreadyInUseException(message: String? = "Email already in use", cause: Throwable? = null) : UserException(message, cause)

/**
 * Exception thrown when a user's password is considered too weak.
 * This exception occurs when the password does not meet the required security criteria.
 */
open class PasswordTooWeakException(message: String? = "Password is too weak", cause: Throwable? = null) : UserException(message, cause)

/**
 * Exception thrown when the authentication token is invalid.
 * This exception is raised when an expired or invalid authentication token is used.
 */
open class InvalidTokenException(message: String? = "Invalid authentication token", cause: Throwable? = null) : UserException(message, cause)

/**
 * Exception thrown when there is a validation failure.
 * This exception indicates that the data provided by the user does not pass the validation checks.
 */
open class ValidationException(message: String? = "Validation failed", cause: Throwable? = null) : UserException(message, cause)

/**
 * Exception thrown when the user's account is locked.
 * This exception occurs when a user tries to access their account but it has been locked due to security reasons or policy violations.
 */
open class AccountLockedException(message: String? = "Account locked", cause: Throwable? = null) : UserException(message, cause)

/**
 * Exception thrown when the user's session has expired.
 * This exception is raised when the session expires due to inactivity or other reasons.
 */
open class SessionExpiredException(message: String? = "Session expired", cause: Throwable? = null) : UserException(message, cause)

/**
 * A sealed class representing exceptions related to transactions or resource access.
 * This is a base class for all exceptions that occur during transactions or while accessing resources.
 */
sealed class TransactionException(message: String? = null, cause: Throwable? = null) : RequestException(message, cause)

/**
 * Exception thrown when there are insufficient funds to complete a transaction.
 * This exception occurs when a user attempts to make a purchase or transaction but lacks the necessary funds.
 */
open class InsufficientFundsException(message: String? = "Insufficient funds", cause: Throwable? = null) : TransactionException(message, cause)

/**
 * Exception thrown when an item is not found.
 * This exception is raised when the requested item is not available in the system or database.
 */
open class ItemNotFoundException(message: String? = "Item not found", cause: Throwable? = null) : TransactionException(message, cause)

/**
 * Exception thrown when the user does not have permission to perform the requested transaction.
 * This exception indicates that the user lacks the necessary authorization for the operation.
 */
open class PermissionDeniedException(message: String? = "Permission denied", cause: Throwable? = null) : TransactionException(message, cause)

/**
 * Exception thrown when the maximum number of attempts has been exceeded.
 * This exception occurs when a user or system exceeds the allowed number of attempts for a specific action (e.g., login retries).
 */
open class MaxAttemptsExceededException(message: String? = "Maximum number of attempts exceeded", cause: Throwable? = null) : TransactionException(message, cause)
