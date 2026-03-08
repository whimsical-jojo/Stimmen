package com.generation.blog.model;

/**
 * Active for a normally functioning account
 * Pending for when the email hasn't been confirmed
 * Banned for when the user has been banned, duh
 */
public enum UserStatus {
    ACTIVE,
    PENDING, 
    BANNED
}
