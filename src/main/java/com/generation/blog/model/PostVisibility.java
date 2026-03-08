package com.generation.blog.model;

/**
 * PUBLIC: everyone can see; published
 * PRIVATE: only author can see (for example if it's being drafted)
 * FRIENDSONLY: only friends, collaborators, and the user can see
 * BANNED: only admins can see
 */
public enum PostVisibility {
    PUBLIC,
    PRIVATE,
    FRIENDSONLY,
    BANNED,
}
