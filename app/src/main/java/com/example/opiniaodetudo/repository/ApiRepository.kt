package com.example.opiniaodetudo.repository

import com.example.opiniaodetudo.model.Review
import java.util.*

class ReviewRepository{

    private constructor()

    companion object {
        val instance : ReviewRepository = ReviewRepository()
    }

    private val reviewDao = mutableListOf<Review>()
    fun save(name: String, review: String) {
        reviewDao.add(Review(UUID.randomUUID().toString(), name, review))
    }

    fun listAll(): List<Review> {
        return reviewDao.toList()
    }


}