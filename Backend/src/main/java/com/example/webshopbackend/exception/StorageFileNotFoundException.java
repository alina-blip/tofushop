/**
 * StorageFileNotFoundException Class
 *
 * This class represents an exception that can occur when a requested file is not found in the storage.
 * It is a runtime exception used to handle situations where a file is expected to exist but cannot be found.
 */
package com.example.webshopbackend.exception;

public class StorageFileNotFoundException extends RuntimeException {
    /**
     * Constructs a new StorageFileNotFoundException with the specified error message.
     *
     * @param message The error message explaining that the requested file was not found.
     */
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new StorageFileNotFoundException with the specified error message and a cause.
     *
     * @param message The error message explaining that the requested file was not found.
     * @param cause   The cause of the exception, which could be another exception that led to this one.
     */
    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
