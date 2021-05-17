package com.example.feeddemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feeddemo.data.api.ApiHelper
import com.example.feeddemo.data.db.DatabaseHelper
import com.example.feeddemo.data.model.FeedModel
import com.example.feeddemo.utils.Resource
import kotlinx.coroutines.launch

class NewsFeedViewModel(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModel() {

    private val rows = MutableLiveData<Resource<List<FeedModel.Row>>>()

    init {
        fetchAllRows()
    }

    private fun fetchAllRows() {
        viewModelScope.launch {
            rows.postValue(Resource.loading(null))
            try {
                val rowsFromDb = dbHelper.getAllRows()
                if (rowsFromDb.isEmpty()) {
                    val rowsFromApi = apiHelper.getAllNewsFeedData()
                    val rowsToInsertInDB = mutableListOf<FeedModel.Row>()

                    for (apiRows in rowsFromApi.rows!!) {
                        val feedRow = FeedModel.Row(
                            apiRows.id,
                            apiRows.title,
                            apiRows.description,
                            apiRows.imageHref
                        )
                        rowsToInsertInDB.add(feedRow)
                    }

                    dbHelper.insertAllRows(rowsToInsertInDB)

                    rows.postValue(Resource.success(rowsToInsertInDB))

                } else {
                    rows.postValue(Resource.success(rowsFromDb))
                }


            } catch (e: Exception) {
                e.printStackTrace()
                rows.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getAllRows(): LiveData<Resource<List<FeedModel.Row>>> {
        return rows
    }

    fun refreshList(){
        viewModelScope.launch {
            dbHelper.clearAllRows()
            fetchAllRows()
        }
    }

}