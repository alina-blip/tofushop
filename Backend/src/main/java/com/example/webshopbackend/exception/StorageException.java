/**
 * StorageException Class
 *
 * This class represents an exception that can occur during storage-related operations, such as file handling or storage initialization.
 * It is a runtime exception used to handle issues related to storage in the application.
 */
package com.example.webshopbackend.exception;

public class StorageException extends RuntimeException {
    /**
     * Constructs a new StorageException with the specified error message.
     *
     * @param message The error message explaining the storage-related issue.
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * Constructs a new StorageException with the specified error message and a cause.
     *
     * @param message The error message explaining the storage-related issue.
     * @param cause   The cause of the exception, which could be another exception that led to this one.
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
