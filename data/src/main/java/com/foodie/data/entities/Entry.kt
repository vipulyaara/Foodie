package com.foodie.data.entities

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Entry is an entity used within a list. It can also be used with paginated lists.
 */
interface Entry : BaseEntity

interface PaginatedEntry : Entry {
    val page: Int
}
