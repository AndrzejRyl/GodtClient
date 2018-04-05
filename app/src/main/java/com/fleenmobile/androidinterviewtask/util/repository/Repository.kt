package com.fleenmobile.androidinterviewtask.util.repository

import com.fleenmobile.androidinterviewtask.data.Recipe
import io.reactivex.Observable

interface Repository {

    fun recipes(): Observable<List<Recipe>>
}