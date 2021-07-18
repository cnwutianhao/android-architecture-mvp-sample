package com.tyhoo.mvp.kotlin.ui

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.common.base.Strings
import com.google.gson.Gson
import com.tyhoo.mvp.kotlin.data.User

class MainPresenter(
    private val context: Context,
    private val mainView: MainContract.View
) : MainContract.Presenter {

    private var jsonResponse: String? = null

    init {
        mainView.presenter = this
    }

    override fun start() {
        networkRequest()
    }

    private fun networkRequest() {
        mainView.setLoadingIndicator(true)

        val queue = Volley.newRequestQueue(context)
        val url = "https://api.github.com/users/cnwutianhao"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response: String ->
                Log.d(TAG, "networkRequest: success")
                jsonResponse = response
                mainView.setLoadingIndicator(false)
                showUser(response)
            }) { error: VolleyError? ->
            Log.d(TAG, "networkRequest: ${error?.message}")
            mainView.setLoadingIndicator(false)
            showUser("")
        }

        queue.add(stringRequest)
    }

    private fun showUser(result: String) {
        if (Strings.isNullOrEmpty(result)) {
            mainView.hideUser()
        } else {
            mainView.showUser(result)
        }
    }

    override fun parseJson() {
        if (Strings.isNullOrEmpty(jsonResponse)) {
            return
        }

        val gson = Gson()
        val user = gson.fromJson(jsonResponse, User::class.java)
        mainView.showParseJson(user)
    }

    companion object {
        const val TAG = "MainPresenter"
    }
}