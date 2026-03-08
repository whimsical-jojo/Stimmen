package com.generation.blog.entities;

/**
 * PUBLIC: everyone can see; published
 * PRIVATE: only author can see (for example if it's being drafted)
 * FRIENDSONLY: only friends and the user can see
 * BANNED: only admins can see
 */
public enum PostVisibility {
    PUBLIC,
    PRIVATE,
    FRIENDSONLY,
    BANNED,
}
