package com.example.opiniaodetudo.repository

import android.content.Context
import com.example.opiniaodetudo.ReviewDatabase
import com.example.opiniaodetudo.infra.dao.ReviewDao
import com.example.opiniaodetudo.model.Review
import java.util.*

class ReviewRepository {

    private val reviewDao: ReviewDao
    constructor(context: Context){
        val reviewDatabase = ReviewDatabase.getInstance(context)
        //dando erro nesta linha, verificar dps
        reviewDao = reviewDatabase.reviewDao()
    }
    fun save(name: String, review: String) {
        reviewDao.save(Review(UUID.randomUUID().toString(), name, review))
    }
    fun listAll(): List<Review> {
        return reviewDao.listAll()
    }

}